package com.kuro4king.crud.model;

public class Region {
    private final Long id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Region(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id + ". " + name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
