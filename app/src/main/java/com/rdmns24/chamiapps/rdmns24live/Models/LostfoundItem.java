package com.rdmns24.chamiapps.rdmns24live.Models;

public class LostfoundItem {

    private int id;
    private String name;
    private String categorie_name;

    public LostfoundItem(int id, String name, String categorie_name) {
        this.id = id;
        this.name = name;
        this.categorie_name = categorie_name;
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

    public String getCategorie_name() {
        return categorie_name;
    }

    public void setCategorie_name(String categorie_name) {
        this.categorie_name = categorie_name;
    }
}
