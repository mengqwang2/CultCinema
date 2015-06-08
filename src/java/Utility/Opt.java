/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author mengqwang
 */
public class Opt {
    
    
    public String showNotLoginNav()
    {

        String opt="<div class=\"navbar-wrapper\"><div class=\"container\"><div class=\"navbar navbar-inverse\"><div class=\"navbar-inner\"><button type=\"button\" class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\"><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button><a class=\"brand\" href=\"index.jsp\">Cult Cinema</a><div class=\"nav-collapse collapse\"><ul class=\"nav\"><li class=\"active\"><a href=\"index.jsp\">Home</a><li><a href=\"MovieDisplay?Action=MovieDisplay\">Movies</a></li><li><a href=\"about.jsp\">About</a></li><li><a href=\"contact.jsp\">Contact</a></li><li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Login<b class=\"caret\"></b></a><ul class=\"dropdown-menu\"><li><a href=\"user_login.jsp\">Member</a></li><li class=\"divider\"></li><li class=\"nav-header\">Staff</li><li><a href=\"manager_login.jsp\">Manager</a></li><li><a href=\"officer_login.jsp\">Officer</a></li></ul></li></ul></div></div></div></div></div>";

      //  opt+="<li><a href='manager_login.jsp'>Manager</a></li>";
      //  opt+="<li><a href='officer_login.jsp'>Officer</a></li></ul>";
        return opt;
    }
    
    public String showLoginNav(int memberID)
    {
        String opt="<div class=\"navbar-wrapper\"><div class=\"container\"><div class=\"navbar navbar-inverse\"><div class=\"navbar-inner\"><button type=\"button\" class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\"><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button><a class=\"brand\" href=\"index.jsp\">Cult Cinema</a><div class=\"nav-collapse collapse\"><ul class=\"nav\"><li class=\"active\"><a href=\"index.jsp\">Home</a></li><li><a href=\"MovieDisplay?Action=MovieDisplay\">Movies</a></li><li><a href=\"about.jsp\">About</a></li><li><a href=\"contact.jsp\">Contact</a></li><li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+memberID+"<b class=\"caret\"></b></a><ul class=\"dropdown-menu\"><li><a href=\"CartRetrieve?action=bkRecord\">Booking Records</a></li><li><a href=\"CartRetrieve?action=loyalty\">Loyalty Point</a></li><li><a href=\"CartRetrieve?action=confirmInfor\">Personal Information</a></li><li><a href=\"CartRetrieve?action=shoppingCart\">Shopping Cart</a></li><li><a href=\"logoffControl\">Log off</a></li></ul></li></ul></div></div></div></div></div>";
        return opt;
    }
    
    public String showLoginNav(String user,int type)  // type=0  manager 
    {
        String opt;
        if(type==0){
            opt="<div class=\"navbar-wrapper\"><div class=\"container\"><div class=\"navbar navbar-inverse\"><div class=\"navbar-inner\"><button type=\"button\" class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\"><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button><a class=\"brand\" href=\"index.jsp\">Cult Cinema</a><div class=\"nav-collapse collapse\"><ul class=\"nav\"><li class=\"active\"><a href=\"index.jsp\">Home</a></li><li><a href=\"MovieDisplay?Action=MovieDisplay\">Movies</a></li><li><a href=\"about.jsp\">About</a></li><li><a href=\"contact.jsp\">Contact</a></li><li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+user+"<b class=\"caret\"></b></a><ul class=\"dropdown-menu\"><li><a href=\"MovieDisplay?Action=ManageMovie\">Manage Movie</a></li><li><a href=\"MovieDisplay?Action=ManageSeat\">Manage seats</a></li><li><a href=\"MovieDisplay?Action=Viewstat\">View Statistics</a></li><li><a href=\"logoffControl\">Log off</a></li></ul></li></ul></div></div></div></div></div>";}
        else
                opt="<div class=\"navbar-wrapper\"><div class=\"container\"><div class=\"navbar navbar-inverse\"><div class=\"navbar-inner\"><button type=\"button\" class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\"><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button><a class=\"brand\" href=\"index.jsp\">Cult Cinema</a><div class=\"nav-collapse collapse\"><ul class=\"nav\"><li class=\"active\"><a href=\"index.jsp\">Home</a></li><li><a href=\"MovieDisplay?Action=MovieDisplay\">Movies</a></li><li><a href=\"about.jsp\">About</a></li><li><a href=\"contact.jsp\">Contact</a></li><li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+user+"<b class=\"caret\"></b></a><ul class=\"dropdown-menu\"><li><a href='RefundControl'>Manage refund</a></li><li><a href='CartRetrieve?action=shoppingCart'>Shopping Cart</a></li><li><a href='logoffControl'>Log off</a></li></ul></div></div></div></div></div>";
        return opt;
    }
    
    public String showItem(String type)
    {
        String opt="";
        if(type.equals("member"))
        {
            opt+="";
        }
        else if(type.equals("manager"))
        {
            opt+="";
        }
        else if(type.equals("officer"))
        {
            opt+="";
        }
        return opt;
    }
    
    public String showHeader()
    {
        String opt="<h1>Cult Cinema Inc</h1>";
        return opt;
    }
    
    public String showFooter()
    {
        String opt="<p>© Copyright 2013 Cult Cinema Inc</p>";
        return opt;
    }
    
}
