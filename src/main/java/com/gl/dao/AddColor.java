package com.gl.dao;
import com.gl.dao.CBIRDao;
import com.gl.model.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class AddColor {

    public boolean addColor(Color color){
        String sql = "INSERT INTO color(id,name,colornum) VALUES(?,?,?);";
        Connection conn = CBIRDao.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, color.getId());
            pst.setString(2, color.getName());
            pst.setString(3, color.getColornum());

            int count = pst.executeUpdate();
            pst.close();
            return count > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
