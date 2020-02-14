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
public class CancellationPolicies {

    private Boolean refundable;
    private List<Penalt> penalties;

    public CancellationPolicies() {
    }

    public List<Penalt> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<Penalt> penalties) {
        this.penalties = penalties;
    }

    public Boolean getRefundable() {
        return refundable;
    }

    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

}
