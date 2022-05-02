package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collections;

// the /push is put into the form action!
@WebServlet(name = "cart", value = "/cart")
public class cart extends HttpServlet {

    Connection con;
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // happens when start servlett
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

    // happens when do get requests on  the servlett
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();       
        Statement stmt = null;
        try {
        
            stmt = con.createStatement();
    
            writer.println("<html> ");
            String cssLink=" <link rel='stylesheet' href='index.css'>";
            writer.println("<head><title>Form</title>"+cssLink+"</head>");            
            writer.println("<body>");
            RequestDispatcher rd = req.getRequestDispatcher("nav.html");
            rd.include(req, resp);
            HttpSession session = req.getSession();
            ArrayList<Integer> items = new ArrayList<Integer>();

            int f_id = Integer.parseInt(req.getParameter("prod"));

            // if no cart
            //try to use arraylist for cart
            if(session.getAttribute("cart") == null)
            {
                //add to items the items id.
                items.add(f_id);
                session.setAttribute("cart", items);
            }
            else
            {
                items = (ArrayList<Integer>)session.getAttribute("cart");
                items.add(f_id);
                session.setAttribute("cart",items);
            }

            writer.println("<p> Cart is currently :"+ items +", recently added "+f_id+" </p>");

            String sql = "SELECT * FROM frog_list";
            ResultSet rs = stmt.executeQuery(sql);
            int rowCount = -1;
            writer.println("<div class='product-main-body'> ");
            writer.println("<div class='product-table'>");
            writer.println("<table>");
            
            while(rs.next())
            {
                
                if(items.contains(rs.getInt("id")))
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
                    writer.println("<h3 align='center'>Quantity: "+Collections.frequency(items,rs.getInt("id"))+"</h3>");
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


        RequestDispatcher rd = req.getRequestDispatcher("form.html");
        rd.include(req,resp);
        writer.println("</body> </html> ");
    }
}


