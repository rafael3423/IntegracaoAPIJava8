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
public class SearchResponse {

    private Boolean searchCompleted;
    private String searchToken;
    private String tokenCreation;
    private Integer totalHotelsReturned;
    private Integer responseTime;
    private SearchQuery searchQuery;
    private List<SearchResults> searchResults;
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public SearchResponse() {
    }

    public Boolean getSearchCompleted() {
        return searchCompleted;
    }

    public void setSearchCompleted(Boolean searchCompleted) {
        this.searchCompleted = searchCompleted;
    }

    public String getSearchToken() {
        return searchToken;
    }

    public void setSearchToken(String searchToken) {
        this.searchToken = searchToken;
    }

    public String getTokenCreation() {
        return tokenCreation;
    }

    public void setTokenCreation(String tokenCreation) {
        this.tokenCreation = tokenCreation;
    }

    public Integer getTotalHotelsReturned() {
        return totalHotelsReturned;
    }

    public void setTotalHotelsReturned(Integer totalHotelsReturned) {
        this.totalHotelsReturned = totalHotelsReturned;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    public SearchQuery getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<SearchResults> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResults> searchResults) {
        this.searchResults = searchResults;
    }

}
