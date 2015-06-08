<%-- 
    Document   : formate
    Created on : Apr 20, 2013, 4:22:29 PM
    Author     : 52165627
--%>

<%@page import="Bean.Member"%>
<%@page import="Utility.Opt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit information</title>
        
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
        <% Member member = (Member)request.getAttribute("memberInfo");%>
        <script type="text/javascript">
            function validate_form(thisform){
               // with(thisform){
              
                if (md5(document.getElementById("password").value)!=document.getElementById("pword").value){
                    document.getElementById('passwordX').style.display = "";
                     if(document.getElementById("newPassword").value!=document.getElementById("confirmedPassword").value){
                        document.getElementById('newPwordX').style.display = "";
                     }
                    return false;
                }
                if(document.getElementById("newPassword").value!=document.getElementById("confirmedPassword").value){
                    document.getElementById('newPwordX').style.display = "";
                    return false;
                }
                return true;
           // }   
        }
        </script>
    </head>
        <body>
             <%@include file="/header.jsp" %>
        <div id="header">
            <% Opt opt=new Opt();  
            //out.println(opt.showHeader());
            %>
        </div>
       
            <% 
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
                    display=true;
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
            <form onsubmit="return validate_form(this);" method="post" action="changeInfor">
                <fieldset>
                    <input type="hidden" name="todo" id="todo" value="" />
                    <input type="hidden" name="pword" id="pword" value="<%=member.getPassword()%>"/>
                    <input type="hidden" name="action" id="action" value="confirmInfor"/>
                    <label>Member ID</label>
                    <input type="text" name="userID" id="userID" value="<%=member.getMemberID()%>" readonly="readonly"/><br/>
                    <label >Name</label>
                    <input type="text" name='name' id='name' value="<%=member.getName()%>"/><br/>
                    <label >Old Password</label>
                    <input type="password" name='password' id='password'/>
                    <span id="passwordX" style="display: none">Wrong Password</span><br/>
                    <label>New Password</label>
                    <input type="password" name='newPassword' id='newPassword'/><br/>
                    <label>Confirmed Password</label>
                    <input type="password" name='confirmedPassword' id='confirmedPassword'/>
                    <span id="newPwordX" style="display: none">Password doesn't match</span><br/>
                    <label >Address</label>
                    <input type="text" name='address' id='address' value="<%=member.getAddress()%>"/><br/>
                    <label >Tel</label>
                    <input type="text" name='tel' id='tel' value="<%=member.getTel()%>"/><br/>
                    <label >Gender</label> <%String g=member.getGender();%>
                    <input type="radio" name="gender" value="male" id="male" <%if (g.equals("male")){%> checked="checked" <%}%>/> Male 
                    <input type="radio" name="gender" value="male" id="female" <%if (g.equals("female")){%> checked="checked" <%}%>/> Female<br/>
                    <label >Email</label>
                    <input type="text" name='email' id='email' value="<%=member.getMail()%>"/><br/>
                    <label>Loyalty</label>
                    <input type="text" name="loyalty" id="loyalty" value="<%=member.getLoyalty()%>" readonly="readonly"/><br/>
                    <input type="submit" value="Confirm Change" /><br/>
                    <a href="CartRetrieve?action=confirmInfor">Back to previous page</a>
                </fieldset>
            </form>
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
