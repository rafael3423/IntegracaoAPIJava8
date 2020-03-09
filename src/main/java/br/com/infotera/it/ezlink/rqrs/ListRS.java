/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.rqrs;

import br.com.infotera.it.ezlink.model.BookingListResponse;
import br.com.infotera.it.ezlink.model.Erro;

/**
 *
 * @author rafael
 */
public class ListRS extends Erro{

    private BookingListResponse bookingListResponse;

    public ListRS() {
    }

    public BookingListResponse getBookingListResponse() {
        return bookingListResponse;
    }

    public void setBookingListResponse(BookingListResponse bookingListResponse) {
        this.bookingListResponse = bookingListResponse;
    }

}
