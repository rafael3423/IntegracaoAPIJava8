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
public class ListRQ {

    private String createdFrom;
    private String createdTo;

    public ListRQ(String createdFrom, String createdTo) {
        this.createdFrom = createdFrom;
        this.createdTo = createdTo;
    }

    public ListRQ() {
    }

    public String getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(String createdFrom) {
        this.createdFrom = createdFrom;
    }

    public String getCreatedTo() {
        return createdTo;
    }

    public void setCreatedTo(String createdTo) {
        this.createdTo = createdTo;
    }

}
