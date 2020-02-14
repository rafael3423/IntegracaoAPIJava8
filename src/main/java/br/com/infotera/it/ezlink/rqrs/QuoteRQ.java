/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.rqrs;

import java.util.List;

/**
 *
 * @author rafael
 */
public class QuoteRQ {

    private String searchToken;
    private List<String> roomId;

    public QuoteRQ(String searchToken, List<String> roomId) {
        this.searchToken = searchToken;
        this.roomId = roomId;
    }

    public String getSearchToken() {
        return searchToken;
    }

    public void setSearchToken(String searchToken) {
        this.searchToken = searchToken;
    }

    public List<String> getRoomId() {
        return roomId;
    }

    public void setRoomid(List<String> roomId) {
        this.roomId = roomId;
    }

}
