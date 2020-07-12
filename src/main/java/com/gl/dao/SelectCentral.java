package com.gl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectCentral {

    public static void main(String[] args){
        double[] dd = SelectCentral.seleceCentralHsv(8);

        for(int i=0;i<dd.length;i++){

            System.out.println(String.valueOf(i)+"-----------------"+String.valueOf(dd[i]));
        }
    }



    public static double[] seleceCentralHsv(int id){
        Connection conn = CBIRDao.getConnection();
        String sql = "select * from central where id = "+String.valueOf(id);
        //Central central = new Central();
        double[] hsv = new double[9];
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                for(int i=0;i<9;i++){
                    hsv[i]=rst.getDouble("hsv"+String.valueOf(i));
                }
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hsv;
    }
}
