package com.gl.dao;
import java.sql.Connection;
import java.sql.DriverManager;
public class CBIRDao {
    private static String url = "jdbc:mysql://localhost/cbir?useSSL=FALSE&serverTimezone=UTC";// 数据库地址
    private static String userName = "root"; // 数据库用户名
    private static String passWord = "guolei"; // 数据库密码
    private static Connection conn = null;

    public static Connection getConnection() {
        if (null == conn) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, userName, passWord);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    public static void main(String[] args) {
        System.out.println(getConnection());
    }

}
