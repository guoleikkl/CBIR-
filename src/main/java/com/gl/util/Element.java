package com.gl.util;

public class Element implements Comparable{
    public int index;
    public double similar;

    public Element(int index, double similar) {
        this.index = index;
        this.similar = similar;
    }

    @Override
    public int compareTo(Object o) {
        double s = ((Element)o).similar;
        if(this.similar < s) {
            return -1;
        }else if(this.similar == s) {
            return 0;
        } else {
            return 1;
        }
    }

    public static void swap(Object[] obj, int i, int j) {
        if(i>=0 && i<obj.length && j>=0 && j<obj.length) {
            Object temp = obj[i];
            obj[i] = obj[j];
            obj[j] = temp;
        }
    }

    //从小到大排序
    public static void sort1(Comparable data[]) {
        int n = data.length;
        Comparable temp;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n-i-1; j++) {
                if(data[j].compareTo(data[j+1]) > 0) {
                    swap(data, j, j+1);
                }
            }
        }
    }
    //从大到小排序

    public static void sort2(Comparable data[]) {
        int n = data.length;
        Comparable temp;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n-i-1; j++) {
                if(data[j].compareTo(data[j+1]) < 0) {
                    swap(data, j, j+1);
                }
            }
        }
    }
}