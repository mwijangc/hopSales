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

import com.google.gson.Gson; 


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
            String cart_holder = items+"";
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
                    writer.println("<td>");
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
                    // writer.println("</div>"); originally for zoom
                    
                    
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

            // id int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
            // cartItems JSON COMMENT 'cartItems',
            // firstName VARCHAR(255) COMMENT 'firstname',
            // lastName VARCHAR(255) COMMENT 'lastname',
            // phoneNumber VARCHAR(255) COMMENT 'phoneNumber',
            // shippingAddress VARCHAR(255) COMMENT 'shipping',
            // deliveryMethod VARCHAR(255) COMMENT '3options',
            // creditCard int COMMENT 'credit card'
            
            //// BEGIN FORM
            writer.println("<div class='product-page-main-body'>");
            writer.println("<div class='purchase-form'>");
            writer.println("<form action='./push' method='post'>");
            
            
            writer.println("<label for='cartList'>Cart Items</label><br>");
            writer.println("<input type='text' id='cartList' name='cartList' value='"+cart_holder+"' required><br>");
        
            
            //deleted quantity
        
            
            writer.println("<label for='firstName'>First name</label><br>");
            writer.println("<input type='text' id='firstName' name='firstName' required><br>");
        
            
            writer.println("<label for='lastName'>Last name</label><br>");
            writer.println("<input type='text' id='lastName' name='lastName' required><br>");
        
            
            
            writer.println("<label for='phoneNumber'>Phone Number</label><br>");
            
            writer.println("<input type='text' id='phoneNumber' name='phoneNumber' required>");
            writer.println("<br>     ");
            
            writer.println("<label for='shipingAddress'>Shipping Address</label><br>");
            writer.println("<input type='text' id='shippingAddress' name='shippingAddress' required><br>");
        
            
            writer.println("<label for='deliveryMethod'>Delivery method</label><br>");
        
            writer.println("<input type='radio' id='overnight' name='deliveryMethod' value='overnight'>");
            writer.println("<label for='overnight'>Overnight</label><br>");
            writer.println("<input type='radio' id='expedited' name='deliveryMethod' value='expedited'>");
            writer.println("<label for='expedited'>2-day expedited</label><br>");
            writer.println("<input type='radio' id='grounded' name='deliveryMethod' value='grounded'>");
            writer.println("<label for='grounded'>6-day grounded</label><br>");
        
            
            writer.println("<label for='creditCard'>Credit card number</label><br>");
            writer.println("<input type='number' id='creditCard' name='creditCard'><br>  ");
        
        
        
            writer.println("<button type='submit' value='Submit' class='button submit-btn'> Place Order</button>");
        
            writer.println("</form>");
        
            
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


        // RequestDispatcher rd = req.getRequestDispatcher("form.html");
        // rd.include(req,resp);
        
    
        writer.println("</body> </html> ");
    }
}


