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
public class BookingListResponse {

    private Integer totalResults;
    private Query query;
    private List<BookingListResults> bookingListResults;

    public BookingListResponse() {
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<BookingListResults> getBookingListResults() {
        return bookingListResults;
    }

    public void setBookingListResults(List<BookingListResults> bookingListResults) {
        this.bookingListResults = bookingListResults;
    }

}
