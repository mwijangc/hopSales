package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;

// the /hello is put into the form action!
@WebServlet(name = "frogs", value = "/frogs")
public class frogs extends HttpServlet {
    
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
        
        writer.println("<html> <body>");
        try{
            
            //email is the name of the table!
            writer.println("<html> ");
            String cssLink=" <link rel='stylesheet' href='index.css'>";
            writer.println("<head><title>Form</title>"+cssLink+"</head>");            
            writer.println("<body>");
            RequestDispatcher rd = req.getRequestDispatcher("nav.html");
            rd.include(req, resp);

            // TODO
            // iterate through dataset and construct product cards
        }       
        catch(Exception e){
            //Handle any other type of error
            writer.println(e);
        }
        writer.println(req.getQueryString());
        writer.println("<h3> Data was submitted! </h3>");
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
        String product = req.getParameter("productID");
        
        writer.println("<p>"+product+"</p>");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // testpa is the database name!
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/" + "testpa", "root", "mysql_554");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO email VALUES (?,?,?)");
            stmt.setInt(1, Integer.valueOf(req.getParameter("productID")));
            stmt.setString(2, req.getParameter("firstName"));
            stmt.setString(3, req.getParameter("lastName"));
            
            stmt.executeUpdate();
            stmt.close();
            con.close();

            // PrintWriter writer = resp.getWriter();
            
            // writer.println("</body> </html> ");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            writer.println("class issue");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            writer.println("sql issue");
        }
    


        writer.println("<h3> Data was submitted! (did a post request btw)</h3>");
        writer.println("</body> </html> ");
    }
}

