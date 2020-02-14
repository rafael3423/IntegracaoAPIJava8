/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.monta;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.reserva.rqrs.WSReservaRQ;
import br.com.infotera.common.reserva.rqrs.WSReservaRS;
import br.com.infotera.it.ezlink.ChamaWS;
import br.com.infotera.it.ezlink.rqrs.CancelRQ;
import br.com.infotera.it.ezlink.rqrs.CancelRS;

/**
 *
 * @author rafael
 */
public class CancelaReservaWS {

    ChamaWS chamaWS = new ChamaWS();

    public WSReservaRS cancela(WSReservaRQ cancelaReservaRQ) throws ErrorException {

        CancelRQ cancelRQ = new CancelRQ(1074,
                1383,
                true);

        chamaWS.chamadaPadrao(cancelaReservaRQ.getIntegrador(), cancelRQ, CancelRS.class);

        return null;
    }
}
