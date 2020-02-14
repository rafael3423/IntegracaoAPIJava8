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
import br.com.infotera.it.ezlink.rqrs.DetailsRQ;
import br.com.infotera.it.ezlink.rqrs.DetailsRS;

/**
 *
 * @author rafael
 */
public class ConsultaReservaWS {
    
        ChamaWS chamaWS = new ChamaWS();
    
        public WSReservaRS consulta(WSReservaRQ consultaReservaRQ) throws ErrorException {

        DetailsRQ detailsRQ = new DetailsRQ(1074);

        chamaWS.chamadaPadrao(consultaReservaRQ.getIntegrador(), detailsRQ, DetailsRS.class);

        return null;

    }
    
}
