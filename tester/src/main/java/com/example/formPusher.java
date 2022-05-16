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


import java.sql.SQLException;


import java.util.ArrayList;

import java.util.UUID;

import com.google.gson.Gson; 

/* 
*****
UPDATE
*****

formpusher now FORWARDS to the details.jsp (keeping main input ability in the servlet, but the static html now just confirms an order has been pushed to the backend)

*****
formPusher.java, called primarily by cart.java's form,
parses the form and inserts into the database with uuids that are randomly generated.
*
Honestly doGet isn't really called. Shouldnt be used usually.
!formPusher includes orderDetails.java which summarizes an order visually
! using a prepared statement we push the information into the backend
!! Note some things are not fully fleshedout/checked due to a library probably being
!! desired for the sensitive information. Otherwise data is checked on basis of data-type (numeric, text)
!! Formatting is not hard coded/type-checked too much yet.


*/

// the /push is put into the form action!
@WebServlet(name = "push", value = "/push")
public class formPusher extends HttpServlet {

    // happens when start servlett
    @Override
    public void init() throws ServletException {
        super.init();
    }

    // happens when do get requests on  the servlett
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("details.jsp");
        rd.forward(req,resp);

    }


    //      
    //     productID
    //     quantity
    //     firstName
    //     lastName
    //     phoneNumber
    //     shippingAddress
    //     deliveryMethod
    //     creditCard
    //  

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
       
    
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // testpa is the database name!
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa", dbCredentials.USER , dbCredentials.PASS);
            // https://stackoverflow.com/questions/24616174/how-to-convert-arraylist-to-json-object
            PreparedStatement ztmt = con.prepareStatement("INSERT INTO customerorders VALUES (?,?,?,?,?,?,?,?,?)");
            // stmt.setInt(1, Integer.valueOf(req.getParameter("productID")));
            // added maven dependency for gson
            HttpSession session = req.getSession();
            ArrayList<Integer> items = (ArrayList<Integer>)session.getAttribute("cart");
            String json_v = new Gson().toJson(items);
            ztmt.setString(1, UUID.randomUUID().toString().replace("-", ""));
            ztmt.setString(2, json_v);
            ztmt.setString(3, req.getParameter("firstName"));
            ztmt.setString(4, req.getParameter("lastName"));
            ztmt.setString(5, req.getParameter("phoneNumber"));
            ztmt.setString(6, req.getParameter("shippingAddress"));
            ztmt.setString(7, req.getParameter("deliveryMethod"));
            ztmt.setString(8, req.getParameter("creditCard"));
            ztmt.setString(9, req.getParameter("email"));
            //get the (generate an order id num) username from session, the id of the item from getParam, and the rating from input getParam,
            // stmt.setString(2, req.getParameter("firstName"));
            // stmt.setString(3, req.getParameter("lastName"));
            
            ztmt.executeUpdate();
            ztmt.close();
            con.close();
            
            // PrintWriter writer = resp.getWriter();
            
            // writer.println("</body> </html> ");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            // writer.println("class issue");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // writer.println("sql issue");
        }
    
        // PrintWriter writer = resp.getWriter();
        // writer.println("<html> ");
        // String cssLink=" <link rel='stylesheet' href='index.css'>";
        // writer.println("<head><title>Form</title>"+cssLink+"</head>");            
        // writer.println("<body>");
        // writer.println("<header>");
        // writer.println("<nav class='navbar' id='navBar'>");
        // writer.println("<a class ='navlink navbarLogo' href='/tester'>");
        // writer.println(" <img height = '25px' width='25px' src='images/fwog_logo.svg'> </a>");
        // writer.println("<a class='navlink' href='cart' >");
        // writer.println("Cart");
        // writer.println("</a>");
                  
        // writer.println("</nav> </header>");

        // RequestDispatcher rd = req.getRequestDispatcher("orderPush");
        // // RequestDispatcher rd = req.getRequestDispatcher("details.jsp");
        // rd.include(req,resp);

        // writer.println("<h3> Order was submitted!</h3>");
        // writer.println("</body> </html> ");
        RequestDispatcher rd = req.getRequestDispatcher("details.jsp");
        rd.forward(req,resp);

        
    }
}

