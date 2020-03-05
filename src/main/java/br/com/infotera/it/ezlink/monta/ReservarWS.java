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
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.enumerator.WSPaxTipoEnum;
import br.com.infotera.common.reserva.rqrs.WSReservaRQ;
import br.com.infotera.common.reserva.rqrs.WSReservaRS;
import br.com.infotera.common.reserva.rqrs.WSReservarRQ;
import br.com.infotera.common.reserva.rqrs.WSReservarRS;
import br.com.infotera.it.ezlink.ChamaWS;
import br.com.infotera.it.ezlink.UtilsWS;
import br.com.infotera.it.ezlink.model.Pax;
import br.com.infotera.it.ezlink.model.Room;
import br.com.infotera.it.ezlink.rqrs.BookRQ;
import br.com.infotera.it.ezlink.rqrs.BookRS;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ReservarWS {

    UtilsWS utilsWS = new UtilsWS();
    ChamaWS chamaWS = new ChamaWS();
    ConsultaReservaWS consultaReservaWS = new ConsultaReservaWS();

    public WSReservarRS reservar(WSReservarRQ reservarRQ) throws ErrorException {

        List<Room> roomList = new ArrayList();

        try {

            for (WSReservaHotelUh rhuh : reservarRQ.getReserva().getReservaHotel().getReservaHotelUhList()) {

                List<Pax> paxList = new ArrayList();

                for (WSReservaNome rn : rhuh.getReservaNomeList()) {

                    String paxTipo;
                    if (rn.getPaxTipo().equals(WSPaxTipoEnum.ADT)) {
                        paxTipo = "ADT";
                    } else {
                        paxTipo = "CHD";
                    }

                    paxList.add(new Pax(rn.getNmNome(), rn.getNmSobrenome(), rn.getQtIdade(), paxTipo));

                }

                String chvSplitRoomId[] = rhuh.getUh().getDsParametro().split("#");
                String roomId = chvSplitRoomId[0];

                roomList.add(new Room(roomId, paxList));

            }

        } catch (Exception ex) {
            throw new ErrorException(reservarRQ.getIntegrador(), ReservaRelatorioWS.class, "reserva", WSMensagemErroEnum.HRE, "Ocorreu uma falha ao efetuar a reserva do quarto ", WSIntegracaoStatusEnum.NEGADO, ex);
        }

        BookRQ bookRQ = new BookRQ(
                reservarRQ.getReserva().getReservaHotel().getDsParametro(),
                reservarRQ.getReserva().getId(),
                roomList);

        BookRS bookRS = chamaWS.chamadaPadrao(reservarRQ.getIntegrador(), bookRQ, BookRS.class);

        WSReserva reserva = new WSReserva(new WSReservaHotel(bookRS.getBooking().getId().toString()));

        WSReservaRS reservaRS = consultaReservaWS.consulta(new WSReservaRQ(reservarRQ.getIntegrador(), reserva), false);
        
        return new WSReservarRS(reservaRS.getReserva(), reservarRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);

    }

}
