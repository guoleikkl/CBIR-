package com.gl.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class Upload {
    @RequestMapping("search")
    public ModelAndView search(String choice, MultipartFile image, HttpServletRequest req) throws Exception, IOException {
        ModelAndView mv = new ModelAndView();
        if(!image.isEmpty()) {
            System.out.println(image.getContentType());//  image/jpeg   获取上传文件的类型
            //System.out.println(image.getName());//image  获取file标签的name属性  <input type="file" name="image" >
            System.out.println(image.getOriginalFilename());//1.jpg   获取上传文件的名称
            String contentType = image.getContentType();
            ServletContext context = req.getServletContext();
            String realPath = context.getRealPath("/upload");//获取本地存储位置的绝对路径
            String filename = "dfdf.jpg";
            String path="C:\\Users\\G_LEI\\Desktop\\uploadimg";
            File f= new File(path, filename);
            if(!f.exists()){
                image.transferTo(f);//将上传的文件存储到指定位置
            }
            else{
                boolean bb = f.delete();//删除文件
                System.out.println("bbbb--------------------"+bb);
                 image.transferTo(f);//将上传的文件存储到指定位置
            }

            //根据用户选择的检索方式运行
            if(choice.equals("a")){
                HistogramController histogramController = new HistogramController();
                mv = histogramController.histogramCon();
            }
            else if(choice.equals("b")){
                ColorController cc = new ColorController();
                mv = cc.colorC();
            }
            else if(choice.equals("c")){
                CentralmomentController cc = new CentralmomentController();
                mv = cc.cc();
            }
            else if(choice.equals("d")){
                TextureController tc = new TextureController();
                mv=tc.tc();
            }
        }else{
            System.out.println("没有上传图片!");
        }
        return mv;
    }
}
