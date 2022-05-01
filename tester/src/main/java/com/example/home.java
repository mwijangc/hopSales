package com.example;
import javax.servlet.RequestDispatcher;
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
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet(name = "home", value = "/home")
public class home extends HttpServlet {

    Connection con;
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    @Override
    public void init() throws ServletException {
        super.init();

        try{
            Class.forName(JDBC_DRIVER);
            // testpa is the database name!
            con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa", "root", "mysql_554");
            
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Statement stmt = null;
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        // default userlogin , replace with actual login eventually
        String username = "defaultAndy";
        session.setAttribute("currentUser", username);
        try{
            
            
            RequestDispatcher rd = req.getRequestDispatcher("head.html");
            rd.include(req,resp);
            
            
            writer.println("<body>");
            // nav
            rd = req.getRequestDispatcher("nav.html");            
            rd.include(req, resp);
            // products from database
            // send these to a servlet instead that produces product card html with the info
            rd = req.getRequestDispatcher("card");
            rd.include(req,resp);
            // need ratings with the orders

            writer.println("<h3> Welcome "+session.getAttribute("currentUser")+" !</h3>");
            writer.println("<p> Would you like to rate any of your past 5 orders?</p>");
            //put users last 5 orders
            rd = req.getRequestDispatcher("recent");
            rd.include(req,resp);

            // main body(static)
            rd= req.getRequestDispatcher("home.html");
			rd.include(req, resp);

            
            writer.println("</body> </html> ");

        }
        catch(Exception e){
            //Handle any other type of error
            writer.println(e);
        }finally{
            //finally block used to close resources
            try{
            if(stmt!=null)
                stmt.close();
            }catch(SQLException ignore) {}// nothing we can do
        } //end try
    }


}
