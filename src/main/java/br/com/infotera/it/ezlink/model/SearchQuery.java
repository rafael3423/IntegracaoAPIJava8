/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.model;

import java.util.List;

/**
 *
 * @author rafael
 */
public class SearchQuery {

    private String checkIn;
    private String checkOut;
    private String destinationId;
    private String nationality;
    private Integer timeout;
    private Boolean hotelInfo;
    private List<Room> rooms;
    private List<String> holdeIds;

    public SearchQuery() {
    }

    public SearchQuery(String checkIn, String checkOut, String destinationId, String nationality, Integer timeout, Boolean hotelInfo, List<Room> rooms) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.destinationId = destinationId;
        this.nationality = nationality;
        this.timeout = timeout;
        this.hotelInfo = hotelInfo;
        this.rooms = rooms;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Boolean getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(Boolean hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<String> getHoldeIds() {
        return holdeIds;
    }

    public void setHoldeIds(List<String> holdeIds) {
        this.holdeIds = holdeIds;
    }

}
