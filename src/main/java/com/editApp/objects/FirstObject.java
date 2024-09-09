package com.editApp.objects;

public class FirstObject {

    private String name;
    private int id;
    // друшие поля по Джэйсону
    // some for new branch

    public FirstObject() {
    }

    public FirstObject(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public void inc() {
        id++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
