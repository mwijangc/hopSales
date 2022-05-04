# README - INF 124 PA 2

## General Layout

We implemented a navigation bar at the top of the screen to navigate between pages. Clicking on the logo will send you to the home page of the site, where you can see,the full list of items, the users's last 5 orders from a database along with the ability to rate, a hero banner, the group of three that worked on it (Michael Wijangco, Becky Dinh, and Jules Labador).

## Requirements

##1.
To satisfy a user session, the default user "DefaultAndy" is logged into the page automatically (will change eventually). Using data about defaultAndy in the MySql tables, we display using the the cards.java and recents.java servlet. Recent.java implements a 5 star rating system (that visually always starts as 1 star) using the <input type='range'> model. The handling of pushing this rating to the backend orders table is via submit.java. The increments are in half-steps and are pushed to the database once the user clicks the 'send' button.

Overall home.java acts as the index.jsp and uses include to "build" the blocks of the page.

##2.
    
Home.java, the main page servlet lists the products as clickable-cards using card.java. These cards then dynamically call
```writer.println("<a href='details?param1="+rs.getString("id")+"'>");```
which generates a page based on the detailedPage.java servlet. This page uses the parameter of the product id to ping the database and display dynamic information accordingly.
This page also uses frog_id to add to the current sessions cart using the cart.java servlet.


##3. 

Generally the form now exists on the same page as the cart, cart.java. 
Cart.java dynamically visualizes the users cart along with a quantity of items and the total price. The form validates loosely for numbers, and evaluates phonenumbers loosely as text as well. Specifics on credit card input are also limited to checking for numbers.


##4. 

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