<%-- 
    Document   : contact
    Created on : Apr 27, 2013, 11:52:14 AM
    Author     : 52165627
--%>
<%@page import="Utility.Opt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact</title>
        <!-- Le styles -->
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
  <style id="holderjs-style" type="text/css">
      .holderjs-fluid {font-size:16px;font-weight:bold;text-align:center;font-family:sans-serif;margin:0}
      td{
          padding:20px;
      }  
      #language{text-align: right;}
  </style>
    <script type="text/javascript">
        function changeLanguage(index){
            document.getElementById("language1").style.display="none";
            document.getElementById("language2").style.display="none";
            document.getElementById("language3").style.display="none";
            document.getElementById("language4").style.display="none";
            document.getElementById("language"+index).style.display='block';
        }
        function hide(){
            document.getElementById("language2").style.display="none";
            document.getElementById("language3").style.display="none";
            document.getElementById("language4").style.display="none";
        }
    </script>
    </head>
    <body onload ="hide()">    
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
                <div id="language">
                    <a href="javascript:void(0);" onclick="changeLanguage(1);">English </a>|
                    <a href="javascript:void(0);" onclick="changeLanguage(2);">简体 </a>|
                    <a href="javascript:void(0);" onclick="changeLanguage(3);">繁體 </a>|
                    <a href="javascript:void(0);" onclick="changeLanguage(4);">Français</a>
                </div>
                <div id="language1"> 
                    <p>If you have any enquiry, please contact us</p>
                    <p>There are 3 members in our company</p>
                    <ui>
                        <li>Jenny Wang Email: <a href="mailto:mengqwang2@gmail.com">  mengqwang2@gmail.com</a></li>
                        <li>Benson Zhang Email: <a href="mailto:yanlin.zhang@my.cityu.edu.hk"> yanlin.zhang@my.cityu.edu.hk</a></li>
                        <li>Luli Xing Email: <a href="mailto:yanlin.zhang@my.cityu.edu.hk"> lulyjasmine@gmail.com</a></li>    
                    </ui>
                </div>
                <div id="language2">
                    <p>如果您有任何问题，欢迎联系我们</p>
                    <p>我们公司一共有三名员工</p>
                    <ui>
                        <li>王梦青 电子邮箱:<a href="mailto:mengqwang2@gmail.com">  mengqwang2@gmail.com</a></li>
                        <li>张延林 电子邮箱:<a href="mailto:yanlin.zhang@my.cityu.edu.hk">  yanlin.zhang@my.cityu.edu.hk</a></li>
                        <li>行璐莉 电子邮箱:<a href="mailto:yanlin.zhang@my.cityu.edu.hk"> lulyjasmine@gmail.com</a></li>    
                    </ui>
                </div>
                
                <div id="language3">            
                    <p>如果您有任何问题，欢迎联系我们</p>
                    <p>我們公司一共有三名員工</p>
                    <ui>
                        <li>王夢青 電子郵箱:<a href="mailto:mengqwang2@gmail.com">  mengqwang2@gmail.com</a></li>
                        <li>張延林 電子郵箱:<a href="mailto:yanlin.zhang@my.cityu.edu.hk">  yanlin.zhang@my.cityu.edu.hk</a></li>
                        <li>行璐莉 電子郵箱:<a href="mailto:yanlin.zhang@my.cityu.edu.hk"> lulyjasmine@gmail.com</a></li>    
                    </ui>
                </div>
                <div id="language4">
                    <p>Si vous avon des questions, contactez-nous s'il vous plaît.</p>
                    <p>Il y a trois persons dans notro entreprise.</p>
                    <ui>
                        <li>Mengqing Wang Email:<a href="mailto:yanlin.zhang@my.cityu.edu.hk"> lulyjasmine@gmail.com</a></li>
                        <li>Yanlin Zhang Email:<a href="mailto:yanlin.zhang@my.cityu.edu.hk"> lulyjasmine@gmail.com</a></li>
                        <li>Luli Xing Email:<a href="mailto:yanlin.zhang@my.cityu.edu.hk"> lulyjasmine@gmail.com</a></li>    
                    </ui>
                </div>
        
                
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
