package com.example.athreyaanand.insider;

/**
 * Created by athreyaanand on 7/29/17.
 */

public class Player {

    private String name;
    private Boolean isReady;

    Player(String name, Boolean isReady){
        this.name = name;
        this.isReady = isReady;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }
}
