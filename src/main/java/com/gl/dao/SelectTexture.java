package com.gl.dao;

import com.gl.model.Texture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SelectTexture {
    public static Texture selectTexture(int id){
        Connection conn = CBIRDao.getConnection();
        String sql = "select * from texture where id = "+String.valueOf(id);
        Texture texture = new Texture();
        double[] E = new double[4];
        double[] Standard = new double[4];
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                texture.setId(id);
                texture.setName(rst.getString("name"));
                for(int i=0;i<4;i++){
                    E[i]=Double.valueOf(rst.getString("E"+String.valueOf(i)));
                }
                texture.setE(E);
                for(int i=0;i<4;i++){
                    Standard[i]=Double.valueOf(rst.getString("S"+String.valueOf(i)));
                }
                texture.setStandard(Standard);
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return texture;
    }











}
