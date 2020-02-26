/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.monta;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSPreReservarRQ;
import br.com.infotera.common.WSPreReservarRS;
import br.com.infotera.common.WSReserva;
import br.com.infotera.common.WSReservaHotel;
import br.com.infotera.common.WSReservaHotelUh;
import br.com.infotera.common.WSReservaNome;
import br.com.infotera.common.WSTarifa;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.enumerator.WSReservaStatusEnum;
import br.com.infotera.common.hotel.WSUh;
import br.com.infotera.common.politica.WSPolitica;
import br.com.infotera.it.ezlink.ChamaWS;
import br.com.infotera.it.ezlink.UtilsWS;
import br.com.infotera.it.ezlink.model.Room;
import br.com.infotera.it.ezlink.rqrs.QuoteRQ;
import br.com.infotera.it.ezlink.rqrs.QuoteRS;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author rafael
 */
public class PreReservarWS {

    ChamaWS chamaWS = new ChamaWS();

    public WSPreReservarRS preReservar(WSPreReservarRQ preReservarRQ) throws ErrorException {

        List<String> roomIdList = new ArrayList();
        try {

            for (WSReservaHotelUh rhuh : preReservarRQ.getReserva().getReservaHotel().getReservaHotelUhList()) {
                roomIdList.add(rhuh.getUh().getDsParametro());
            }
        } catch (Exception ex) {
            throw new ErrorException(preReservarRQ.getIntegrador(), PreReservarWS.class, "preReservar", WSMensagemErroEnum.HPR, "Ocorreu uma falha ao efetuar a pré reserva do quarto", WSIntegracaoStatusEnum.NEGADO, ex);
        }

        QuoteRQ quoteRQ = new QuoteRQ(preReservarRQ.getReserva().getReservaHotel().getDsParametro(), roomIdList);

        QuoteRS quoteRS = chamaWS.chamadaPadrao(preReservarRQ.getIntegrador(), quoteRQ, QuoteRS.class);
        System.out.println(quoteRS);

        int sqQuarto = 1;
        List<WSReservaHotelUh> reservaHotelUhList = new ArrayList();

        Map<String, Room> roomMap = new LinkedHashMap();

        try {

            for (Room rm : quoteRS.getQuoteResponse().getRooms()) {
                roomMap.put(rm.getRoomId(), rm);
            }
        } catch (Exception ex) {
            throw new ErrorException(preReservarRQ.getIntegrador(), PreReservarWS.class, "preReservar", WSMensagemErroEnum.HPR, "Ocorreu uma falha ao efetuar a pré reserva do quarto", WSIntegracaoStatusEnum.NEGADO, ex);
        }

        System.out.println(roomMap);

        try {

            for (WSReservaHotelUh rhu : preReservarRQ.getReserva().getReservaHotel().getReservaHotelUhList()) {

                Room room = roomMap.get(rhu.getUh().getDsParametro());
                //setamos o novo room id
                rhu.getUh().setDsParametro(room.getRoomId());
                //setamos a nova tarifa

                UtilsWS utils = new UtilsWS();

                List<WSPolitica> politicaCancelamentoList = new ArrayList();

                if (room.getCancellationPolicies() != null) {
                    politicaCancelamentoList = utils.politicaCancelamento(room.getCancellationPolicies().getPenalties(),
                            room.getCancellationPolicies().getRefundable(),
                            room.getRemarks());
                }

                rhu.setTarifa(new WSTarifa(room.getPrice().getCurrency(),
                        room.getPrice().getValue(),
                        null,
                        null,
                        null,
                        politicaCancelamentoList));

                rhu.setUh(new WSUh(null,
                        rhu.getUh().getCdUh(),
                        rhu.getUh().getDsCategoria(),
                        rhu.getUh().getDsUh(),
                        rhu.getUh().getDsParametro()));

                List<WSReservaNome> reservaNomeList = new ArrayList();

                try {

                    for (WSReservaNome rn : rhu.getReservaNomeList()) {

                        reservaNomeList.add(new WSReservaNome(rn.getNmNome(),
                                rn.getNmSobrenome(),
                                rn.getPaxTipo(),
                                rn.getDtNascimento(),
                                rn.getQtIdade(),
                                rn.getSexo()));

                        rhu.setReservaNomeList(reservaNomeList);

                        reservaHotelUhList.add(new WSReservaHotelUh(sqQuarto++,
                                rhu.getUh(),
                                rhu.getRegime(),
                                rhu.getTarifa(),
                                preReservarRQ.getDtEntrada(),
                                preReservarRQ.getDtSaida(),
                                rhu.getReservaNomeList(),
                                WSReservaStatusEnum.SOLICITACAO));
                    }
                } catch (Exception ex) {
                    throw new ErrorException(preReservarRQ.getIntegrador(), PreReservarWS.class, "preReservar", WSMensagemErroEnum.HPR, "Ocorreu uma falha ao efetuar a pré reserva do quarto", WSIntegracaoStatusEnum.NEGADO, ex);
                }
            }
        } catch (Exception ex) {
            throw new ErrorException(preReservarRQ.getIntegrador(), PreReservarWS.class, "preReservar", WSMensagemErroEnum.HPR, "Ocorreu uma falha ao efetuar a pré reserva do quarto", WSIntegracaoStatusEnum.NEGADO, ex);
        }
        WSReservaHotel reservaHotel = new WSReservaHotel(reservaHotelUhList);

        reservaHotel.setHotel(preReservarRQ.getReserva().getReservaHotel().getHotel());
        reservaHotel.setDsParametro(preReservarRQ.getReserva().getReservaHotel().getDsParametro());

        Random r = new Random();
        WSReserva reserva = new WSReserva(reservaHotel);
        reserva.setIdExterno("vfw" + r.nextInt(400));

        return new WSPreReservarRS(reserva, preReservarRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);
    }

}
