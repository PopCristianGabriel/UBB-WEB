package mvcIntelliJIdea.controller;

/**
 * Created by forest on 16.12.2014.
 */


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcIntelliJIdea.model.Authenticator;
import mvcIntelliJIdea.model.User;



public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;

        Authenticator authenticator = new Authenticator();
        String result = authenticator.authenticate(username, password);
        if (result.equals("success")) {
            rd = request.getRequestDispatcher("/succes.jsp");
            User user = new User(username, password);
            request.setAttribute("user", user);
            // Normally, here we should set the "user" attribute on the session like this:
            // HttpSession session = request.getSession();
            // session.setAttribute("user", user);
            // .. and then, in all JSP/Servlet pages we should check if the "user" attribute exists in the session
            // and if not, we should return/exit the method:
            // HttpSession session = request.getSession();
            // String user = session.getAttribute("user");
            // if (user==null || user.equals("")) {
            //        return;
            // }
        } else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        rd.forward(request, response);
    }



}