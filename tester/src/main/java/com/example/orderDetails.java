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
import java.util.ArrayList;
import java.util.Collections;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "orderPush", value = "/orderPush")
public class orderDetails extends HttpServlet {

    

    @Override
    public void init() throws ServletException {
        super.init();
        

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Connection con;
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
       
        Statement stmt = null;
        PrintWriter writer = resp.getWriter();
        
        try{
            Class.forName(JDBC_DRIVER);
            // testpa is the database name!
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa", "root", "mysql_554");         
            stmt = con.createStatement();
            
            // ResultSet rs = stmt.executeQuery(sql);
            
            // writer.println("<html> ");
            
            // String cssLink=" <link rel='stylesheet' href='index.css'>";
            // writer.println("<head><title>Form</title>"+cssLink+"</head>");            
            // writer.println("<body>");
            // RequestDispatcher rd = req.getRequestDispatcher("nav.html");
            // rd.include(req, resp);
            HttpSession session = req.getSession();
            // writer.println("ordered from" +req.getParameter("cartList"));
            
            String sql = "SELECT * FROM frog_list";
            ResultSet rs = stmt.executeQuery(sql);
            int rowCount = -1;
            writer.println("<div class='product-main-body'> ");
            writer.println("<div class='product-table'>");
            writer.println("<table>");
            ArrayList<Integer> items = new ArrayList<Integer>();
            items =  (ArrayList<Integer>)session.getAttribute("cart");
            double total = 0;
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
                    // collection quantity
                    total+= rs.getFloat("price")*(Collections.frequency(items,rs.getInt("id")));
                    writer.println("</td>");

                    if(rowCount%5 == 0 && (rowCount != 0 && rowCount!= 5))
                    {
                        writer.println("</tr>");
                    }                              

                }
                
            }
            writer.println("</table>");
            writer.println("</div>");
            writer.println("<h3>Total is : $"+total+"! </h3>");
            writer.println("</div>");
            
            // writer.println("</body> </html> ");
            stmt.close();
            con.close();
           

        }


        catch(SQLException se)
        {
            //Handle errors for JDBC
            // writer.println(se);
        }
        catch(Exception e){
            //Handle any other type of error
            // writer.println(e);
        }finally{
            //finally block used to close resources
            try{
            if(stmt!=null)
                stmt.close();
            }catch(SQLException ignore) {}// nothing we can do
        } //end try
    }


}
