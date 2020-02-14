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
public class HotelsRQ {

    private Integer skip;
    private Integer limit;
    private String order;
    private String destinationId;
    private String countryISO2;
    private String createdUpdated;
    public HotelsRQ hotelsRQ;

    public HotelsRQ() {
    }

    public HotelsRQ(Integer skip, Integer limit, String order, String destinationId, String countryISO2, String createdUpdated) {
        this.skip = skip;
        this.limit = limit;
        this.order = order;
        this.destinationId = destinationId;
        this.countryISO2 = countryISO2;
        this.createdUpdated = createdUpdated;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }

    public String getCreatedUpdated() {
        return createdUpdated;
    }

    public void setCreatedUpdated(String createdUpdated) {
        this.createdUpdated = createdUpdated;
    }

}
