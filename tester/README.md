# README - INF 124 PA 2

## General Layout

We implemented a navigation bar at the top of the screen to navigate between pages. Clicking on the logo will send you to the home page of the site, where you can see,the full list of items, the users's last 5 orders from a database along with the ability to rate, a hero banner, the group of three that worked on it (Michael Wijangco, Becky Dinh, and Jules Labador).

## Requirements
##1. You want to use Java Servlet and MySQL database to generate the product information dynamically. Include the output of two servlets to create the homepage for your e-commerce site: the first servlet should handle the displaying of the list of products obtained from a backend database, and the second servlet should display the last 5 products that the user has ordered, additionally, the user should be able to directly provide a rating for each one of these orders. The rating functionality should be similar to the below image. You are also required to use servlet "include" feature to implement this requirement.

  To satisfy a user session, the default user "DefaultAndy" is logged into the page automatically (will change eventually). Using data about defaultAndy in the MySql tables, we display using the the cards.java and recents.java servlet. Recent.java implements a 5 star rating system (that visually always starts as 1 star) using the <input type='range'> model. The handling of pushing this rating to the backend orders table is via submit.java. The increments are in half-steps and are pushed to the database once the user clicks the 'send' button.

  Overall home.java acts as the index.jsp and uses include to "build" the blocks of the page.

##2. Using servlets create a "product details" page. This page should take a product identifier as a parameter and show the product details after getting the relevant information from the database. This page should NOT have an order form, only a button to "Add to Cart". Use servlet "session" to store the products in a shopping cart.
    
    Home.java, the main page servlet lists the products as clickable-cards using card.java. These cards then dynamically call
    ```writer.println("<a href='details?param1="+rs.getString("id")+"'>");```
    which generates a page based on the detailedPage.java servlet. This page uses the parameter of the product id to ping the database and display dynamic information accordingly.
    This page also uses frog_id to add to the current sessions cart using the cart.java servlet.


##3. When the user submits a form to order a product, instead of sending an email from the client-side, as you did in first assignment, the request should be sent to a server-side Java Servlet that stores that information in a database table. The form should be validated to prevent insertion of bad data in your database. 

    Generally the form now exists on the same page as the cart, cart.java. 
    Cart.java dynamically visualizes the users cart along with a quantity of items and the total price. The form validates loosely for numbers, and evaluates phonenumbers loosely as text as well. Specifics on credit card input are also limited to checking for numbers.


##4. Using servlets create a "check out" page, which allows the user to place an order. The page should show all the products in the shopping cart and the total price. This page should have a form which will allow the user to do the following:

Enter shipping information: name, shipping address, phone number, credit card number, etc.
Submit the order for storage in the backend database
On successful submission, forward to the order details page. You are required to use servlet "forward" feature to implement this requirement.

    The checkout page is combined with the cart.java visualization. The relevant information is in a form within cart.java and is pushed to the backend via formPusher.java and visually confirmed via orderDetails.java.

##5. [Extra credit] Deploy your website on UCI openlab or other cloud services such as AWS,  (Links to an external site.)or Google Cloud (Links to an external site.)

To deploy your website on openlab, you can follow the instructions here: Singularity @ ICS InstructionsLinks to an external site. 
Use the following ports for deploying your website:
Tomcat port: 3 + last four digits of any team member's ID
MySQL port: 4 + last four digits of any team member's ID
Include the URL of your deployed website in the readme file of your submission

    This was not done/implemented by our team.
    
## Group Members

Michael Wijangco
Becky Dinh
Jules Labador