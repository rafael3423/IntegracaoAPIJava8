/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.rqrs;

import br.com.infotera.it.ezlink.model.Booking;
import br.com.infotera.it.ezlink.model.Erro;

/**
 *
 * @author rafael
 */
public class DetailsRS extends Erro{
    
    private Booking booking;

    public DetailsRS() {
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
    
    
}
