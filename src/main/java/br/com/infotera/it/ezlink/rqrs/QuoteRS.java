/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.rqrs;

import br.com.infotera.it.ezlink.model.Erro;
import br.com.infotera.it.ezlink.model.QuoteResponse;

/**
 *
 * @author rafael
 */
public class QuoteRS extends Erro{

    private QuoteResponse quoteResponse;

    public QuoteRS() {
    }

    public QuoteResponse getQuoteResponse() {
        return quoteResponse;
    }

    public void setQuoteResponse(QuoteResponse quoteResponse) {
        this.quoteResponse = quoteResponse;
    }

}
