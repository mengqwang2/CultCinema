<%-- 
    Document   : editMovie
    Created on : Apr 14, 2013, 12:51:55 PM
    Author     : 52165627
--%>

<%@page import="Utility.Opt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add movie</title>
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
      
  
  </style>
    <script type="text/javascript" src="./editor/ckeditor.js"></script>
<script type="text/javascript" src="./editor/adapters/jquery.js"></script>
    </head>
    <body>
        <%@include file="/header.jsp" %>
      
            <% Opt opt=new Opt();  
                String type="";
                boolean display=false;
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
                    display=true;
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
        <form action="ManageMovie" method="POST">
            <label>Name</label>
            <input type="text" name="Name"><br/>
            <label>Duration (Minutes) </label>
            <input type="text" name="Duration"><br/>
            <label>Description</label>
            <textarea id="editor1" name="Description"></textarea>
            <script type="text/javascript">
            CKEDITOR.replace( 'editor1' );
            </script>
            
            <label>Director</label>
            <input type="text" name="Director"><br/>
            <label>Cast</label>
            <input type="text" name="Cast"><br/>
            <label>Category</label>
            <input type="text" name="Category"><br/>
            <label>Language</label>
            <input type="text" name="Language"><br/>
            
            <label> Poster src </label>
            <input type="text" name="src" value=""><br/>
            <input type="hidden" name="action" value="AddMovie">
            <input type="submit" value="Add Movie">
        </form>
        <a href="manageMovie.jsp">Return to movie Management site</a>
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
