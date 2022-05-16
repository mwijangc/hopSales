<!DOCTYPE html>
<html lang="en">
    <!-- jsp building homepage -->
    <!-- new index.jsp uses part servlet and static html for the most part now -->
    <%
    
    request.getRequestDispatcher("home").include(request,response);
    request.getRequestDispatcher("recentz.jsp").include(request,response);
    
    %>
    <div class="main-body">

        <div class="hero-bg">
        <div class="hero-header">
            <div class="hero-fancy-side">
            <span class="fancy-word">
            The cutest
            </span>
    <!-- some styling/js for fancy fancy word -->
            <span class="fancy-word">
            Frogs
            </span>
            </div>
    
            <div class="header-text">
                <p>
                    Frogs are friend not food.
                </p>
            </div>
        </div>
        </div>
    
        <!-- all the team member s tuff -->
        <div class="team-intro">
            <p>Team of Frog Agents</p>
        </div>
        <div class="team-block">            
    
            <div class="profile-card">                
                <img src="images/bock.svg">
                <p> Becky Dinh, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Massa purus gravida.</p>
            </div>
            <div class="profile-card">
                <img src="images/julz.svg">
                <p> Jules Labador, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Massa purus gravida.</p>
            </div>
            <div class="profile-card">
                <img src="images/mk.svg">
                <p> Michael Wijangco, Lorem ipsum dolor sit amet, consectetur adipiscing elit. Massa purus gravida.</p>
            </div> 
    
        </div>
    
    
        <div class="products-bar">
            <!-- maybe a carousel w/ transitions of the products? -->
            <a href="frogs">
                <button class="button product-btn" >  
                    Check-out
                </button>
            </a>
        </div>
    
        <!-- <a href="submit"><button> </button></a>  -->
        
    
    
    </div>
    
    

        
</body> 
</html>
    


