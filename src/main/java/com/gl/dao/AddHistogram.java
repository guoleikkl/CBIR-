package com.gl.dao;
import com.gl.model.Histogram;
import com.gl.util.Element;
import com.gl.util.HistogramIdentification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class AddHistogram {

    public boolean addHistogram(Histogram histogram) {
        String sql = "INSERT INTO histogram(id,name,charact) VALUES(?,?,?);";
        Connection conn = CBIRDao.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, histogram.getId());
            pst.setString(2, histogram.getName());
            pst.setString(3, histogram.getCharact());

            int count = pst.executeUpdate();
            //int count = 1;
            pst.close();
            return count > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void main(String[] args) throws IOException {
        int character = 1;
        String charact = new String();
        int n=9144;
        Element[] element = new Element[n];
        Histogram histogram = new Histogram();
        AddHistogram addHistogram=new AddHistogram();
        String str = "C:\\Users\\G_LEI\\Desktop\\images\\abc_";
        HistogramIdentification ident1 = new HistogramIdentification();
        for(int j=0; j<n; j++) {
            float f = 0f;
            System.out.println(j);
            String path = str + (j+1) + ".jpg";
            charact = ident1.getCharacteristic(path);
            histogram.setId(String.valueOf(j+1));
            histogram.setName("abc_"+String.valueOf(j+1)+".jpg");
            histogram.setCharact(charact);
            addHistogram.addHistogram(histogram);
        }

    }














}
