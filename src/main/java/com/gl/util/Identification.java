package com.gl.util;

public interface Identification {

    public static final int DECIMAL_PALACE = 1000;

    public String getCharacteristic(String srcPath);

    //public String getCharacteristic2(String target);

    public float identification(String charac1, String charac2);
}