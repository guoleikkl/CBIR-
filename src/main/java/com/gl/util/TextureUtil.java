package com.gl.util;

import com.gl.model.Texture;
import org.springframework.remoting.caucho.SimpleBurlapServiceExporter;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;



public class TextureUtil {

    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\G_LEI\\Desktop\\images\\abc_1.jpg";
        Texture tt =TextureUtil.GrayCal(path);
        System.out.println("EEE1 "+tt.getE()[0]+"-"+tt.getE()[1]+"-"+tt.getE()[2]+"-"+tt.getE()[3]+"-");
        System.out.println("sss1 "+tt.getStandard()[0]+"-"+tt.getStandard()[1]+"-"+tt.getStandard()[2]+"-"+tt.getStandard()[3]+"-");
    }

    public static Texture GrayCal(String path) throws Exception
    {
        File file=new File(path);
        BufferedInputStream in=new BufferedInputStream(new FileInputStream(file.getPath()));
        BufferedImage img=ImageIO.read(in);
        int width=img.getWidth();
        int height=img.getHeight();
        int gray[][]=new int[width][height];   //存储从RGB转换为灰度的值
        int sum0=0,sum45=0,sum90=0,sum135=0;  // 分为0 度，45 度，90 度，135 度四个方向
        double[]con=new double[4];   //存四个方向计算后的对比度
        double[]ent=new double[4];    //存四个方向计算后的熵
        double[]corr=new double[4];   //存四个方向计算后的相关性
        double[]asm=new double[4];     //存四个方向计算后的能量
        double GLCM[][][]=new double[4][8][8]; //存储灰度共生矩阵内容
        double E[]=new double[4];   //存期望值
        double Standard[]=new double[4];   //存标准差
        //计算灰度
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                //先获取RGB的值
                int px=img.getRGB(i, j);
                double R=(px&0xff0000)>>16;
                double G=(px&0xff00)>>8;
                double B=(px&0xff);
                gray[i][j]=(int)(R*0.30+G*0.59+B*0.11)/32;
            }
        }
        //取像素间距为2
        int d=2;
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                //0°方向计算
                if(i+d>=0&&i+d<width)
                {
                    GLCM[0][gray[i][j]][gray[i+d][j]]+=1;
                    sum0++;
                }
                //45°计算
                if(i+d>=0&&i+d<width&&j+d>=0&&j+d<height)
                {
                    GLCM[1][gray[i][j]][gray[i+d][j+d]]+=1;
                    sum45++;
                }
                //90°计算
                if(j+d>=0&&j+d<height)
                {
                    GLCM[2][gray[i][j]][gray[i][j+d]]+=1;
                    sum90++;
                }
                if(i-d>=0&&i-d<width&&j+d>=0&&j+d<height)
                {
                    GLCM[3][gray[i][j]][gray[i-d][j+d]]+=1;
                    sum135++;
                }
            }
        }

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                GLCM[0][i][j]/=sum0;
                GLCM[1][i][j]/=sum45;
                GLCM[2][i][j]/=sum90;
                GLCM[3][i][j]/=sum135;
            }
        }

        //计算能量、相关度和熵
        for(int k=0;k<4;k++)  //表示四个方向
        {
            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                {
                    con[k]+=Math.pow((i-j), 2)*GLCM[k][i][j];
                    asm[k]+=Math.pow(GLCM[k][i][j], 2);
                    if(GLCM[k][i][j]!=0)
                        ent[k]-=GLCM[k][i][j]*Math.log(GLCM[k][i][j]);
                }
            }
        }

        //为了计算相关性，需要计算μx，μy等参数
        int ux[]=new int[4];
        int uy[]=new int[4];
        int cx[]=new int[4];
        int cy[]=new int[4];
        for(int k=0;k<4;k++)  //表示四个方向
        {
            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                {
                    ux[k]+=GLCM[k][i][j]*i;
                    uy[k]+=GLCM[k][i][j]*j;
                }
            }
        }

        for(int k=0;k<4;k++)  //表示四个方向
        {
            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                {
                    cx[k]+=Math.pow(i-ux[k], 2);
                    cy[k]+=Math.pow(j-uy[k], 2);
                    corr[k]+=i*j*GLCM[k][i][j];
                }
            }
            corr[k]=(corr[k]-ux[k]*uy[k])/(cx[k]*cy[k]);
        }

        //求每个矩阵的四个参数的期望和标准差
        for(int k=0;k<4;k++)  //表示四个方向
        {
            E[k]=(con[k]+asm[k]+ent[k]+corr[k])/4;
            Standard[k]=Math.sqrt(Math.pow(con[k]-E[k], 2)+Math.pow(asm[k]-E[k], 2)+Math.pow(ent[k]-E[k], 2)+Math.pow(corr[k]-E[k], 2));
        }
        in.close();

        Texture texture = new Texture();
        texture.setE(E);
        texture.setStandard(Standard);
        return texture;
    }

    public static double similar(double[] E1,double[] E2,double[] S1,double[] S2){
        double cosvalue = 1;
        double fenzi = 0;
        double fenmu1 = 0;
        double fenmu2= 0;
        for (int i = 0; i<4; i++){
            fenzi += E1[i]*E2[i];
            fenmu1 += E1[i]*E1[i];
            fenmu2 += E2[i]*E2[i];
        }
        for (int i = 0; i<4; i++){
            fenzi += S1[i]* S2[i];
            fenmu1 += S1[i]*S1[i];
            fenmu2 += S2[i]*S2[i];
        }
        fenmu1=Math.sqrt(fenmu1);
        fenmu2=Math.sqrt(fenmu2);
        cosvalue = fenzi/(fenmu1*fenmu2);
        return cosvalue;
    }
}