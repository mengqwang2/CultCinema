<%-- 
    Document   : manageSeat
    Created on : Apr 21, 2013, 5:51:21 PM
    Author     : 52165627
--%>

<%@page import="DAO.StatDAO"%>
<%@page import="Utility.Opt"%>
<%@page import="Bean.Section"%>
<%@page import="Bean.Movie"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage seat</title>
   
   <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://cdnjs.bootcss.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">

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
      td{padding:20px;}
  
  </style>
    </head>
    
    <body>
         <%@include file="/header.jsp" %>
        <div id="header">
            <% Opt opt=new Opt();  
                boolean display=false;
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
                    display=true;
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
            <%if(display){%>
            <% String action= (String)request.getAttribute("action");%>
            <form method="post" action="stat">
                <input type="hidden" name="id" value="0">
                <input type="hidden" name="action" value="show">
                    <select name="movieID">
                    <%  List<Movie> movies = (List<Movie>)session.getAttribute("movies");
                        int i=0;
                        for(Movie movie: movies){
                    %> 
                    <option value="<%=movie.getMovieID()%>" 
                            <%if(action.equals("show")) 
                                {int id=(Integer)request.getAttribute("selectedMovie");
                                if (movie.getMovieID()==id){%>selected="selected"<%}}%>><%=movie.getName()%>
                    </option>                           
                    <% i++;
                        } %>      
                    </select> 

                        <input type="submit" class="btn" value="Select Movie" style="position: relative; top:-5px;margin:  0 5px"> 

            </form>

            <% if(action.equals("show")){ %>
            <table id="ticketInfo">
                <tr>
                    <td>Section</td>
                    <td>Venue</td>
                    <td>Time</td>
                    <td>Price</td>
                    <td>Total Tickets</td>
                    <td>Sold Tickets</td>
                    <td>Available</td>
                </tr>
                  <% List<Section> sections = (List<Section>)request.getAttribute("sections"); 
                    StatDAO statDAO=new StatDAO();
                    int total;
                    int sold=0;
                    int count=0;
                    int cc=1;
                    int available;
                    for (Section section: sections ){
                        total=statDAO.getTotalTicket(section.getSectionID())-statDAO.getReservedTicket(section.getSectionID());
                        sold=statDAO.getSoldTicket(section.getSectionID());
                        available=total-sold;
                        %>     
                       <tr> 
                           <td><%=section.getSectionID()%></td>
                           <td><%=section.getVenueID()%></td>
                           <td><%=section.getTime()%> </td>
                           <td>$<%=section.getPrice()%>  </td>     
                           <td><%=total%></td>
                           <td><%=sold%></td>
                           <td><%=available%></td>
                       </tr>
                     <%count+=1; } %>    
             
            </table>
                     
            <APPLET codebase="classes" code="Asg1/BarChartJApplet.class" width="800" height="600">
                        <param name="AxisTitleH" value="Section" />
                        <param name="AxisTitleV" value="Data" />
                        <param name="ChartTitle" value="Stat information graph." />
                        <param name="NumOfDataSeries" value="<%=count%>" />
                        <% for (Section section: sections ){
                        sold=statDAO.getSoldTicket(section.getSectionID());%>
                        <param name="Data<%=cc++%>" value="<%=sold%>" />
                        
                        <%}%>
                         
                        <param name="AxisValueH" value="Sold" />
                        <param name="AxisValueV" value="<% for (Section section: sections ){%><%=section.getSectionID()%>,<%}%>" />
                        <param name="ShowChartTitle" value="1" />
                        <param name="ShowAxisTitles" value="1" />
                        <param name="ShowDataLabels" value="1" />
                        <param name="ShowLegend" value="1" />
            </APPLET>
                     <%}%>
                        <%}else{%>
        
        <img src="http://c0016417.cdn2.cloudfiles.rackspacecloud.com/Bx.jpg"style="margin:0 auto"/>
                <%}%>
                         <%@include file="/footer.jsp" %>

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
