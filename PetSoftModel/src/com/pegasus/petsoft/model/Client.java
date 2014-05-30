/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pegasus.petsoft.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author marina.silva
 */
public class Client {
    
    private int id;
    private String name;
    private GregorianCalendar bornDate;
    private Address address;
    private int phone;
    private int celphone;
    private ArrayList<Pets> pets;

    public Client() {
        
    }
    
//    @Override
   /* public String toString(){
        return name;
    }*/
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

    public GregorianCalendar getBornDate() {
        return bornDate;
    }

    public void setBornDate(GregorianCalendar bornDate) {
        this.bornDate = bornDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCelphone() {
        return celphone;
    }

    public void setCelphone(int celphone) {
        this.celphone = celphone;
    }

    public ArrayList<Pets> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pets> pets) {
        this.pets = pets;
    }

    public Client(int id, String name, Address address, int phone, ArrayList<Pets> pets) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.pets = pets;
    }    
}
