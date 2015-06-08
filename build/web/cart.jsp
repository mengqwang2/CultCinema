<%-- 

    Document   : cart

    Created on : Apr 20, 2013, 6:38:57 PM

    Author     : 52209388

--%>




<%@page import="Bean.Reserve"%>
 
<%@page import="java.util.List"%>

<%@page import="Bean.Member"%>

<%@page import="Bean.Section"%>

<%@page import="Bean.Venue"%>
 
<%@page import="Bean.Movie"%>

<%@page import="Utility.Opt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
 
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Shopping Cart</title>
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
  <style id="holderjs-style" type="text/css">.holderjs-fluid {font-size:16px;font-weight:bold;text-align:center;font-family:sans-serif;margin:0}
      table{
          float:left;
          margin:10px;
      }
      
      #foot{
          clear:both;
      }
  
  </style>
 
        <script type='text/javascript'>
            function checkToPay(topay,lpts)
            {               
                if((document.getElementById("loyalty").checked == true)&&(lpts/100<=topay))
                     document.getElementById('toPay').innerHTML=topay-lpts/100;
                else if((document.getElementById("loyalty").checked == true)&&(lpts/100>topay))
                    document.getElementById('toPay').innerHTML=0;
                else
                    document.getElementById("toPay").innerHTML=topay;
            }

    </script>

    </head>

    <body>
         <%@include file="/header.jsp" %>

         <% List<Movie> mv=(List<Movie>)request.getAttribute("movieInfo");%>
 
         <% List<Venue> v=(List<Venue>)request.getAttribute("venueInfo");%>

         <% List<Section> s=(List<Section>)request.getAttribute("sectionInfo"); %>
 
         <% Member m=(Member)request.getAttribute("memberInfo"); %>

         <% List<Reserve> rvBooking=(List<Reserve>)request.getAttribute("lsReserve"); %>
 
         <% int total=0;%>

         <div id="header">
            <% Opt opt=new Opt();  
            boolean display=false;
              //  out.println(opt.showHeader()); 
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
                    display=true;
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
            <% int count=0; 
            String sectionStr="";
            String seatStr="";
            String payStr="";
            %>
 
            <% for (Reserve rvb:rvBooking)

            { %>

            <table border="1">

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

                       

                           <% out.println(rvb.getSeat()); %>

                        </td>
 
                    </tr>
                    <tr>

                        <td>

                            Amount

                        </td>
 
                        <td>

                           <% out.println(s.get(count).getPrice()); %>

                        </td>

                    </tr>
                </tbody>

                <% total+=s.get(count).getPrice(); 
                seatStr=seatStr+rvb.getSeat()+",";
                sectionStr=sectionStr+s.get(count).getSectionID()+",";
                payStr=payStr+s.get(count).getPrice()+",";
                %>

                <tfoot>
                    <tr>
                        <td></td>
                        <td> <form action="removeRv" method="post">
 
                    <input type="hidden" name="sectionID" value="<% out.print(s.get(count).getSectionID());%>" />

                    <input type="hidden" name="seat" value="<% out.print(rvb.getSeat());%>" />

                    <input type="submit" value="Remove" />

                    </form>
                        </td>
                    </tr>
                </tfoot>

            </table>

               

            <% count++;} %>
            <% int lpts=m.getLoyalty(); 
          
                        int luse=0;
                        int ladd=0;
                        if(total>=lpts/100)
                        {
                            luse=lpts;
                            ladd=(total-lpts/100)*10;
                        }
                        else
                        {
                            luse=total*100;
                            ladd=0;
                        }
                        %>
            
            <% if(total!=0)
            { %>
            <div id="foot">
            <p>Total Price: 

                <span id="total"><% out.print(total); %></span>

            </p>

            <p>To Pay:
 
                <span id="toPay"><% out.print(total); %></span>

            </p>

            <input type='checkbox' name='loyalty' id='loyalty' onclick='checkToPay(<%out.print(total); %>,<%out.print(m.getLoyalty());%>)'/>Use Loyalty Point
            
           
            
                        
            <form action="confirmPurchase" method="post">
                <input type='hidden' name='SectionID' value='<% out.print(sectionStr);%>' />
                <input type='hidden' name='loyaltyUse' value='<% out.print(luse);%>' />
                <input type='hidden' name='loyaltyAdd' value='<% out.print(ladd);%>' />
                <input type="hidden" name="seat" value="<% out.print(seatStr);%>" />
                <input type="hidden" name="price" value="<% out.print(payStr);%>" />
                <input type="submit" value="Purchase!" />
            </form>
            
             <% }  else {
             out.println("<p>No movie item in the shopping cart! </p><a href='MovieDisplay?Action=MovieDisplay'>Choose some movies now!</a>"); }%>
                            
            <div id="footer">
                <%}else{%>
        
        <img src="http://c0016417.cdn2.cloudfiles.rackspacecloud.com/Bx.jpg"style="margin:0 auto"/>
                <%}%>
         <%@include file="/footer.jsp" %>
        </div>
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