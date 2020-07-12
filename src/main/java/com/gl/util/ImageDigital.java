package com.gl.util;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;

public class ImageDigital {
    private static BufferedImage img = null;

    //读取图片

    public static BufferedImage readImg(String srcPath) throws IOException {
        //DataInputStream in = null;
        BufferedImage img1 = null;
        //try {
            FileInputStream fi1 = new FileInputStream(new File(srcPath));
           img1 = ImageIO.read(fi1);
            //in = new DataInputStream(new FileInputStream(srcPath));
            //img = ImageIO.read(new File(srcPath));
/*
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }*/
        return img1;
    }
    //读取图片
    public static int[] readImg2(String srcPath) throws IOException {
        img = ImageDigital.readImg(srcPath);
        int w = img.getWidth();
        int h = img.getHeight();
        int pix[] = new int[w*h];
        img.getRGB(0, 0, w, h, pix, 0, w);
        return pix;
    }

    //将图片写入磁盘
     //img 图像的BufferedImage对象
     //formatName 存储的文件格式
     //destPath 图像要保存的存储位置

    public static void writeImg(BufferedImage img, String formatName, String destPath) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(destPath);
            ImageIO.write(img, formatName, out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

     //将图片写入磁盘
     // pix 图像的矩阵数组
     //formatName存储的文件格式
     //destPath图像要保存的存储位置
    public static void writeImg(int pix[], int w, int h, String formatName,
                                String destPath)  {
        //img = new BufferedImage(w, h,ColorModel.getRGBdefault());
        img.setRGB(0, 0, w, h, pix, 0, w);
        writeImg(img, formatName, destPath);
    }
    //将图片转化成黑白灰度图片,返回的是一维的像素矩阵，而不是像素值
     //pix 保存图片像素
     //return 灰度矩阵
    public static int[] grayImage(int pix[], int w, int h) {
        ColorModel cm = ColorModel.getRGBdefault();
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                //0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue()
                pix[i*w + j] = (int) (0.299*cm.getRed(pix[i*w + j]) + 0.587*cm.getGreen(pix[i*w + j]) + 0.114*cm.getBlue(pix[i*w + j]) );

            }
        }
        return pix;
    }

    public static void main(String[] args) throws IOException {
        String srcPath="D:\\ima\\dfdf.jpg";
        String format = "jpg";
        String destPath="D:\\ima\\dfdf.jpg";
        ImageDigital.grayImage(srcPath,format,destPath);
    }
//将图片转化成黑白灰度图片


    public static void grayImage(String srcPath, String format, String destPath) throws IOException {
        img = readImg(srcPath);
        int w = img.getWidth();
        int h = img.getHeight();
        int pix[] = new int[w*h];
        img.getRGB(0, 0, w, h, pix, 0, w);
        int[] newPix = new int[w*h];
        ColorModel cm = ColorModel.getRGBdefault();
        int c = 0;
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                c = (int)(0.299*cm.getRed(pix[i*w + j]) + 0.587*cm.getGreen(pix[i*w + j]) + 0.114*cm.getBlue(pix[i*w + j]));
                newPix[i*w + j] = 255<<24 | c<<16 | c<<8 | c;
            }
        }
        img.setRGB(0, 0, w, h, newPix, 0, w);
        writeImg(img, format, destPath);
    }
}
