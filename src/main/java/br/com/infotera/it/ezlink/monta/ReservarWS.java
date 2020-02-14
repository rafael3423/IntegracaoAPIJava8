/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.monta;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.reserva.rqrs.WSReservarRQ;
import br.com.infotera.common.reserva.rqrs.WSReservarRS;
import br.com.infotera.it.ezlink.ChamaWS;
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
    
    ChamaWS chamaWS = new ChamaWS();

    public WSReservarRS reservar(WSReservarRQ reservarRQ) throws ErrorException {


        List<Room> roomList = new ArrayList();
        List<Pax> paxList = new ArrayList();
        
        paxList.add(new Pax("Teste", "Notebook"));
              
        roomList.add(new Room("6cefeab0-4d01-11ea-b2bd-ad23287b2998", paxList));




        BookRQ bookRQ = new BookRQ(
                "68b2b630-4d01-11ea-b2bd-ad23287b2998", 
                "vfw357", 
                roomList);

        chamaWS.chamadaPadrao(reservarRQ.getIntegrador(), bookRQ, BookRS.class);


        return null;

    }

}

