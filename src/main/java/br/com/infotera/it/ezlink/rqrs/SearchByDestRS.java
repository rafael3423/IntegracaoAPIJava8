/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.rqrs;

import br.com.infotera.it.ezlink.model.Erro;
import br.com.infotera.it.ezlink.model.SearchResponse;

/**
 *
 * @author rafael
 */
public class SearchByDestRS extends Erro{

    private SearchResponse searchResponse;

    public SearchByDestRS() {
    }

    public SearchResponse getSearchResponse() {
        return searchResponse;
    }

    public void setSearchResponse(SearchResponse searchResponse) {
        this.searchResponse = searchResponse;
    }

}
