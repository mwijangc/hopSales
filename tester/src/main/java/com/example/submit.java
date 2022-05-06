package com.example;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;

/* 
*****
submit.java is called by recents to handle the rating system.
*
submit needs a better 'id' system for backend insertion .
The session is important, as the currentUser is a parameter sent in.
Might want to prettify/convert this into a modal rather than a whole page redirect.
As for a clear usage, this rating system is called specifically when
the Send button in recents.java is used.
Future iteration might just send to the backend onChange/mouseEnd/mouseUp etc.
The button solidifies/uses less resources for now.

*/

@WebServlet(name = "submit", value = "/submit")
public class submit extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html> ");
        String cssLink=" <link rel='stylesheet' href='index.css'>";
        writer.println("<head><title>Form</title>"+cssLink+"</head>");            
        writer.println("<body>");
        writer.println("<header>");
        writer.println("<nav class='navbar' id='navBar'>");
        writer.println("<a class ='navlink navbarLogo' href='/tester'>");
        writer.println(" <img height = '25px' width='25px' src='images/fwog_logo.svg'> </a>");
        writer.println("<a class='navlink' href='cart' >");
        writer.println("Cart");
        writer.println("</a>");
        
        writer.println("</a>");           
        writer.println("</nav> </header>");
        // writer.println("<html>");
        // writer.println("<header>"+"<link rel='stylesheet' href='rating.css'></link>"   +"</header>");
        // writer.println("<body>");
        // RequestDispatcher rd = req.getRequestDispatcher("/rating.html");
        // rd.include(req,resp);
        //String name = req.getParameter("name");
        //String email = req.getParameter("email");
        //writer.println("Name: "+ name + " Email: " + email);
        HttpSession session = req.getSession();
        // default userlogin , replace with actual login eventually
        
        String currUser = (String)session.getAttribute("currentUser");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // testpa is the database name!
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa", dbCredentials.USER , dbCredentials.PASS);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO orders VALUES (?,?,?,?)");
            // stmt.setInt(1, Integer.valueOf(req.getParameter("productID")));
            stmt.setInt(1, Integer.valueOf(req.getParameter("frog_id")));
            stmt.setString(2, currUser);
            stmt.setInt(3, Integer.valueOf(req.getParameter("frog_id")));
            stmt.setFloat(4, Float.valueOf(req.getParameter("rating")));
            //get the (generate an order id num) username from session, the id of the item from getParam, and the rating from input getParam,
            // stmt.setString(2, req.getParameter("firstName"));
            // stmt.setString(3, req.getParameter("lastName"));
            
            stmt.executeUpdate();
            stmt.close();
            con.close();
            
            // PrintWriter writer = resp.getWriter();
            
            // writer.println("</body> </html> ");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            // writer.println("class issue");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // writer.println("sql issue");
        }
    

        String rating_val = ""+ req.getParameter("rating");
        // writer.println("Sent a rating of " + rating_val+ "for product id:" +req.getParameter("frog_id")+", the "+req.getParameter("frog_name"));
        writer.println("<div><h3>"+"Sent a rating of " + rating_val+ "! for product id: " +req.getParameter("frog_id")+" , the "+req.getParameter("frog_name")+"!  </h3></div>");

        //ideally just use a toast tab and redirect/send back to homepage....
        // RequestDispatcher rd = req.getRequestDispatcher("home");
        //     rd.forward(req,resp);
        // writer.println("</body> </html> ");
    }
}

