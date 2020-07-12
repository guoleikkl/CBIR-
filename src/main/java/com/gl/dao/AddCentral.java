package com.gl.dao;
import com.gl.util.Centralmoment;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.gl.model.Central;

import javax.imageio.ImageIO;

public class AddCentral {

    public static void main(String[] args) throws IOException {
        String path1 = "C:\\Users\\G_LEI\\Desktop\\images\\abc_";
        File f;
        FileInputStream fi;
        BufferedImage img;
        double[] hsv;
        Central central = new Central();
        AddCentral addCentral = new AddCentral();
        for(int i=0;i<9144;i++){
            path1 = "C:\\Users\\G_LEI\\Desktop\\images\\abc_";
            path1 = path1+String.valueOf(i+1)+".jpg";
            System.out.println(path1);
            f= new File(path1);
            fi = new FileInputStream(f);
            img = ImageIO.read(fi);
            hsv = Centralmoment.toHSV(img);

            central.setId(i+1);
            central.setName("abc_"+String.valueOf(i+1)+".jpg");
            central.setHsv(hsv);
            addCentral.addCentral(central);
            fi.close();
        }

    }


    public boolean addCentral(Central central){
        String sql = "INSERT INTO central(id,name,hsv0,hsv1,hsv2,hsv3,hsv4,hsv5,hsv6,hsv7,hsv8) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        Connection conn = CBIRDao.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,central.getId());
            pst.setString(2,central.getName());
            pst.setDouble(3,central.getHsv()[0]);
            pst.setDouble(4,central.getHsv()[1]);
            pst.setDouble(5,central.getHsv()[2]);
            pst.setDouble(6,central.getHsv()[3]);
            pst.setDouble(7,central.getHsv()[4]);
            pst.setDouble(8,central.getHsv()[5]);
            pst.setDouble(9,central.getHsv()[6]);
            pst.setDouble(10,central.getHsv()[7]);
            pst.setDouble(11,central.getHsv()[8]);

            int count = pst.executeUpdate();
            //int count = 1;
            pst.close();
            return count > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
