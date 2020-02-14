/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.rqrs;

/**
 *
 * @author rafael
 */
public class CancelRQ {

    private Integer bookingId;
    private Integer roomId;
    private Boolean all;

    public CancelRQ() {
    }

    public CancelRQ(Integer bookingId, Integer roomId, Boolean all) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.all = all;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

}
