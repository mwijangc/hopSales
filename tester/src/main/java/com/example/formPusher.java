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
import java.util.UUID;

import com.google.gson.Gson; 

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
        PrintWriter writer = resp.getWriter();
        writer.println("<html> <body>");
        // String name = req.getParameter("name");
        // String email = req.getParameter("email");
        // writer.println("<p>Name: "+ name + " Email: " + email+"</p>");
        writer.println(req.getQueryString());
        writer.println("<h3> Order was submitted! </h3>");
        writer.println("</body> </html> ");
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
        PrintWriter writer = resp.getWriter();
        writer.println("<html> ");
        String cssLink=" <link rel='stylesheet' href='index.css'>";
        writer.println("<head><title>Form</title>"+cssLink+"</head>");            
        writer.println("<body>");
        writer.println("<header>");
        writer.println("<nav class='navbar' id='navBar'>");
        writer.println("<a class ='navlink navbarLogo' href='/tester'>");
        writer.println(" <img height = '25px' width='25px' src='images/fwog_logo.svg'> </a>");
        writer.println("<a class='navlink' href='form' action='form'>");
        writer.println("Form");
    writer.println("</a>");
    writer.println("<a class='navlink' href='products.html'>    ");
    writer.println("Frogs");
    writer.println("</a>");           
    writer.println("</nav> </header>");
        // String name = req.getParameter("name");
        // String email = req.getParameter("email");
        // writer.println("<p>Name: "+ name + " Email: " + email+"</p>");
        RequestDispatcher rd = req.getRequestDispatcher("orderPush");
        rd.include(req,resp);
        // String product = req.getParameter("productID");
        
        // writer.println("<p>"+product+"</p>");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // testpa is the database name!
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa", "root", "mysql_554");
            // https://stackoverflow.com/questions/24616174/how-to-convert-arraylist-to-json-object
            PreparedStatement ztmt = con.prepareStatement("INSERT INTO customerorders VALUES (?,?,?,?,?,?,?,?)");
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
    


        writer.println("<h3> Order was submitted!</h3>");
        writer.println("</body> </html> ");
    }
}

