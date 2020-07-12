package com.gl.dao;
import com.gl.dao.CBIRDao;
import com.gl.model.Histogram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SelectHistogram {


    public Histogram selectHistogram(String id){
        Connection conn = CBIRDao.getConnection();
        String sql = "select * from histogram where id = "+id;
        Histogram histogram = new Histogram();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                histogram.setId(id);
                histogram.setName(rst.getString("name"));
                histogram.setCharact(rst.getString("charact"));
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return histogram;

    }
}
