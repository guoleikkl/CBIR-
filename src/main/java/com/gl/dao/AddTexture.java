package com.gl.dao;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.gl.model.Texture;
import com.gl.util.TextureUtil;

public class AddTexture {

    public static void main(String[] args) throws Exception {
        String path1;
        Texture texture;
        for (int i=0;i<9144;i++){
            path1 = "C:\\Users\\G_LEI\\Desktop\\images\\abc_";
            path1 = path1+String.valueOf(i+1)+".jpg";
            System.out.println(path1);
            texture=TextureUtil.GrayCal(path1);
            texture.setId(i+1);
            texture.setName("abc_"+String.valueOf(i+1)+".jpg");
            AddTexture.addTexture(texture);
        }
    }




    public static boolean addTexture(Texture texture){
        String sql = "INSERT INTO texture(id,name,E0,E1,E2,E3,S0,S1,S2,S3) VALUES(?,?,?,?,?,?,?,?,?,?);";
        Connection conn = CBIRDao.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,texture.getId());
            pst.setString(2,texture.getName());
            pst.setString(3,String.valueOf(texture.getE()[0]));
            pst.setString(4,String.valueOf(texture.getE()[1]));
            pst.setString(5,String.valueOf(texture.getE()[2]));
            pst.setString(6,String.valueOf(texture.getE()[3]));
            pst.setString(7,String.valueOf(texture.getStandard()[0]));
            pst.setString(8,String.valueOf(texture.getStandard()[1]));
            pst.setString(9,String.valueOf(texture.getStandard()[2]));
            pst.setString(10,String.valueOf(texture.getStandard()[3]));
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
