/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.rqrs;

import br.com.infotera.it.ezlink.model.Room;
import java.util.List;

/**
 *
 * @author rafael
 */
public class BookRQ {

    private String searchToken;
    private String customerBookingId;
    private List<Room> rooms;

    public BookRQ() {
    }

    public BookRQ(String searchToken, String custumerBookingId, List<Room> rooms) {
        this.searchToken = searchToken;
        this.customerBookingId = custumerBookingId;
        this.rooms = rooms;
    }

    public String getSearchToken() {
        return searchToken;
    }

    public void setSearchToken(String searchToken) {
        this.searchToken = searchToken;
    }

    public String getCustomerBookingId() {
        return customerBookingId;
    }

    public void setCustomerBookingId(String custumerBookingId) {
        this.customerBookingId = custumerBookingId;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}
