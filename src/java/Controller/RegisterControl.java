/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.Member;
import DAO.MemberDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mengqwang
 */
public class RegisterControl extends HttpServlet {

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
        processRequest(request, response);
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
        try {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String tel = request.getParameter("tel");
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");
            
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            String hashPass = hash.toString(16);
            
            Member user=new Member();  
            user.setName(name);  
            user.setPassword(hashPass); 
            user.setAddress(address);
            user.setGender(gender);
            user.setTel(tel);
            user.setMail(email);  
            
        MemberDAO md=new MemberDAO();
        
        boolean bool = false;  
        try {
            bool = md.doUserReg(user);
            
        } catch (SQLException ex) {
            Logger.getLogger(RegisterControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegisterControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        if(bool)  
        {
            int memberID=md.getMemberID(user);
            user.setMemberID(memberID);
            request.setAttribute("member", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerSuccess.jsp");
            dispatcher.forward(request, response);
        }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterControl.class.getName()).log(Level.SEVERE, null, ex);
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
