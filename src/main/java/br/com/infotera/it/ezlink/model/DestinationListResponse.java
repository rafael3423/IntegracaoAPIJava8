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
public class DestinationListResponse {

    private Integer totalAfterFilter;
    private Query query;
    private DestinationListResults destinationListResults;

    public DestinationListResponse() {
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

    public DestinationListResults getDestinationListResults() {
        return destinationListResults;
    }

    public void setDestinationListResults(DestinationListResults destinationListResults) {
        this.destinationListResults = destinationListResults;
    }

}
