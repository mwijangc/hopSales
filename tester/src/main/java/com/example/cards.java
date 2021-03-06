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

/* 
*****
Cards.java , the card servlet dynamically lists products based on the provided product_id.
*
!Starts by querying the backend for every single frog.
rowCount is a counter variable(lowkey hardcoded) to make rows 5 each.
The page is built dynamically with info from backend;
**name, price, image_path(a path stored that is where images are stored)

***Note that if your SQL server is not on, you'll get nulls/errors here and need to restart.
*/

@WebServlet(name = "card", value = "/card")
public class cards extends HttpServlet {

    Connection con;
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    @Override
    public void init() throws ServletException {
        super.init();

        try{
            Class.forName(JDBC_DRIVER);
            // testpa is the database name!
            con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa", dbCredentials.USER , dbCredentials.PASS);
            
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
            String sql = "SELECT * FROM frog_list";
            ResultSet rs = stmt.executeQuery(sql);
            int rowCount = -1;
            writer.println("<div class='product-main-body'> ");
            writer.println("<div class='product-table'>");
            writer.println("<table>");
            while(rs.next())
            {
                rowCount++;      
                if(rowCount%5 == 0)
                {
                    writer.println("<tr>");
                }          
                writer.println("<td> <div class='zoom'>");
                // then an A href that rq FORWARDS to the product page?
                writer.println("<a href='details?param1="+rs.getString("id")+"'>");
                
                String name = rs.getString("name");
                double price = rs.getFloat("price");
                String image_path = rs.getString("image");
                writer.println("<div class='product-card resp-product-card'>");
                writer.println("<span class='product-img-block'>");
                writer.println(" <img width='150' height='150' class='product-image' src='"+image_path+"' ></img> ");
                writer.println("</span>");
                writer.println(" <h3 align='center'>"+ name+" </h3>");
                //put stars?
                writer.println("<p align='center'>$"+price+"</p>");
                writer.println("</div>");
                writer.println("</a>");
                //ending a href
                writer.println("</div>");
                
                writer.println("</td>");

                if(rowCount%5 == 0 && (rowCount != 0 && rowCount!= 5))
                {
                    writer.println("</tr>");
                }          

            }
            writer.println("</table>");
            writer.println("</div>");
            writer.println("</div>");
          

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
