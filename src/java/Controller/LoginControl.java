/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.Manager;
import Bean.Member;
import Bean.Officer;
import DAO.ManagerDAO;
import DAO.MemberDAO;
import DAO.OfficerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

            

/**
 *
 * @author mengqwang
 */
public class LoginControl extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           doPost(request,response);
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String username = request.getParameter("j_username");
       String password = request.getParameter("j_password");
       String user_type= request.getParameter("user_type");
       try
       {
       if(user_type.equals("member"))
       {    boolean bool;
            Member user=new Member();  
           try { 
            user.setMemberID(Integer.parseInt(username));
            user.setPassword(password);
            MemberDAO md=new MemberDAO();  
            bool = false;  
            
                bool = md.checkUserLogin(user);
            } catch (Exception ex) {
                Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
                RequestDispatcher dispatcher = request.getRequestDispatcher("user_login.jsp");
                request.setAttribute("error", "The input username or password is wrong.");
                dispatcher.forward(request, response);
                return;
            }
 
            if(bool){ 
                request.getSession().setAttribute("memberID",user.getMemberID());
              
            }    
            else{
             RequestDispatcher dispatcher = request.getRequestDispatcher("user_login.jsp");
              request.setAttribute("error", "The input username or password is wrong.");
             dispatcher.forward(request, response);
             return;
            }
       }
       else if(user_type.equals("manager"))
       {
           Manager user=new Manager();
           user.setManagerID(username);
           user.setPassword(password);
           ManagerDAO md=new ManagerDAO();
           boolean bool = false;  
            try {
                bool = md.checkUserLogin(user);
            } catch (Exception ex) {
                Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
                 RequestDispatcher dispatcher = request.getRequestDispatcher("manager_login.jsp");
                 request.setAttribute("error", "The input username or password is wrong.");
                dispatcher.forward(request, response);
            }
 
            if(bool&&(md.getLogin(user)==0)){ 
                request.getSession().setAttribute("managerID",user.getManagerID());
                md.setLogin(user);
              
            }
             else if(bool&&(md.getLogin(user)!=0)){
                RequestDispatcher dispatcher = request.getRequestDispatcher("manager_login.jsp");
                 request.setAttribute("error", "You have already sign in in another place.");
                 dispatcher.forward(request, response);
                 return;
                
            }
            else{
                 RequestDispatcher dispatcher = request.getRequestDispatcher("manager_login.jsp");
                 request.setAttribute("error", "The input username or password is wrong.");
                 dispatcher.forward(request, response);
                 return;
            }
       }
       else if(user_type.equals("officer"))
       {
           Officer user=new Officer();
           user.setOfficerID(username);
           user.setPassword(password);
           OfficerDAO od=new OfficerDAO();
           boolean bool = false;  
            try {
                bool = od.checkUserLogin(user);
            } catch (Exception ex) {
                Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
                RequestDispatcher dispatcher = request.getRequestDispatcher("officer_login.jsp");
                request.setAttribute("error", "The input username or password is wrong.");
                dispatcher.forward(request, response);
            }
 
            if(bool&&(od.getLogin(user)==0)){ 
                request.getSession().setAttribute("officerID",user.getOfficerID());
                od.setLogin(user);
            }
            else if(bool&&(od.getLogin(user)!=0)){
                RequestDispatcher dispatcher = request.getRequestDispatcher("officer_login.jsp");
                 request.setAttribute("error", "You have already sign in in another place.");
                 dispatcher.forward(request, response);
                 return;
                
            }
            else{
                 RequestDispatcher dispatcher = request.getRequestDispatcher("officer_login.jsp");
                 request.setAttribute("error", "The input username or password is wrong.");
                 dispatcher.forward(request, response);
                 return;
            }
           
       }
       }catch(Exception e)
       {
           request.getSession().invalidate(); 
           RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
       }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
