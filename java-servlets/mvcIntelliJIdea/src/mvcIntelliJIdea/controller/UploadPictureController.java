package mvcIntelliJIdea.controller;

import mvcIntelliJIdea.model.Picture;
import mvcIntelliJIdea.model.User;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UploadPictureController extends HttpServlet {
    ArrayList<Picture> pictures = new ArrayList<Picture>();
    Connection con;
    int id;
    boolean showAll = true;
    boolean showN = false;
    public UploadPictureController() throws SQLException, ClassNotFoundException {
        super();
        get_data_from_db();


    }

    private void get_data_from_db() throws ClassNotFoundException, SQLException {
        Class.forName("org.gjt.mm.mysql.Driver");
        this.con = DriverManager.getConnection("jdbc:postgresql://localhost/labweb", "postgres", "dawd112");
        Statement stmt =this.con.createStatement();
        ResultSet rs;
        id = 0;
        rs = stmt.executeQuery("Select * from Pictures");
        while(rs.next()){
            id++;
            Picture picture = new Picture(id,rs.getString("username"),rs.getString("file"),rs.getInt("score"));
            this.pictures.add(picture);

        }
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/succes.jsp");

        if(request.getParameter("N") != null && !request.getParameter("N").equals("")){
            int n = Integer.parseInt(request.getParameter("N"));
            request.setAttribute("size",n);
            request.setAttribute("showAll",false);
            request.setAttribute("showN",true);
             Collections.sort(this.pictures);
            request.setAttribute("sorted", this.pictures);
        }
        else if(request.getParameter("voted") != null && !request.getParameter("voted").equals("")){
            request.setAttribute("showAll",true);
            request.setAttribute("showN",false);
            String voted = request.getParameter("voted");
            int rate = Integer.parseInt(request.getParameter("rate"));
            String voter = request.getParameter("username");
            User user = new User(voter, "nuconteaza");
            request.setAttribute("user", user);
                for(Picture picture : this.pictures){
                    if(picture.get_id() == Integer.parseInt(voted) && !picture.get_name().equals(voter))
                        picture.score += rate;
            }

        }
        else {
            request.setAttribute("showAll",true);
            request.setAttribute("showN",false);
            String username = request.getParameter("flag");
            String file = request.getParameter("img");
            id++;
            Picture picture = new Picture(id,username, file);
            this.pictures.add(picture);
            request.setAttribute("pictures", pictures);
            User user = new User(username, "nuconteaza");
            request.setAttribute("user", user);

            Statement stmt = null;
            try {
                stmt = this.con.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                stmt = this.con.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ResultSet rs;
            try {
                String q = "''''''";
                PreparedStatement statement = this.con.prepareStatement("insert into pictures values(?,?,?)");
                statement.setString(1, picture.get_name());
                statement.setString(2, picture.get_file());
                statement.setInt(3, picture.get_score());
                statement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        rd.forward(request, response);


    }
}

