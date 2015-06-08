<%-- 
    Document   : bkRecord
    Created on : Apr 21, 2013, 1:53:40 PM
    Author     : 52209388
--%>

<%@page import="Bean.Member"%>
<%@page import="Bean.Section"%>
<%@page import="Bean.Venue"%>
<%@page import="Bean.Movie"%>
<%@page import="java.util.List"%>
<%@page import="Bean.Booking"%>
<%@page import="Utility.Opt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Record</title>
           <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://cdnjs.bootcss.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">

    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="./bst/index.css" rel="stylesheet">
        <link href="./css/commonCss.css" rel="stylesheet">

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-72-precomposed.png">
      <link rel="apple-touch-icon-precomposed" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-57-precomposed.png">
      <link rel="shortcut icon" href="http://twitter.github.io/bootstrap/assets/ico/favicon.png">
  <style id="holderjs-style" type="text/css">.holderjs-fluid {font-size:16px;font-weight:bold;text-align:center;font-family:sans-serif;margin:0}
      table{
          float:left;
          margin:10px;
      }
      
      #footer{
          clear:both;
      }
  
  </style>
    </head>
    <body>
         <%@include file="/header.jsp" %>
         <% List<Movie> mv=(List<Movie>)request.getAttribute("movieInfo");%>
 
         <% List<Venue> v=(List<Venue>)request.getAttribute("venueInfo");%>

         <% List<Section> s=(List<Section>)request.getAttribute("sectionInfo"); %>

         <% List<Booking> bk=(List<Booking>)request.getAttribute("bookingInfo"); %>
         
         <% boolean []refundSec=(boolean [])request.getAttribute("refundSec"); %>
        <div id="header">
            <% Opt opt=new Opt();  
            boolean display=false;
            //out.println(opt.showHeader());
            %>
            
        </div>
             
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
                    display=true;
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
             <%if(display){%>
                <% int count=0; %>
 
            <% for (Booking bks:bk)

            { %>

            <table border="1" style="height:350px">

                <caption>Ticket Information</caption>

                 <thead>

                    <tr>

                        <th>

                            Item

                        </th>

                        <th>
 
                            Detail

                        </th>

                    </tr>    

                </thead>

                <tbody>

                     <tr>

                        <td>

                            Name

                        </td>

                        <td>

                           <% out.println(mv.get(count).getName()); %>
 
                        </td>

                    </tr>

                    <tr>

                        <td>

                            Duration
 
                        </td>

                        <td>

                           <% out.println(mv.get(count).getDuration()); %>

                        </td>
 
                    </tr>

                    <tr>

                        <td>

                            Category

                        </td>
 
                        <td>

                           <% out.println(mv.get(count).getCategory());%>

                        </td>

                    </tr>
 
                    <tr>

                        <td>

                            House

                        </td>

                        <td>
 
                           <% out.println(v.get(count).getHouse()); %>

                        </td>

                    </tr>

                    <tr>

                         <td>

                            Time

                        </td>

                        <td>

                           <% out.println(s.get(count).getTime()); %>
 
                        </td>

                    </tr>

                    

                    <tr>

                        <td>

                            Seat
 
                        </td>

                        <td>

                       

                           <% out.println(bks.getSeat()); %>
                        </td> 
                    </tr>                  
                    <tr>
                        <td>
                            Price
                        </td>
                        <td>
                           <% out.println(s.get(count).getPrice()); %>
                        </td>
                    </tr>                    
                    <tr>
                        <td>Pay</td> 
                        <td><% out.println(bks.getPayment()); %></td>
                    </tr>
                </tbody>    
                <tfoot>
                    <tr>
                        <td>
                            
                        </td>
                        <td>
                            <form action="memberRefund" method="post">
              
            <input type="hidden" name="issueID" value="<% out.print(bks.getIssueID());%>" />
            
            <% if(bks.getStatus().equals("P")&&bks.getPayment()>0&&refundSec[count]==true&&type=="member")
               { %>
                    <input type="submit" value="Ask Refund!" />
               <% } 
            else if(bks.getStatus().equals("RP")) 
               { %>
               <p>Refund request has been submitted!</p>     
               <input type="submit" value="Cancel Request for Refund!" />
               
               <% } %>            
               </form>
                        </td>
                    </tr>
                </tfoot>
            </table>
              
            
            <% count++;} %>
            
            <%}else{%>
        
        <img src="http://c0016417.cdn2.cloudfiles.rackspacecloud.com/Bx.jpg"style="margin:0 auto"/>
                <%}%>
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

