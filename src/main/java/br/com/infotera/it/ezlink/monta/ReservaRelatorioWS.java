/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.monta;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSReserva;
import br.com.infotera.common.WSReservaHotel;
import br.com.infotera.common.WSReservaHotelUh;
import br.com.infotera.common.WSReservaNome;
import br.com.infotera.common.WSReservaRelatorioRQ;
import br.com.infotera.common.WSReservaRelatorioRS;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSPaxTipoEnum;
import br.com.infotera.common.enumerator.WSReservaStatusEnum;
import br.com.infotera.common.enumerator.WSSexoEnum;
import br.com.infotera.common.hotel.WSHotel;
import br.com.infotera.common.util.Utils;
import br.com.infotera.it.ezlink.ChamaWS;
import br.com.infotera.it.ezlink.model.BookingListResults;
import br.com.infotera.it.ezlink.rqrs.ListRQ;
import br.com.infotera.it.ezlink.rqrs.ListRS;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ReservaRelatorioWS {

    ChamaWS chamaWS = new ChamaWS();

    public WSReservaRelatorioRS relatorio(WSReservaRelatorioRQ reservaRelatorioRQ) throws ErrorException {
                
        String dtCheckin = Utils.formatData(reservaRelatorioRQ.getDtInicial(), "yyyy-MM-dd'T'HH:mm:ss");
        String dtCheckout = Utils.formatData(reservaRelatorioRQ.getDtFinal(), "yyyy-MM-dd'T'HH:mm:ss");
        
        ListRQ listRQ = new ListRQ(dtCheckin, dtCheckout);

        ListRS listRS = chamaWS.chamadaPadrao(reservaRelatorioRQ.getIntegrador(), listRQ, ListRS.class);

        List<WSReserva> reservaList = new ArrayList();
        
        for (BookingListResults blr : listRS.getBookingListResponse().getBookingListResults()) {

            Date dtReserva = Utils.toDate(blr.getCreatedAt(), "yyyy-MM-dd'T'HH:mm:ss");

            List<WSReservaNome> nmReservaList = new ArrayList();
            List<String> nmst = new ArrayList();

            nmReservaList.add(new WSReservaNome(blr.getPax().getFirstName(), blr.getPax().getLastName(), null, null, null));

            List<WSReservaHotelUh> reservaHotelUhList = new ArrayList();

            reservaHotelUhList.add(new WSReservaHotelUh(null, nmReservaList));

            WSReservaStatusEnum reservaStatusEnum = null;

            if (blr.getStatus().equals("Confirmed")) {
                reservaStatusEnum = WSReservaStatusEnum.CONFIRMADO;
            } else if (blr.getStatus().equals("Rejected")) {
                reservaStatusEnum = WSReservaStatusEnum.NEGADO;
            } else if (blr.getStatus().equals("Cancelled")) {
                reservaStatusEnum = WSReservaStatusEnum.CANCELADO;
            }

            WSReservaHotel reservaHotel = new WSReservaHotel(dtReserva,
                    null,
                    blr.getBookingId().toString(),
                    new WSHotel(null, blr.getHotelName(), null, null),
                    reservaHotelUhList,
                    null,
                    null,
                    reservaStatusEnum,
                    null,
                    null);

            WSReserva reserva = new WSReserva(reservaHotel);

            reservaList.add(reserva);
        }

        return new WSReservaRelatorioRS(reservaList, reservaRelatorioRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);

    }

}
