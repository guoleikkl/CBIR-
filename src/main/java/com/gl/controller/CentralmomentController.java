package com.gl.controller;
import com.gl.util.Centralmoment;
import com.gl.util.Element;
import com.gl.dao.SelectCentral;
import org.springframework.web.servlet.ModelAndView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CentralmomentController {
    public ModelAndView cc() throws IOException {
        ModelAndView mv = new ModelAndView();
        String str1 = "C:\\Users\\G_LEI\\Desktop\\uploadimg\\dfdf.jpg";
        String str2 = "C:\\Users\\G_LEI\\Desktop\\images\\abc_";
        int num = 9144;
        FileInputStream fi1 = new FileInputStream(new File(str1));
        BufferedImage img1 = ImageIO.read(fi1);
        double[] hsv1 = Centralmoment.toHSV(img1);
        double metri;
        fi1.close();
        Element[] element= new Element[num];
        for(int i=0;i<num;i++){
            double[] hsv2 = SelectCentral.seleceCentralHsv(i);
            metri = Centralmoment.Metri(hsv1,hsv2);
            System.out.println("-"+String.valueOf(i)+"-----"+String.valueOf(metri));
            element[i] = new Element((i+1),metri);
        }
        Element.sort1(element);
        int[] number = new int[9];
        String paths;
        for(int i=0; i<9; i++) {
            System.out.println("num-- "+element[i].index+"----"+element[i].similar);
            paths="/images/abc_"+String.valueOf(element[i].index-1)+".jpg";
            mv.addObject("path"+String.valueOf(i+1),paths);
        }
        String path0 = "/uploadimg/dfdf.jpg";
        mv.addObject("path",path0);
        mv.setViewName("success3");
        return mv;
    }












}
