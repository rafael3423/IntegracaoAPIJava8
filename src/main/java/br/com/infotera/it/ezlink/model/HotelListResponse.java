/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.model;

/**
 *
 * @author rafael
 */
public class HotelListResponse {

    private Integer totalAfterFilter;
    private Query query;
    private HotelListResults hotelListResults;

    public HotelListResponse() {
    }

    public Integer getTotalAfterFilter() {
        return totalAfterFilter;
    }

    public void setTotalAfterFilter(Integer totalAfterFilter) {
        this.totalAfterFilter = totalAfterFilter;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public HotelListResults getHotelListResults() {
        return hotelListResults;
    }

    public void setHotelListResults(HotelListResults hotelListResults) {
        this.hotelListResults = hotelListResults;
    }

}
