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
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


/* 
*****
UPDATE (DEPRECATED)
*****

NO LONGER USED;
REPLACED BY recentz.jsp!


*****
recents.java, the recent servlet displays the users last 5 orders from the backend,
and allows for a star rating to be sent in to the backend(might want to remake component)
**the star rating sql insertion is handled by the submit.java servlet.
*
! The recents info is pinged using the current sessions currentUser attribute
** Doesnt nececssarily handle an empty/no user existing for now.
! follows similar logic to displaying the products from card.java however
doesn't allow for zooming. Also prompts for ratings.
! Ratings will visually always be 1 star and can be dragged in 0.5
increments to 5. This data is stored in the backend via submit.java
!!The table is named orders


*/
@WebServlet(name = "recent", value = "/recent")
public class recents extends HttpServlet {
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
        PrintWriter writer = resp.getWriter();
        Statement stmt = null;
        try{
            // Class.forName("com.mysql.jdbc.Driver");
            // testpa is the database name!
            con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa",dbCredentials.USER , dbCredentials.PASS);
            
            //email is the name of the table!            
            stmt = con.createStatement();
            HttpSession session = req.getSession();
            String currUser = (String)session.getAttribute("currentUser");
            String sql = "SELECT * FROM users WHERE username = '"+currUser+"'";
            ResultSet rs = stmt.executeQuery(sql);

            
            // writer.println("<html> <body>");
            // until the rows exist/stop existing lol
            // TODO
            // CREATE PRODUCT CARDS WITH THE CURRENT USERS ORDER DATA
            
            


            while(rs.next())
            {                  
                String name = rs.getString("username");
                String orders = rs.getString("orders");                
                String[] orders_s = orders.split(",");
                int[] order_nums = new int[orders_s.length];
                for(int i = 0 ; i < orders_s.length ; i++)
                {
                    order_nums[i] = Integer.parseInt(orders_s[i].replaceAll("[\\D]", ""));//turn all non digits to blank
                    // writer.println(orders_s[i]);
                    // writer.println(order_nums[i]);
                }
                // SELECT * FROM frog_list WHERE id = 1||id = 2||id=10 ;
                int rowCount = -1;
                writer.println("<div class='product-main-body'> ");
                writer.println("<div class='product-table'>");
                writer.println("<table>");

                String sql_frogs = "SELECT * FROM frog_list WHERE id = '"+order_nums[0]+"' || "+
                "id = '"+order_nums[1]+"'|| id= '"+ order_nums[2]+"'|| id= '"+ order_nums[3]+"'|| id= '"+ order_nums[4]+"'";
                ResultSet r_frogs = stmt.executeQuery(sql_frogs);
                while (r_frogs.next())
                {
                    rowCount++;      
                    if(rowCount%5 == 0)
                    {
                        writer.println("<tr>");
                    }          
                    writer.println("<td> ");
                    // then an A href that rq FORWARDS to the product page?
                    String f_name = r_frogs.getString("name");
                    double price = r_frogs.getFloat("price");
                    String image_path = r_frogs.getString("image");
                    int frog_id = r_frogs.getInt("id");
                    writer.println("<div class='product-card resp-product-card'>");
                    writer.println("<span class='product-img-block'>");
                    writer.println(" <img width='150' height='150' class='product-image' src='"+image_path+"' ></img> ");
                    writer.println("</span>");
                    writer.println(" <h3 align='center'>"+f_name+" </h3>");
                    //put stars?
                    writer.println("<p align='center'>$"+price+"</p>");
                    // RequestDispatcher rd = req.getRequestDispatcher("rating.html");
                    // rd.include(req,resp);

                    // CURRENTLY
                    // default set of the stars is 1, doesnt show "past ratings" or an active rating.
                    // easier with REACT
                    writer.println(" <form action='./submit' method='get'>");
                    writer.println("<label class='rating-label' >");
                    writer.println("<input type='hidden' name='frog_id' value='"+frog_id+"'></input>");
                    writer.println("<input type='hidden' name='frog_name' value='"+f_name+"'></input>");
                    writer.println("    <strong>Rating</strong>");
                    writer.println("    <input");
                    writer.println("       class='rating'");
                    writer.println("       max='5'");
                    writer.println("       oninput=\"this.style.setProperty('--value', this.value)\"");
                    writer.println("       step='0.5'");
                    writer.println("       type='range'");
                    writer.println("      value='1'");
                    writer.println("      name='rating'>");
                    writer.println("</label>");
                    writer.println("<button type='submit' value='submit'>send</button>");
                    writer.println("</form>");
                    
                    // writer.println("</div>"); (originally for zoom)
                    //ending a href
                    writer.println("</div>");
                    writer.println("</td>");
             

                    if(rowCount%5 == 0 && (rowCount != 0 && rowCount!= 5))
                    {
                        writer.println("</tr>");
                    }          
                }
                // writer.println("Name: "+ name + " Orders: "+ orders_s[i] );
                writer.println("</table>");
                writer.println("</div>");
                writer.println("</div>");
               
            }
         
          
            // writer.println("</body> </html> ");
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
