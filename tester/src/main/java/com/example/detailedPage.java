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

@WebServlet(name = "details", value = "/details")
public class detailedPage extends HttpServlet {

    

    @Override
    public void init() throws ServletException {
        super.init();
        

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Connection con;
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
       
        Statement stmt = null;
        PrintWriter writer = resp.getWriter();
        
        try{
            Class.forName(JDBC_DRIVER);
            // testpa is the database name!
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa", "root", "mysql_554");         
            stmt = con.createStatement();
            String sql = "SELECT * FROM frog_list WHERE id = '"+req.getParameter("param1")+"'";
            ResultSet rs = stmt.executeQuery(sql);
            //email is the name of the table!
            writer.println("<html> ");
            String cssLink=" <link rel='stylesheet' href='index.css'>";
            writer.println("<head><title>Form</title>"+cssLink+"</head>");            
            writer.println("<body>");
            RequestDispatcher rd = req.getRequestDispatcher("nav.html");
            rd.include(req, resp);
            String name = "";
            double price = 0;
            String image_path = "";
            String description = "";
            while (rs.next())
            {
                name = rs.getString("name");
                price = rs.getFloat("price");
                image_path = rs.getString("image");
                description = rs.getString("description");
                // writer.println(name);
            }
            // param1 reperesents frog_id
            writer.println("<div class='main-body'>");
            writer.println("<div class='float-container'>");
    
            
            writer.println("<div class='float-child'>");
            writer.println("<img width='400' height='400' class='product-image' src='"+image_path+"' ></img>");
            // writer.println(" <img width='150' height='150' class='product-image' src='"+image_path+"' ></img> ");

            writer.println("        </div>");

            writer.println("<div class='float-child'>");
            // writer.println("<cite>");
            // writer.println("<a href='https://en.wikipedia.org/wiki/Wood_frog'>");
            writer.println("<h1>"+name+"</h1>");
            // writer.println("</a> ");
            // writer.println("</cite>");
            writer.println("<h2>$"+price+"0</h2>        ");
            

            writer.println("<h3>");
            writer.println("Frog Description");
            writer.println("</h3>");

            writer.println("    <p >"+description+"</p>");
            
            writer.println("<a href='form'><button>Fill out Purchase Form</button></a>");

            writer.println("</div>");

            writer.println("</div>");
            
            writer.println("</body> </html> ");
            stmt.close();
            con.close();
           

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
