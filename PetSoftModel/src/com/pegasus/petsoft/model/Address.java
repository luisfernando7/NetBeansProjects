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
public class Address {

    private int id;
    private String street;
    private String neighborhood;
    private String city;
    private int cep;
    private int number;
    private String complement;

    private enum uf {

        AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG,
        PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getneighborhood() {
        return neighborhood;
    }

    public void setneighborhood(String neiborhood) {
        this.neighborhood = neiborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

}
