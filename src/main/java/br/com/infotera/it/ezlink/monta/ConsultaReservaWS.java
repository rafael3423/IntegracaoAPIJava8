/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.monta;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSEndereco;
import br.com.infotera.common.WSIntegrador;
import br.com.infotera.common.WSReserva;
import br.com.infotera.common.WSReservaHotel;
import br.com.infotera.common.WSReservaHotelUh;
import br.com.infotera.common.WSReservaNome;
import br.com.infotera.common.WSTarifa;
import br.com.infotera.common.WSTarifaAdicional;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.enumerator.WSPagtoFornecedorTipoEnum;
import br.com.infotera.common.enumerator.WSPaxTipoEnum;
import br.com.infotera.common.enumerator.WSReservaStatusEnum;
import br.com.infotera.common.enumerator.WSTarifaAdicionalTipoEnum;
import br.com.infotera.common.hotel.WSHotel;
import br.com.infotera.common.hotel.WSHotelCategoria;
import br.com.infotera.common.hotel.WSRegime;
import br.com.infotera.common.hotel.WSUh;
import br.com.infotera.common.politica.WSPolitica;
import br.com.infotera.common.reserva.rqrs.WSReservaRQ;
import br.com.infotera.common.reserva.rqrs.WSReservaRS;
import br.com.infotera.common.util.Utils;
import br.com.infotera.it.ezlink.ChamaWS;
import br.com.infotera.it.ezlink.UtilsWS;
import br.com.infotera.it.ezlink.model.Booking;
import br.com.infotera.it.ezlink.model.Hotel;
import br.com.infotera.it.ezlink.model.Pax;
import br.com.infotera.it.ezlink.model.RoomBooking;
import br.com.infotera.it.ezlink.rqrs.DetailsRQ;
import br.com.infotera.it.ezlink.rqrs.DetailsRS;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ConsultaReservaWS {

    UtilsWS utilsWS = new UtilsWS();
    ChamaWS chamaWS = new ChamaWS();

    public WSReservaRS consulta(WSReservaRQ consultaReservaRQ, Boolean isCancelamento) throws ErrorException {

        DetailsRQ detailsRQ = new DetailsRQ(Integer.parseInt(consultaReservaRQ.getReserva().getReservaHotel().getNrLocalizador()));

        DetailsRS detailsRS = chamaWS.chamadaPadrao(consultaReservaRQ.getIntegrador(), detailsRQ, DetailsRS.class);

        WSReserva reserva = montaReserva(consultaReservaRQ.getIntegrador(), detailsRS.getBooking(), isCancelamento);

        return new WSReservaRS(reserva, consultaReservaRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);
    }

    public WSReserva montaReserva(WSIntegrador integrador, Booking booking, Boolean isCancelamento) throws ErrorException {

        WSHotel hotel = new WSHotel();

        List<WSReservaHotelUh> reservaHotelUh = new ArrayList();

        WSReservaStatusEnum reservaStatus = null;

        try {

            for (Hotel h : booking.getHotels()) {

                WSEndereco endereco = new WSEndereco(h.getLocalization().getAddress(),
                        "",
                        "",
                        "",
                        h.getLocalization().getCountryISO2(),
                        "",
                        h.getLocalization().getCoordinates().get(0).toString(),
                        h.getLocalization().getCoordinates().get(1).toString());

                WSHotelCategoria hotelCategoria = new WSHotelCategoria(null, h.getCategory().toString());

                hotel = new WSHotel(h.getId(),
                        h.getName(),
                        null,
                        hotelCategoria,
                        null,
                        null,
                        endereco,
                        null,
                        null);

                int qntCancelado = 0;
                int qntConfirmado = 0;
                int sqQuarto = 0;

                List<WSTarifaAdicional> tarifaAdicionalList = null;

                try {

                    for (RoomBooking r : h.getRooms()) {

                        sqQuarto++;

                        WSUh uh = new WSUh(r.getName(),
                                null,
                                null,
                                null,
                                null);

                        UtilsWS utilsWS = new UtilsWS();

                        List<WSPolitica> politicaCancelamentoList = new ArrayList();

                        if (r.getCancellationPolicies() != null) {
                            politicaCancelamentoList = utilsWS.montaPoliticaCancelamento(r.getCancellationPolicies().getPenalties(),
                                    r.getCancellationPolicies().getRefundable(),
                                    r.getRemarks());
                        }

                        //adiciona tarifas adicionais do tipo multa
                        Double vlMulta = 0.0;

                        try {

                            for (WSPolitica politica : politicaCancelamentoList) {
                                if (politica.getPoliticaTipo().isCancelamento()) {
                                    if ((politica.getPoliticaCancelamento().isStNaoReembolsavel()) || (new Date().compareTo(politica.getPoliticaCancelamento().getDtMinCancelamento()) == 1)) {
                                        if (politica.getPoliticaCancelamento().getDtMaxCancelamento() != null) {
                                            if (new Date().compareTo(politica.getPoliticaCancelamento().getDtMinCancelamento()) == 1 && new Date().compareTo(politica.getPoliticaCancelamento().getDtMaxCancelamento()) == -1) {
                                                vlMulta = politica.getPoliticaCancelamento().getVlCancelamento();
                                            }
                                        } else {
                                            vlMulta = politica.getPoliticaCancelamento().getVlCancelamento();
                                        }
                                    }
                                }
                            }

                            if (vlMulta != 0.0 && isCancelamento) {
                                if (tarifaAdicionalList == null) {
                                    tarifaAdicionalList = new ArrayList();
                                }
                                tarifaAdicionalList.add(new WSTarifaAdicional(WSTarifaAdicionalTipoEnum.MULTA, "Multa de cancelamento", r.getPrice().getCurrency(), vlMulta));
                            }
                        } catch (Exception ex) {
                            throw new ErrorException(integrador, UtilsWS.class, "montareserva", WSMensagemErroEnum.HCO, "Erro ao montar a tarifa de cancelamento", WSIntegracaoStatusEnum.INCONSISTENTE, ex);
                        }

                        WSTarifa tarifa = new WSTarifa(r.getPrice().getCurrency(),
                                r.getPrice().getValue(),
                                null,
                                null,
                                tarifaAdicionalList,
                                WSPagtoFornecedorTipoEnum.FATURADO,
                                politicaCancelamentoList);

                        WSRegime regime = new WSRegime(r.getBoard(), r.getBoard(), r.getBoard());

                        List<WSReservaNome> reservaNomeList = new ArrayList();
                        try {

                            for (Pax pa : r.getPax()) {

                                WSPaxTipoEnum paxTipoEnum = null;

                                if (pa.getAge() > 11 || pa.getType().equals("ADT")) {
                                    paxTipoEnum = WSPaxTipoEnum.ADT;

                                } else if (pa.getAge() > 1) {
                                    paxTipoEnum = WSPaxTipoEnum.CHD;

                                } else {
                                    paxTipoEnum = WSPaxTipoEnum.INF;
                                }

                                reservaNomeList.add(new WSReservaNome(pa.getFirstName(),
                                        pa.getLastName(),
                                        paxTipoEnum,
                                        null,
                                        pa.getAge(),
                                        null));
                            }
                        } catch (Exception ex) {
                            throw new ErrorException(integrador, UtilsWS.class, "montareserva", WSMensagemErroEnum.HCO, "Erro ao montar Lista de pax", WSIntegracaoStatusEnum.INCONSISTENTE, ex);
                        }

                        WSReservaStatusEnum reservaStatusEnum = null;

                        if (r.getStatus().equals("Confirmed")) {
                            reservaStatusEnum = WSReservaStatusEnum.CONFIRMADO;
                            qntConfirmado++;

                        } else if (r.getStatus().equals("Rejected")) {
                            reservaStatusEnum = WSReservaStatusEnum.NEGADO;

                        } else {
                            reservaStatusEnum = WSReservaStatusEnum.CANCELADO;
                            qntCancelado++;
                        }

                        reservaHotelUh.add(new WSReservaHotelUh(sqQuarto,
                                uh,
                                regime,
                                tarifa,
                                Utils.toDate(r.getCheckIn(), "yyyy-MM-dd"),
                                Utils.toDate(r.getCheckOut(), "yyyy-MM-dd"),
                                reservaNomeList,
                                reservaStatusEnum));

                    }
                } catch (Exception ex) {
                    throw new ErrorException(integrador, UtilsWS.class, "montareserva", WSMensagemErroEnum.HCO, "Erro ao montar Rooms", WSIntegracaoStatusEnum.INCONSISTENTE, ex);
                }

                if (sqQuarto == qntConfirmado) {
                    reservaStatus = WSReservaStatusEnum.CONFIRMADO;
                } else if (sqQuarto == qntCancelado) {
                    reservaStatus = WSReservaStatusEnum.CANCELADO;
                } else {
                    reservaStatus = WSReservaStatusEnum.INCONSISTENTE;
                }
            }
        } catch (Exception ex) {
            throw new ErrorException(integrador, UtilsWS.class, "montareserva", WSMensagemErroEnum.HCO, "Erro ao montar Hotels", WSIntegracaoStatusEnum.INCONSISTENTE, ex);
        }

        WSReservaHotel reservaHotel = new WSReservaHotel(null,
                null,
                booking.getId().toString(),
                hotel,
                reservaHotelUh,
                null,
                null,
                reservaStatus,
                null,
                booking.getCustomerBookingId());

        WSReserva reserva = new WSReserva(reservaHotel);
        reserva.setId(booking.getId().toString());

        return reserva;
    }

}
