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

// the /push is put into the form action!
@WebServlet(name = "cart", value = "/cart")
public class cart extends HttpServlet {

    // happens when start servlett
    @Override
    public void init() throws ServletException {
        super.init();
    }

    // happens when do get requests on  the servlett
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();        
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

   
}

