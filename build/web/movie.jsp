<%-- 
    Document   : movie
    Created on : Apr 7, 2013, 2:34:23 PM
    Author     : 52165627
--%>


<%@page import="Utility.Opt"%>
<%@page import="java.util.List"%>
<%@page import="Bean.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Controller.MovieDisplay"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie list</title>
         <script language="javascript" type="text/javascript" src="js/WdatePicker.js"></script>
        <!-- Le styles -->
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
      .holderjs-fluid {font-size:16px;font-weight:bold;text-align:center;font-family:sans-serif;margin:0}
      td{
          padding:20px;
      }
  
  </style></head>
    <body>      
       <%@include file="/header.jsp" %>
            <% Opt opt=new Opt();  
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
            
           <div id="navigation">
                
                <% 
                    out.println(opt.showItem(type));
                %>
            </div>

        
            <div class="container" style=" position:relative;top:90px;">
                <form action="MovieDisplay?Action=bydate"method="POST">
                    Date:&nbsp;&nbsp;
                     <input type="text" name="date" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy/MM/dd',alwaysUseStartDate:true})"/>

                    &nbsp;&nbsp;<input type="submit" value="Show" class="btn" style="position: relative; top:-5px"/>
                </form>
        <% List<Movie> movies = 
                (List<Movie>)session.getAttribute("movies"); 
        %>
        <form name="movie">
            <h3>Movies</h3>
            <table id="movie_table">
        <%
        for (Movie movie: movies ){
        %>
        
        <tr>
            <td><img style="width: 80px; height: 110px;" src="<%=movie.getSrc()%>"></td>
            <td><%=movie.getName()%> </td>
           
            <td><a href="SectionDisplay?id=<%= movie.getUid()%>&movieID=<%= movie.getMovieID()%>&action=ViewSection">More information</a></td>
        
        </tr>
        <% } %>
        <% if(movies.size()==0){%>
        <tr><td>No movie available!</td></tr>
        <%}%>
        </table>
        </form>
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
