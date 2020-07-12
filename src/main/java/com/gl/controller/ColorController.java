package com.gl.controller;
import com.gl.model.Color;
import com.gl.util.ColorUtil;
import com.gl.dao.SelectColor;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ColorController {
    public ModelAndView colorC() throws Exception {
        String path="C:\\Users\\G_LEI\\Desktop\\uploadimg";
        ModelAndView mv = new ModelAndView();
        ColorUtil colorUtil = new ColorUtil();
        String colornum;
        FileInputStream fi = new FileInputStream(new File(path+"\\dfdf.jpg"));
        colornum = colorUtil.getHash(fi);
        int[] distance = new int[9144];
        String path2;
        SelectColor selectColor = new SelectColor();
        Color color;
        Map<String,Integer> map = new HashMap<>();
        for(int i =0;i<7708;i++){
            color = selectColor.selectColor(String.valueOf(i+1));
            System.out.print("i=");
            System.out.print(i+1);
            System.out.print("    num="+color.getColornum());
            distance[i]=colorUtil.distance(colornum,color.getColornum());
            map.put(String.valueOf(i),distance[i]);
            System.out.println("     distance="+distance[i]);
        }
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            list.add(entry); //将map中的元素放入list中
        }
        list.sort(new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
            //逆序（从大到小）排列，正序为“return o1.getValue()-o2.getValue”
        });

        String number;
        String paths;
        for(int i=0;i<9;i++){
            number=String.valueOf(Integer.valueOf(list.get(i).getKey())+1);
            System.out.println("nnnnn========="+number+"-----"+list.get(i).getValue());
            paths="/images/abc_"+number+".jpg";
            mv.addObject("path"+String.valueOf(i+1),paths);
        }
        String path0 = "/uploadimg/dfdf.jpg";
        mv.addObject("path",path0);
        mv.setViewName("success2");
        fi.close();
        return mv;
    }
}
