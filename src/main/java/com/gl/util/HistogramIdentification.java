package com.gl.util;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HistogramIdentification {
    //表示R、G、B的位数
    public static final int GRAYBIT = 4;
    public HistogramIdentification() {
    }

    //求一维的灰度直方图
    public String getHistogram(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        int series = (int) Math.pow(2, GRAYBIT);	//16   GRAYBIT=4;用12位的int表示灰度值，前4位表示red,中间4们表示green,后面4位表示blue
        int greyScope = 256/series;//16
        float[] hist = new float[series*series*series]; //4096
        int r, g, b, index;
        int pix[] = new int[w*h];
        pix = img.getRGB(0, 0, w, h, pix, 0, w);//返回默认RGB颜色模型中的整数像素数组
        for(int i=0; i<w*h; i++) {
            r = pix[i]>>16 & 0xff;
            r = r/greyScope;
            g = pix[i]>>8 & 0xff;
            g = g/greyScope;
            b = pix[i] & 0xff;
            b = b/greyScope;
            index = r<<(2*GRAYBIT) | g<<GRAYBIT | b;
            hist[index] ++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<hist.length; i++) {
            hist[i] = hist[i]/(w*h);
            hist[i] = (float)Math.round(hist[i]*1000)/1000;
            sb.append(hist[i]);
            if(i != hist.length-1) {
                sb.append("_");
            }
        }
        return sb.toString();
    }

    //基于一维灰度直方图特征的图像匹配
    public static float identification(float[] histR, float[] histD) {
        float p = (float) 0.0;
        for(int i=0; i<histR.length; i++) {
            p += Math.sqrt(histR[i]*histD[i]);
        }
        p = (float)Math.round(p*1000)/1000;
        return p;
    }

    //将得到的字符串分割，得到特征值
    private float[] convertFloat(String charact ) {
        String strs[] = charact.split("_");
        float histo[] = new float[strs.length];
        for(int i=0; i<strs.length; i++) {
            histo[i] = Float.parseFloat(strs[i]);
        }
        return histo;
    }
    public float identification(String charac1, String charc2) {
        float[] histR = convertFloat(charac1);
        float[] histD = convertFloat(charc2);
        return identification(histR, histD);
    }
    public String getCharacteristic(String srcPath) throws IOException {
        BufferedImage img = ImageDigital.readImg(srcPath);
        String his = getHistogram(img);
        return his;
    }


}
