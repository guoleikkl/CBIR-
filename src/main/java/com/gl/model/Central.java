package com.gl.model;

public class Central {
    private int id;
    private String name;
    private double[] hsv = new double[9];

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

    public double[] getHsv() {
        return hsv;
    }

    public void setHsv(double[] hsv) {
        this.hsv = hsv;
    }
}
