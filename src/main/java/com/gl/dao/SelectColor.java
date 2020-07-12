package com.gl.dao;
import com.gl.model.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SelectColor {

    public static void main(String[] args){
        SelectColor s=new SelectColor();
        Color color=s.selectColor("2");
        System.out.println("color id:"+color.getId());
        System.out.println("color name:"+color.getName());
        System.out.println("color colornum:"+color.getColornum());
    }


    public Color selectColor(String id){
        Connection conn = CBIRDao.getConnection();
        String sql = "select * from color where id = "+id;
        Color color=new Color();

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                color.setId(id);
                color.setName(rst.getString("name"));
                color.setColornum(rst.getString("colornum"));
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return color;
    }
}
