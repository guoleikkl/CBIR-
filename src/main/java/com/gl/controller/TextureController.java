package com.gl.controller;
import com.gl.dao.SelectTexture;
import com.gl.model.Texture;
import com.gl.util.Element;
import com.gl.util.TextureUtil;
import org.springframework.web.servlet.ModelAndView;

public class TextureController {
    public ModelAndView tc() throws Exception {
        ModelAndView mv = new ModelAndView();
        String str1 = "C:\\Users\\G_LEI\\Desktop\\uploadimg\\dfdf.jpg";
        String str2 = "C:\\Users\\G_LEI\\Desktop\\images\\abc_";
        int num = 9144;
        Texture tt1 = TextureUtil.GrayCal(str1);
        Element[] element= new Element[num];
        Texture tt2;
        double simi;
        for(int i = 0;i<num;i++){
            tt2 = SelectTexture.selectTexture(i+1);
            simi = TextureUtil.similar(tt1.getE(),tt2.getE(),tt1.getStandard(),tt2.getStandard());
            System.out.println("-"+String.valueOf(i)+"--------"+String.valueOf(simi));
            element[i]=new Element((i+1),simi);
        }
        Element.sort2(element);
        String paths;
        for(int i=0; i<9; i++) {
            System.out.println("num-- "+element[i].index+"---------"+element[i].similar);
            paths="/images/abc_"+String.valueOf(element[i].index)+".jpg";
            mv.addObject("path"+String.valueOf(i+1),paths);
        }
        String path0 = "/uploadimg/dfdf.jpg";
        mv.addObject("path",path0);
        mv.setViewName("success4");
        return mv;
    }
}
