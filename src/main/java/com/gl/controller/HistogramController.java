package com.gl.controller;
import com.gl.dao.SelectHistogram;
import com.gl.util.HistogramIdentification;
import com.gl.util.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public class HistogramController {
    public ModelAndView histogramCon() throws IOException {
        ModelAndView mv = new ModelAndView();
        //ImageSimilar imageSimilar = new ImageSimilar();
        String str1 = "C:\\Users\\G_LEI\\Desktop\\uploadimg\\dfdf.jpg";
        String str2 = "C:\\Users\\G_LEI\\Desktop\\images\\abc_";
        int num = 9144;
        HistogramIdentification ident1 = new HistogramIdentification();
        //Identification ident2 = new PHash();
        String charact1 = ident1.getCharacteristic(str1);
        int characNum = 1;
        String charact2 = new String();
        Element[] element = new Element[num];
        SelectHistogram selectHistogram = new SelectHistogram();
        float f = 0f;
        for (int j = 0; j < num; j++) {
            f=0f;
            charact2 = selectHistogram.selectHistogram(String.valueOf(j+1)).getCharact();
            f += ident1.identification(charact1, charact2);
            f = (int) ((f / characNum) * 1000) / 1000f;
            System.out.println("-"+String.valueOf(j)+"--------" + f);
            element[j] = new Element((j + 1), 1000 * f);
        }
        Element.sort2(element);
        int[] number = new int[9];
        String paths;
        for(int i=0; i<9; i++) {
            System.out.println("num-- "+element[i].index+"----"+element[i].similar);
            paths="/images/abc_"+String.valueOf(element[i].index)+".jpg";

            mv.addObject("path"+String.valueOf(i+1),paths);
        }
        String path0 = "/uploadimg/dfdf.jpg";
        mv.addObject("path",path0);
        mv.setViewName("success1");
        return mv;
    }
}

