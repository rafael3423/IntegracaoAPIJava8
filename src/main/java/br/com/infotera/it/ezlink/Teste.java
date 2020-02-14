/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSAutenticacao;
import br.com.infotera.common.WSIntegrador;
import br.com.infotera.common.WSPreAlterarRS;
import br.com.infotera.common.WSPreReservarRQ;
import br.com.infotera.common.WSPreReservarRS;
import br.com.infotera.common.WSReserva;
import br.com.infotera.common.WSReservaHotel;
import br.com.infotera.common.WSReservaHotelUh;
import br.com.infotera.common.WSReservaNome;
import br.com.infotera.common.WSReservaRelatorioRQ;
import br.com.infotera.common.WSReservaRelatorioRS;
import br.com.infotera.common.enumerator.WSAmbienteEnum;
import br.com.infotera.common.enumerator.WSIntegradorEnum;
import br.com.infotera.common.enumerator.WSPaxTipoEnum;
import br.com.infotera.common.enumerator.WSRelatorioPeriodoEnum;
import br.com.infotera.common.enumerator.WSReservaStatusEnum;
import br.com.infotera.common.enumerator.WSSexoEnum;
import br.com.infotera.common.hotel.WSConfigUh;
import br.com.infotera.common.hotel.WSConsultaReservaRQ;
import br.com.infotera.common.hotel.WSHospede;
import br.com.infotera.common.hotel.WSHotel;
import br.com.infotera.common.hotel.rqrs.WSDetalheHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSDisponibilidadeHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSDisponibilidadeHotelRS;
import br.com.infotera.common.hotel.rqrs.WSPesquisaHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSPesquisaHotelRS;
import br.com.infotera.common.reserva.rqrs.WSReservaRQ;
import br.com.infotera.common.reserva.rqrs.WSReservaRS;
import br.com.infotera.common.reserva.rqrs.WSReservarRQ;
import br.com.infotera.common.util.Utils;
import br.com.infotera.it.teste.Printa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Teste {

    public static void main(String[] args) throws ErrorException {

        EzlinkWS ezLinkWS = new EzlinkWS();
        List<String> dsCredencialList = new ArrayList();

        dsCredencialList.add("5e3c81a254a7ee2f6bc358af.30aa335a8090f28d096976e38ecf68");

        WSIntegrador integrador = new WSIntegrador(1,
                "ezlink",
                WSIntegradorEnum.INFOTRAVEL,
                "BRL",
                30,
                WSAmbienteEnum.HOMOLOGACAO,
                dsCredencialList);

        //DISPONIBILIDADE
        Date dtreservaFrom = Utils.toDate("28-02-2020", "dd-MM-yyyy");
        Date dtReservaTo = Utils.toDate("29-02-2020", "dd-MM-yyyy");

        List<WSConfigUh> configUh = new ArrayList();
        List<WSReservaNome> reservaNomeList = new ArrayList();
        reservaNomeList.add(new WSReservaNome("jose", "silva", WSPaxTipoEnum.ADT, null, 30, null));
        reservaNomeList.add(new WSReservaNome("jose", "silva", WSPaxTipoEnum.ADT, null, 30, null));
        configUh.add(new WSConfigUh(reservaNomeList));

        List<WSHotel> hotelIds = new ArrayList();
        hotelIds.add(new WSHotel(null, "5d433db9b9d26646cd01bce9", null));
        hotelIds.add(new WSHotel(null, "5d433dc34da68646fb27e333", null));

        WSDisponibilidadeHotelRS hotelRS = ezLinkWS.disponibilidade(new WSDisponibilidadeHotelRQ(integrador,
                dtreservaFrom,
                dtReservaTo,
                null,
                configUh,
                hotelIds));

        Printa.disponibilidadeHotel(hotelRS, false, false);

        WSReservaHotelUh reservaHotelUh = new WSReservaHotelUh(1,
                hotelRS.getHotelPesquisaList().get(0).getQuartoList().get(0).getQuartoUhList().get(0).getUh(),
                hotelRS.getHotelPesquisaList().get(0).getQuartoList().get(0).getQuartoUhList().get(0).getRegime(),
                hotelRS.getHotelPesquisaList().get(0).getQuartoList().get(0).getQuartoUhList().get(0).getTarifa(),
                null,
                null,
                reservaNomeList,
                WSReservaStatusEnum.SOLICITACAO);

        List<WSReservaHotelUh> reservaHotelUhList = Arrays.asList(reservaHotelUh);
        
        WSReservaHotel reservaHotel = new WSReservaHotel(reservaHotelUhList);
        
        reservaHotel.setDsParametro(hotelRS.getHotelPesquisaList().get(0).getDsParametro());
        
        WSReserva reserva = new WSReserva(reservaHotel);
        
        WSPreReservarRS preReservarRS = ezLinkWS.preReservar(new WSPreReservarRQ(integrador, reserva));
        System.out.println(preReservarRS);

//        ezLinkWS.reservar(new WSReservarRQ(integrador, null));
        //RESERVA RELATORIO
//        Date dtReservaFrom = Utils.toDate("01-01-2020","dd-MM-yyyy");
//        Date dtReservaTo = Utils.toDate("10-02-2020T23:59:59","dd-MM-yyyy'T'HH:mm:ss");
//        
//
//        WSReservaRelatorioRS reservaRelatorioRS = ezLinkWS.relatorio(new WSReservaRelatorioRQ(integrador,
//                null,
//                dtReservaFrom,
//                dtReservaTo));
//
//        for (WSReserva rr : reservaRelatorioRS.getReservaList()) {
//
//            String reservaLoc = rr.getReservaHotel().getNrLocalizador();
//            System.out.println(reservaLoc);
//            WSReservaStatusEnum reservaStatusEnum = rr.getReservaHotel().getReservaStatus();
//            System.out.println(reservaStatusEnum);
//
//        }
//        ezLinkWS.consulta(new WSReservaRQ(integrador, null));
//        ezLinkWS.cancelar(new WSReservaRQ(integrador, null));
//            ezLinkWS.detalheHotel(new WSDetalheHotelRQ(integrador, null));
//            ezLinkWS.pesquisaHotel(new WSPesquisaHotelRQ(integrador, null, true));
    }
}
