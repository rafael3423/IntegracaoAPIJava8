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
public class RoomBooking {

    private Integer id;
    private String checkIn;
    private String checkOut;
    private String status;
    private String name;
    private String board;
    private Price price;
    private Pax pax;
    private CancellationPolicies cancellationPolicies;
    private String remarks;

    public RoomBooking() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Pax getPax() {
        return pax;
    }

    public void setPax(Pax pax) {
        this.pax = pax;
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
