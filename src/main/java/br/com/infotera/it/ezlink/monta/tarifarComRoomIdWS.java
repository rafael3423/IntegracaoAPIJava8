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
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.enumerator.WSReservaStatusEnum;
import br.com.infotera.common.hotel.WSConfigUh;
import br.com.infotera.common.hotel.WSHotel;
import br.com.infotera.common.hotel.WSHotelPesquisa;
import br.com.infotera.common.hotel.WSQuarto;
import br.com.infotera.common.hotel.WSQuartoUh;
import br.com.infotera.common.hotel.rqrs.WSDisponibilidadeHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSDisponibilidadeHotelRS;
import br.com.infotera.common.hotel.rqrs.WSTarifarHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSTarifarHotelRS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rafael
 */
public class tarifarComRoomIdWS {

    public WSTarifarHotelRS tarifarHotel(WSTarifarHotelRQ tarifarHotelRQ) throws ErrorException {

        DisponibilidadeWS disponibilidadeWS = new DisponibilidadeWS();

        List<WSConfigUh> configUhList = new ArrayList();
        String chavePesquisa = null;
        int sqUh = 1;
        try {
            for (WSReservaHotelUh rhuh : tarifarHotelRQ.getReservaHotel().getReservaHotelUhList()) {

                List<WSReservaNome> reservaNomeList = new ArrayList();
                try {
                    for (WSReservaNome rn : rhuh.getReservaNomeList()) {
                        reservaNomeList.add(new WSReservaNome(rn.getNmNome(),
                                rn.getNmSobrenome(),
                                rn.getPaxTipo(),
                                rn.getDtNascimento(),
                                rn.getQtIdade(),
                                rn.getSexo(),
                                rn.getDocumento(),
                                rn.getId()));

                    }
                } catch (Exception ex) {
                    throw new ErrorException(tarifarHotelRQ.getIntegrador(), TarifarWS.class, "tarifarHotel", WSMensagemErroEnum.HTA, "Ocorreu uma falha ao gerar tarifas", WSIntegracaoStatusEnum.NEGADO, ex);
                }

                String chvaPesqSplit[] = rhuh.getUh().getDsParametro().split("#");
                String refundable = chvaPesqSplit[1];

                if (chavePesquisa != null) {
                    chavePesquisa = chavePesquisa + "%"
                            + sqUh++ + "#"
                            + rhuh.getUh().getDsUh() + "#"
                            + rhuh.getRegime().getCdRegime()
                            + "#" + rhuh.getRegime().getDsRegime()
                            + "#" + refundable;
                } else {
                    chavePesquisa = sqUh++ + "#"
                            + rhuh.getUh().getDsUh() + "#"
                            + rhuh.getRegime().getCdRegime() + "#"
                            + rhuh.getRegime().getDsRegime() + "#"
                            + refundable;
                }

                configUhList.add(new WSConfigUh(reservaNomeList));
            }
        } catch (Exception ex) {
            throw new ErrorException(tarifarHotelRQ.getIntegrador(), TarifarWS.class, "tarifarHotel", WSMensagemErroEnum.HTA, "Ocorreu uma falha ao gerar tarifas", WSIntegracaoStatusEnum.NEGADO, ex);
        }

        List<WSHotel> hotelList = new ArrayList();

        hotelList.add(tarifarHotelRQ.getReservaHotel().getHotel());

        WSDisponibilidadeHotelRQ disponibilidadeRQ = new WSDisponibilidadeHotelRQ(tarifarHotelRQ.getIntegrador(),
                tarifarHotelRQ.getReservaHotel().getReservaHotelUhList().get(0).getDtEntrada(),
                tarifarHotelRQ.getReservaHotel().getReservaHotelUhList().get(0).getDtSaida(),
                null,
                configUhList,
                hotelList);

        //chamando a disponibilidade
        WSDisponibilidadeHotelRS disponibilidadeHotelRS = disponibilidadeWS.disponibilidade(disponibilidadeRQ);

        //montar um MAP para armazenar todo o resultado da busca
        Map<String, WSQuartoUh> mapQuartoUH = new HashMap();

        try {
            for (WSHotelPesquisa hp : disponibilidadeHotelRS.getHotelPesquisaList()) {
                int flag = 1;
                for (WSQuarto q : hp.getQuartoList()) {
                    for (WSQuartoUh quh : q.getQuartoUhList()) {

                        String chvaPesqSplit[] = quh.getUh().getDsParametro().split("#");
                        String refundable = chvaPesqSplit[1];

                        String chaveUh = flag + "#"
                                + quh.getUh().getDsUh() + "#"
                                + quh.getRegime().getCdRegime() + "#"
                                + quh.getRegime().getDsRegime() + "#"
                                + refundable;

                        if (mapQuartoUH.get(chaveUh) != null) {
                            WSQuartoUh quhMap = mapQuartoUH.get(chaveUh);
                            if (quh.getTarifa().getVlNeto() <= quhMap.getTarifa().getVlNeto()) {
                                mapQuartoUH.put(chaveUh, quh);
                            }
                        } else {
                            mapQuartoUH.put(chaveUh, quh);
                        }
                    }
                    flag++;

                }
            }
        } catch (Exception ex) {
            throw new ErrorException(tarifarHotelRQ.getIntegrador(), TarifarWS.class, "tarifarHotel", WSMensagemErroEnum.HTA, "Ocorreu uma falha ao gerar tarifas", WSIntegracaoStatusEnum.NEGADO, ex);
        }

        String chvaPesqSplit[] = chavePesquisa.split("%");

        List<WSReservaHotelUh> reservaHotelUhList = new ArrayList();
        try {
            for (String chva : chvaPesqSplit) {

                WSQuartoUh quartoUh = mapQuartoUH.get(chva);

                WSReservaHotelUh reservaHotelUh = new WSReservaHotelUh(sqUh,
                        quartoUh.getUh(),
                        quartoUh.getRegime(),
                        quartoUh.getTarifa(),
                        disponibilidadeHotelRS.getHotelPesquisaList().get(0).getDtEntrada(),
                        disponibilidadeHotelRS.getHotelPesquisaList().get(0).getDtSaida(),
                        null);

                reservaHotelUh.setReservaNomeList(disponibilidadeRQ.getConfigUhList().get(0).getReservaNomeList());
                reservaHotelUhList.add(reservaHotelUh);

            }
        } catch (Exception ex) {
            throw new ErrorException(tarifarHotelRQ.getIntegrador(), TarifarWS.class, "tarifarHotel", WSMensagemErroEnum.HTA, "Ocorreu uma falha ao gerar tarifas", WSIntegracaoStatusEnum.NEGADO, ex);
        }

        WSReservaHotel reservaHotel = new WSReservaHotel(WSReservaStatusEnum.SOLICITACAO, null, reservaHotelUhList);

        reservaHotel.setDsParametro(disponibilidadeHotelRS.getHotelPesquisaList().get(0).getDsParametro());

        if (tarifarHotelRQ.getReservaHotel().getReservaStatus().equals(WSReservaStatusEnum.SOLICITACAO)) {
            PreReservarWS preReservarWS = new PreReservarWS();

            WSReserva reserva = new WSReserva(reservaHotel);

            WSPreReservarRS preReservarRS = preReservarWS.preReservar(new WSPreReservarRQ(tarifarHotelRQ.getIntegrador(), reserva));

            WSReservaHotel reservaHotelPre = new WSReservaHotel(WSReservaStatusEnum.SOLICITACAO,
                    preReservarRS.getReserva().getReservaHotel().getHotel(),
                    preReservarRS.getReserva().getReservaHotel().getReservaHotelUhList());

            reservaHotelPre.setDsParametro(preReservarRS.getReserva().getReservaHotel().getDsParametro());

            return new WSTarifarHotelRS(reservaHotelPre, tarifarHotelRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);

        } else {
            return new WSTarifarHotelRS(reservaHotel, tarifarHotelRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);
        }

    }

}
