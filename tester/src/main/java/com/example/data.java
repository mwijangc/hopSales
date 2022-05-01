package com.example;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "data", value = "/data")
public class data extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            // testpa is the database name!
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa", "root", "mysql_554");
            Statement stmt = con.createStatement();
            //email is the name of the table!
            String sql = "SELECT name,email FROM email";
            ResultSet rs = stmt.executeQuery(sql);

            PrintWriter writer = resp.getWriter();
            writer.println("<html> <body>");
            writer.println("bruh"+ req.getParameter("param1"));
            // until the rows exist/stop existing lol
            while(rs.next()){                  
                String name = rs.getString("name");
                String email = rs.getString("email");
                writer.println("Name: "+ name + " Email: " + email);
                writer.println("Hello");
            }
            writer.println("</body> </html> ");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
