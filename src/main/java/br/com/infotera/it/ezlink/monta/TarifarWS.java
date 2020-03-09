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
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSReservaStatusEnum;
import br.com.infotera.common.hotel.rqrs.WSTarifarHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSTarifarHotelRS;

/**
 *
 * @author rafael
 */
public class TarifarWS {

    public WSTarifarHotelRS tarifarHotel(WSTarifarHotelRQ tarifarHotelRQ) throws ErrorException {

        WSReservaHotel reservaHotel = new WSReservaHotel(WSReservaStatusEnum.SOLICITACAO, null, tarifarHotelRQ.getReservaHotel().getReservaHotelUhList());

        reservaHotel.setDsParametro(tarifarHotelRQ.getReservaHotel().getDsParametro());

        WSReserva reserva = new WSReserva(reservaHotel);

        PreReservarWS preReservarWS = new PreReservarWS();
        WSPreReservarRS preReservarRS = preReservarWS.preReservar(new WSPreReservarRQ(tarifarHotelRQ.getIntegrador(), reserva));

        WSReservaHotel reservaHotelPre = new WSReservaHotel(WSReservaStatusEnum.SOLICITACAO,
                preReservarRS.getReserva().getReservaHotel().getHotel(),
                preReservarRS.getReserva().getReservaHotel().getReservaHotelUhList());

        reservaHotelPre.setDsParametro(preReservarRS.getReserva().getReservaHotel().getDsParametro());

        return new WSTarifarHotelRS(reservaHotelPre, tarifarHotelRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);

    }

}
