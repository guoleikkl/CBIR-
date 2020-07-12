package com.gl.util;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageSimilar {
    //搜索图片，求图片的相似度

    public static void searchImage(int n, String str1, String str2, HistogramIdentification ident1) throws IOException {
        int characNum = 1;
        String charact1 = ident1.getCharacteristic(str1);
        String charact2= new String();
        Element[] element = new Element[n];
        //获得str1中图片的特征值
        for(int j=0; j<n; j++) {
            float f = 0f;
            charact2 = ident1.getCharacteristic(str2 + (j+1) + ".jpg");
            f += ident1.identification(charact1, charact2);
            f = (int)((f/characNum)*1000)/1000f;
            element[j] = new Element((j+1), 1000*f);
        }
        Element.sort2(element);
        BufferedImage img = null;
        String path3 = "C:\\Users\\G_LEI\\Desktop\\result\\";
        int[] num = new int[9];
        for(int i=0; i<9; i++) {
            img = ImageDigital.readImg(str2 + element[i].index + ".jpg");
            ImageDigital.writeImg(img, "jpg", path3 + "T"  + "_" + String.valueOf(i) + ".jpg");
            num[i]=Integer.valueOf(element[i].index);
        }

    }


    public static void main(String[] args) throws IOException {
        URL url = ImageSimilar.class.getResource("/image");
        //String str1 = url.getPath() + "/person.jpg";
        //String str2 = url.getPath() + "/person";
        String str1 = "C:\\Users\\G_LEI\\Desktop\\images\\abc_5574.jpg";
        String str2 = "C:\\Users\\G_LEI\\Desktop\\images\\abc_";
        int n = 7700;
        HistogramIdentification ident1 = new HistogramIdentification();
        //Identification ident2 = new PHash();
        searchImage(n, str1, str2, ident1);//ident1,    , ident2
    }

}