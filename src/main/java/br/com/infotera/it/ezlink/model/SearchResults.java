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
public class SearchResults {

    private String hotelId;
    private String hotelName;
    private String category;
    private Localization localization;
    private List<List<Room>> rooms;

    public SearchResults() {
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public List<List<Room>> getRooms() {
        return rooms;
    }

    public void setRooms(List<List<Room>> rooms) {
        this.rooms = rooms;
    }

}
