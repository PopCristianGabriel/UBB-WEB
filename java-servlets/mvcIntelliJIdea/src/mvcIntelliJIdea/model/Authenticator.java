package mvcIntelliJIdea.model;

import java.sql.*;

/**
 * Created by forest on 16.12.2014.
 */
public class Authenticator {
    private Statement stmt;

    public Authenticator() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/labweb", "postgres", "dawd112");
            stmt = con.createStatement();
        } catch(Exception ex) {
            System.out.println("eroare la connect:"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String authenticate(String username, String password) {
        ResultSet rs;
        String result = "error";
        System.out.println(username+" "+password);
        try {
            rs = stmt.executeQuery("select * from users where username='"+username+"' and password='"+password+"'");
            if (rs.next()) {
                result = "success";
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}