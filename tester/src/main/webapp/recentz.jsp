<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import ="javax.servlet.ServletException"%>
<%@ page import ="javax.servlet.annotation.WebServlet" %>
<%@ page import ="javax.servlet.http.HttpServlet" %>
<%@ page import ="javax.servlet.http.HttpServletRequest" %>
<%@ page import ="javax.servlet.http.HttpServletResponse" %>
<%@ page import ="javax.servlet.http.HttpSession" %>

<%@ page import ="java.io.IOException" %>
<%@ page import ="java.io.PrintWriter" %>
<%@ page import ="java.sql.Connection" %>

<%@ page import ="java.sql.Statement" %>
<%@ page import ="java.sql.ResultSet" %>
<%@ page import ="java.sql.SQLException" %>
<%@ page import="com.example.DatabaseHelper" %>

<%
final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
try {
DatabaseHelper d_help = new DatabaseHelper();
Connection connection = d_help.getConnection();                   
Statement stmt = connection.createStatement(); 
String currUser = (String)session.getAttribute("currentUser");
String sql = "SELECT * FROM users WHERE username = '"+currUser+"'";
ResultSet rs = stmt.executeQuery(sql);
%>


<%


    while(rs.next())
    {                  
        String name = rs.getString("username");
        String orders = rs.getString("orders");                
        String[] orders_s = orders.split(",");
        int[] order_nums = new int[orders_s.length];
        for(int i = 0 ; i < orders_s.length ; i++)
        {
            order_nums[i] = Integer.parseInt(orders_s[i].replaceAll("[\\D]", ""));//turn all non digits to blank
    
        }
    
        int rowCount = -1;
        %>
        <div class='product-main-body'> 
        <div class='product-table'>
        <table>
            <%
    
        String sql_frogs = "SELECT * FROM frog_list WHERE id = '"+order_nums[0]+"' || "+
        "id = '"+order_nums[1]+"'|| id= '"+ order_nums[2]+"'|| id= '"+ order_nums[3]+"'|| id= '"+ order_nums[4]+"'";
        ResultSet r_frogs = stmt.executeQuery(sql_frogs);
        while (r_frogs.next())
        {
            rowCount++;      
            if(rowCount%5 == 0)
            {
                %> <tr> <%
            }          
            %> <td> <%
            
            String f_name = r_frogs.getString("name");
            double price = r_frogs.getFloat("price");
            String image_path = r_frogs.getString("image");
            int frog_id = r_frogs.getInt("id");
            %> <div class='product-card resp-product-card'>
            <span class='product-img-block'>
            <img width='150' height='150' class='product-image' src='<%=image_path %>' ></img>
            </span>
            <h3 align='center'><%=f_name%> </h3>
            
            <p align='center'>$<%=price%></p>
        
    
        
            <form action='./submit' method='get'>
            <label class='rating-label' >
            <input type='hidden' name='frog_id' value='<%=frog_id%>' ></input>
            <input type='hidden' name='frog_name' value='<%= f_name%>' ></input>
                <strong>Rating</strong>
                <input
                class='rating'
                max='5'
                oninput= 'this.style.setProperty("--value", this.value)'
                step='0.5'
                type='range'
                value='1'
                name='rating'>
            </label>
            <button type='submit' value='submit'>send!!</button>
            </form>
        


            
        
            
            </div>
            </td>
    
            <%
            if(rowCount%5 == 0 && (rowCount != 0 && rowCount!= 5))
            {
                %>
                </tr>
                <%
            }          
        }
    }
}
catch (Exception e) 
{
    e.printStackTrace();
}
   %>
   
    </table>
    </div>
    </div>

 


