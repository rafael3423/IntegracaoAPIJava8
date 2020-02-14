/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.rqrs;

import br.com.infotera.it.ezlink.model.CancelledBooking;

/**
 *
 * @author rafael
 */
public class CancelRS {

    private CancelledBooking cancelledBooking;

    public CancelRS() {
    }

    public CancelledBooking getCancelledBooking() {
        return cancelledBooking;
    }

    public void setCancelledBooking(CancelledBooking cancelledBooking) {
        this.cancelledBooking = cancelledBooking;
    }

}
