package com.example.iterepi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Seller implements Serializable {

    private String id;
    private String name;
    private String nit;
    private String email;
    private String logo;
    private HashMap<String, String> salesID;
    private HashMap<String,Place> places;
    private HashMap<String, Card> cards;

    public Seller() {
    }

    public Seller(String id, String name, String nit, String email, String logo, HashMap<String, String> salesID, HashMap<String, Place> places, HashMap<String, Card> cards) {
        this.id = id;
        this.name = name;
        this.nit = nit;
        this.email = email;
        this.logo = logo;
        this.salesID = salesID;
        this.places = places;
        this.cards = cards;
    }

    public HashMap<String, String> getSalesID() {
        return salesID;
    }

    public void setSalesID(HashMap<String, String> salesID) {
        this.salesID = salesID;
    }

    public HashMap<String, Card> getCards() {
        return cards;
    }

    public void setCards(HashMap<String, Card> cards) {
        this.cards = cards;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public HashMap<String,Place>getPlaces() {
        return places;
    }

    public void setPlaces(HashMap<String,Place> places) {
        this.places = places;
    }

    public int numPlaces(){
        if(places==null){
            return 0;
        }else{
            return places.keySet().size();
        }
    }
}
