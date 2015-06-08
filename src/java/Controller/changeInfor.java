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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 52165627
 */
public class changeInfor extends HttpServlet {

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
 
            java.lang.String IDtemp = request.getParameter("userID");
            int memberID=Integer.parseInt(IDtemp == null || "".equals(IDtemp)?"0":IDtemp);
            String name=request.getParameter("name");
            String password=request.getParameter("newPassword");
            String address=request.getParameter("address");
            String tel=request.getParameter("tel");
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");     
            java.lang.String loyaltyTemp = request.getParameter("loyalty");
            int loyalty=Integer.parseInt(loyaltyTemp == null || "".equals(loyaltyTemp)?"0":loyaltyTemp);
            Member member = new Member();
            member.setMemberID(memberID);
            member.setName(name);
            member.setAddress(address);
            member.setPassword(password);
            member.setGender(gender);
            member.setTel(tel);
            member.setMail(email);
            member.setLoyalty(loyalty);
            MemberDAO mDAO = new MemberDAO();
            mDAO.setMember(member, password, name, address, tel, gender, email, loyalty);
            request.setAttribute("memberInfo",member);  
            request.getRequestDispatcher("confirmInfor.jsp").forward(request, response);

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
