/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pegasus.petsoft.model;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author luis.moraes
 */
public class Pets {
    
    private int id;
    private String name;
    private String breed;
    private GregorianCalendar bornDate;
    private Client owner;

    public Pets() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public GregorianCalendar getBornDate() {
        return bornDate;
    }

    public void setBornDate(GregorianCalendar bornDate) {
        this.bornDate = bornDate;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Pets(String name, String breed, Client owner) {
        this.name = name;
        this.breed = breed;
        this.owner = owner;
    }
    
    
}

