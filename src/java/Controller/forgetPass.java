/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.Member;
import DAO.MemberDAO;
import Utility.Email;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.Authenticator;
import org.apache.catalina.Session;

/**
 *
 * @author 52209388
 */
public class forgetPass extends HttpServlet {

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
            throws ServletException, IOException {
        doPost(request, response);
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
        doPost(request, response);
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
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           int memberID=Integer.parseInt(request.getParameter("memberID"));
           Member m=new Member();
           m.setMemberID(memberID);
           MemberDAO md=new MemberDAO();
           md.getMember(m);
           String newPass=m.getTel();
           m.setPassword(newPass);
           md.setMember(m,m.getPassword() ,m.getName(),m.getAddress(),m.getTel(),m.getGender(),m.getMail(),m.getLoyalty());
           request.setAttribute("action", "newPass");
           //email to member about the new password
           Email em=new Email();
           try {
               em.Send(m.getMail(), "This is your new password for cult cinima website", "Your new password is "+newPass);
           } catch (AddressException ex) {
               Logger.getLogger(forgetPass.class.getName()).log(Level.SEVERE, null, ex);
           } catch (MessagingException ex) {
               Logger.getLogger(forgetPass.class.getName()).log(Level.SEVERE, null, ex);
           }
           request.getRequestDispatcher("forgetPass.jsp").forward(request, response);
        } 
        finally {            
            out.close();
        }
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

 
