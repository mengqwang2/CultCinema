<%-- 
    Document   : refund
    Created on : Apr 9, 2013, 5:35:30 PM
    Author     : 52165627
--%>

<%@page import="Utility.Opt"%>
<%@page import="Bean.Booking"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Refund</title>
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
                    display=true;
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
        <form action="" method="post">
            <table>
                <tr>
                    <td>Issue ID</td>
                    <td>Section ID</td>
                    <td>Seat</td>
                    <td>Payment</td>
                    <td>Member ID</td>
                </tr>
            
            <% 
            List<Booking> Refunds= 
                (List<Booking>)session.getAttribute("refunds"); 
            %>
        <%
        for (Booking refund: Refunds ){
        %><tr>
            <td><%=refund.getIssueID()%></td>
            <td><%=refund.getSectionID()%></td>
            <td><%=refund.getSeat()%></td>
            <td><%=refund.getPayment()%></td>
            <td><%=refund.getMemberID()%></td>
            <td><a href="ConfirmRefund?IssueID=<%=refund.getIssueID()%>">Confirm Refund</td>
        </tr> 
        <% } %>
        </table>
        </form>   <%}else{%>
        
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
