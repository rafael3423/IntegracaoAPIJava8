/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.rqrs;

import br.com.infotera.it.ezlink.model.Erro;
import br.com.infotera.it.ezlink.model.HotelListResponse;

/**
 *
 * @author rafael
 */
public class HotelsRS extends Erro{

    private HotelListResponse hotelListResponse;

    public HotelsRS() {
    }

    public HotelListResponse getHotelListResponse() {
        return hotelListResponse;
    }

    public void setHotelListResponse(HotelListResponse hotelListResponse) {
        this.hotelListResponse = hotelListResponse;
    }

}
