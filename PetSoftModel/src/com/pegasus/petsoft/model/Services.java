/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pegasus.petsoft.model;

/**
 *
 * @author luis.moraes
 */
public class Services {
    
    private int id;
    private String type;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Services(int id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }
    
    
}
