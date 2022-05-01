package com.example;
import javax.servlet.RequestDispatcher;
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

@WebServlet(name = "form", value = "/form")

public class form extends HttpServlet {

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
        
        try{
            stmt = con.createStatement();
            //email is the name of the table!
            writer.println("<html> ");
            String cssLink=" <link rel='stylesheet' href='index.css'>";
            writer.println("<head><title>Form</title>"+cssLink+"</head>");            
            writer.println("<body>");
            RequestDispatcher rd = req.getRequestDispatcher("nav.html");
            rd.include(req, resp);
            rd= req.getRequestDispatcher("form.html");
			rd.include(req, resp);
            writer.println("</body> </html> ");

        }


        catch(SQLException se)
        {
            //Handle errors for JDBC
            writer.println(se);
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
