/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.rqrs;

import br.com.infotera.it.ezlink.model.DestinationListResponse;

/**
 *
 * @author rafael
 */
public class DestinationRS {

    private DestinationListResponse destinationListResponse;

    public DestinationRS() {
    }

    public DestinationListResponse getDestinationListResponse() {
        return destinationListResponse;
    }

    public void setDestinationListResponse(DestinationListResponse destinationListResponse) {
        this.destinationListResponse = destinationListResponse;
    }

}
