package com.gl.model;

public class Texture {
    private int id;
    private String name;
    private double[] E;
    private double[] Standard;

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

    public double[] getE() {
        return E;
    }

    public void setE(double[] e) {
        E = e;
    }

    public double[] getStandard() {
        return Standard;
    }

    public void setStandard(double[] standard) {
        Standard = standard;
    }
}
