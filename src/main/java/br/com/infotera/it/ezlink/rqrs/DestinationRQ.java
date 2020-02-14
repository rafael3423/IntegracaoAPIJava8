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
public class DestinationRQ {

    private Integer skip;
    private Integer limit;
    private String order;
    private String countryISO2;
    private String createdUpdated;

    public DestinationRQ() {
    }

    public DestinationRQ(Integer skip, Integer limit, String order, String countryISO2, String createdUpdated) {
        this.skip = skip;
        this.limit = limit;
        this.order = order;
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
