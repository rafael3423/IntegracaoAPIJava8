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
public class Room {

    private Integer adults;
    private Integer children;
    private List<Integer> childrenAge;
    private String roomId;
    private List<Pax> pax;
    private String roomName;
    private Board board;
    private Price price;
    private Boolean refundable;
    private CancellationPolicies cancellationPolicies;
    private String remarks;
   
    

    public Room() {
    }

    public Room(Integer adults, Integer children, List<Integer> childrenAge) {
        this.adults = adults;
        this.children = children;
        this.childrenAge = childrenAge;
    }

    public Room(String roomId, List<Pax> pax) {
        this.roomId = roomId;
        this.pax = pax;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public List<Integer> getChildrenAge() {
        return childrenAge;
    }

    public void setChildrenAge(List<Integer> childrenAge) {
        this.childrenAge = childrenAge;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<Pax> getPax() {
        return pax;
    }

    public void setPax(List<Pax> pax) {
        this.pax = pax;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Boolean getRefundable() {
        return refundable;
    }

    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    public CancellationPolicies getCancellationPolicies() {
        return cancellationPolicies;
    }

    public void setCancellationPolicies(CancellationPolicies cancellationPolicies) {
        this.cancellationPolicies = cancellationPolicies;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
