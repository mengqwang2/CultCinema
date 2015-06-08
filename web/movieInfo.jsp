<%-- 
    Document   : movieInfo
    Created on : 2013-4-7, 22:51:33
    Author     : Administrator
--%>

<%@page import="Utility.Opt"%>
<%@page import="java.util.List"%>
<%@page import="Bean.Section"%>
<%@page import="Bean.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Movie movie=(Movie)request.getAttribute("movie");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=movie.getName()%></title>
        <script type="text/javascript">
            /*
            function getSection()
              {
                Venue.value="id"; 
                var id=document.getElementById("sectionSelect").selectedIndex;
                         
            }
            */
        </script>
        <link href="./css/commonCss.css" rel="stylesheet" type="text/css">
        <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://cdnjs.bootcss.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/commonCss.css" rel="stylesheet">
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="./bst/index.css" rel="stylesheet">


    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="http://twitter.github.io/bootstrap/assets/ico/favicon.png">
  <style id="holderjs-style" type="text/css">
      .holderjs-fluid{ 
          font-size:16px;font-weight:bold;text-align:center;font-family:sans-serif;margin:0}
      img{float: left;margin-right:100px}
      #disqus_thread{clear:both;}
      select{width: 400px;}
      
  </style>
    </head>
    <body>
         <%@include file="/header.jsp" %>
        
         <div id="header">
            <% Opt opt=new Opt();  
           // out.println(opt.showHeader());
            %>
        </div>
        <div id='loginNav'>
            <% 
                String type="";
                if (session.isNew())
                {
                    out.println(opt.showNotLoginNav());
                }
                else if(session.getAttribute("memberID")==null&&session.getAttribute("managerID")==null&&session.getAttribute("officerID")==null)
                {
                    out.println(opt.showNotLoginNav());
                }
                else if(session.getAttribute("memberID")!=null)
                {
                    int memberID=(Integer)session.getAttribute("memberID");
                    out.println(opt.showLoginNav(memberID));
                    type="member";
                }
                else if(session.getAttribute("managerID")!=null)
                {
                    String managerID=(String)session.getAttribute("managerID");
                    out.println(opt.showLoginNav(managerID,0));
                    type="manager";
                }
                else if(session.getAttribute("officerID")!=null)
                {
                    String officerID=(String)session.getAttribute("officerID");
                    out.println(opt.showLoginNav(officerID,1));
                    type="officer";
                }
            %>
        </div>
        
        <div id="navigation">
                
                <% 
                    out.println(opt.showItem(type));
                %>
        </div>
        
        <div class="container" style=" position:relative;top:90px;">
        <img style="width: 200px; height: 240px;" src="<%=movie.getSrc()%>">    
        <div id="movie_Info">
            <h5><%=movie.getName()%></h5>
        <ul>
            <li>Language: <%=movie.getLanguage()%></li>
            <li>Duration: <%=movie.getDuration()%></li>
            <li>Director: <%=movie.getDirector()%></li>
            <li>Category: <%=movie.getCategory()%></li>
            <li>Cast: <%=movie.getCast()%></li>
            <li>Description: <%=movie.getDescription()%></li>
        </ul>
        <form action="SeatDisplay" method="Post">            
                <select name="sectionSelect">
                <% List<Section> sections = (List<Section>)request.getAttribute("sections"); 
                      for (Section section: sections ){
                  %>     
                  <option value="<%=section.getSectionID()%>">
                       Venue: House <%=section.getVenueID()%>
                       Time: <%=section.getTime()%> 
                       Price: $<%=section.getPrice()%>          
                    </option>
                <% } %>      
                </select> 
                <input type="hidden" name="action" value="purchase">
                <input type="submit" value="Purchase">
        </form>
        </div>
                    <div id="disqus_thread"></div>
    <script type="text/javascript">
        /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
        var disqus_shortname = 'e7rank'; // required: replace example with your forum shortname

        /* * * DON'T EDIT BELOW THIS LINE * * */
        (function() {
            var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;
            dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
            (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
        })();
    </script>
    <noscript>Please enable JavaScript to view the <a href="http://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
    <a href="http://disqus.com" class="dsq-brlink">comments powered by <span class="logo-disqus">Disqus</span></a>
    
       <div id="footer">
         <%@include file="/footer.jsp" %>
        </div>  
        </div>
       
                       <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./bst/jquery.js"></script>
    <script src="./bst/bootstrap-transition.js"></script>
    <script src="./bst/bootstrap-alert.js"></script>
    <script src="./bst/bootstrap-modal.js"></script>
    <script src="./bst/bootstrap-dropdown.js"></script>
    <script src="./bst/bootstrap-scrollspy.js"></script>
    <script src="./bst/bootstrap-tab.js"></script>
    <script src="./bst/bootstrap-tooltip.js"></script>
    <script src="./bst/bootstrap-popover.js"></script>
    <script src="./bst/bootstrap-button.js"></script>
    <script src="./bst/bootstrap-collapse.js"></script>
    <script src="./bst/bootstrap-carousel.js"></script>
    <script src="./bst/bootstrap-typeahead.js"></script>
    <script>
      !function ($) {
        $(function(){
          // carousel demo
          $('#myCarousel').carousel()
        })
      }(window.jQuery)
    </script>
    <script src="./bst/holder.js"></script>
    </body>
</html>
