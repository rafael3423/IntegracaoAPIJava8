/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.monta;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.hotel.rqrs.WSPesquisaHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSPesquisaHotelRS;
import br.com.infotera.it.ezlink.ChamaWS;
import br.com.infotera.it.ezlink.rqrs.HotelsRQ;
import br.com.infotera.it.ezlink.rqrs.HotelsRS;

/**
 *
 * @author rafael
 */
public class PesquisarHotelWS {
        ChamaWS chamaWS = new ChamaWS();

    public WSPesquisaHotelRS pesquisarHotel(WSPesquisaHotelRQ pesquisaHotelRQ) throws ErrorException {

        HotelsRQ hotelsRQ = new HotelsRQ(
                600,
                100,
                "desc",
                "5d652a0e37f0a05da7a94a8e",
                "UK",
                "2018-12-19");

        chamaWS.chamadaPadrao(pesquisaHotelRQ.getIntegrador(), hotelsRQ, HotelsRS.class);

        return null;
    }
    
}
