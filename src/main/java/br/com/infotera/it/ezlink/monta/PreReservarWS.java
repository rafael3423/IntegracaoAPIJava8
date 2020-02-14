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
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSReservaStatusEnum;
import br.com.infotera.common.hotel.WSQuarto;
import br.com.infotera.common.hotel.WSQuartoUh;
import static br.com.infotera.common.util.Utils.chamaWS;
import br.com.infotera.it.ezlink.ChamaWS;
import br.com.infotera.it.ezlink.model.Room;
import br.com.infotera.it.ezlink.rqrs.QuoteRQ;
import br.com.infotera.it.ezlink.rqrs.QuoteRS;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class PreReservarWS {

    ChamaWS chamaWS = new ChamaWS();

    public WSPreReservarRS preReservar(WSPreReservarRQ preReservarRQ) throws ErrorException {

        List<String> roomIdList = new ArrayList();

        for (WSReservaHotelUh rhuh : preReservarRQ.getReserva().getReservaHotel().getReservaHotelUhList()) {

            roomIdList.add(rhuh.getUh().getDsParametro());
        }

        QuoteRQ quoteRQ = new QuoteRQ(preReservarRQ.getReserva().getReservaHotel().getDsParametro(), roomIdList);

        QuoteRS quoteRS = chamaWS.chamadaPadrao(preReservarRQ.getIntegrador(), quoteRQ, QuoteRS.class);
        System.out.println(quoteRS);

        List<WSReservaHotelUh> reservaHotelUhList = new ArrayList();

//        WSQuartoUh quartoUh = new WSQuartoUh(uh,
//                regime,
//                tarifa,
//                Integer.SIZE)
//        
//        for (Room rm : quoteRS.getQuoteResponse().getRooms()) {
//            reservaHotelUhList.add(new WSReservaHotelUh(Integer.SIZE,
//                    ,
//                     regime,
//                    tarifa,
//                    dtEntrada,
//                    dtSaida,
//                    reservaNomeList,
//                    WSReservaStatusEnum.RESERVADO));
//        }

        WSReservaHotel reservaHotel = new WSReservaHotel(reservaHotelUhList);
        WSReserva reserva = new WSReserva(reservaHotel);

        return new WSPreReservarRS(reserva, preReservarRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);

    }
}
