<%-- 
    Document   : user_login
    Created on : Apr 16, 2013, 11:00:40 AM
    Author     : mengqwang
--%>

<%@page import="Utility.Opt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>User Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="bst/bootstrap.css" rel="stylesheet">
    <link href="./css/commonCss.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <link href="bst/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="//cdnjs.bootcss.com/ajax/libs/html5shiv/3.6.2/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="http://www.bootcss.com/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="http://www.bootcss.com/assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="http://www.bootcss.com/assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="http://www.bootcss.com/assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="http://www.bootcss.com/assets/ico/favicon.png">
  </head>

  <body>
 <%@include file="/header.jsp" %>
    <div class="container">

      <form class="form-signin" action='login_check' method='POST'>
        <h2 class="form-signin-heading">Please sign in</h2>
        <input class="input-block-level" placeholder="Username" type="text" name="j_username" id="j_username">
        <input class="input-block-level" placeholder="Password" type="password" name='j_password' id='j_password'>
		<input type='hidden' name='user_type' value='member' />
        <label class="checkbox">
          <input value="remember-me" type="checkbox"> Remember me
        </label>
                
                <%String s= ((request.getAttribute("error")!=null)?(String)request.getAttribute("error"):"");%>               
                <%=s%>
              
                
        <button class="btn btn-large btn-primary" type="submit" name="submit">Sign in</button>
        <div style="float:right">
	<a href='register.jsp'>Not register yet?</a>	<br/>
        <a href='forgetPass.jsp'>Forget Password?</a></div>
      </form>
	  <div id='reg'>
                
            </div>
            <div id='forgetPass'>
               
            </div>
        <div id="footer">
         <%@include file="/footer.jsp" %>
        </div> 
    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="bst/jquery.js"></script>
    <script src="bst/bootstrap.js"></script>

  

</body></html>