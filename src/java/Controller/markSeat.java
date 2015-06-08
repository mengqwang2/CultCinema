/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.Booking;
import DAO.BookingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mengqwang
 */
public class markSeat extends HttpServlet {

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
             HttpSession session=request.getSession();
            int memberID=0;
            if(session.getAttribute("memberID")!=null)
                memberID=(Integer)session.getAttribute("memberID");
            else if(session.getAttribute("managerID")!=null)
                memberID=1;
            else if(session.getAttribute("officerID")!=null)
                memberID=2;
                    
            int sectionID=Integer.parseInt(request.getParameter("SectionID_m"));
            String seatNo=request.getParameter("seat_m");
            String unmark=request.getParameter("unmark");
            
            int nSeats=0;
            String[] indiSeats=seatNo.split(",");
            nSeats=indiSeats.length;
            
            int[] seatArr;
            seatArr=new int[nSeats];
            for(int i=0;i<nSeats;i++)
            {
                seatArr[i]=Integer.parseInt(indiSeats[i]);
            }
            
            List<Booking> bkInfo=new ArrayList<Booking>();
            
            for(int i=0;i<nSeats;i++)
            {
                Booking indibk=new Booking();
                indibk.setSeat(seatArr[i]);
                indibk.setSectionID(sectionID);
                indibk.setMemberID(memberID);
                indibk.setStatus("P");
                indibk.setPayment(-1);
                bkInfo.add(indibk);
            }
            
            for(Booking bk:bkInfo)
            {
                BookingDAO bkdao=new BookingDAO();
                bkdao.addBkRecord(bk);
            }
            
            nSeats=0;
            String[] unmarkSeat=unmark.split(",");
            nSeats=unmarkSeat.length;
            
            int[] unMark;
            unMark=new int[nSeats];
            for(int i=0;i<nSeats;i++)
            {
                unMark[i]=Integer.parseInt(unmarkSeat[i]);
            }
            
           bkInfo=new ArrayList<Booking>();
            
            for(int i=0;i<nSeats;i++)
            {
                Booking indibk=new Booking();
                indibk.setSeat(unMark[i]);
                indibk.setSectionID(sectionID);
                
                bkInfo.add(indibk);
            }
            
            for(Booking bk:bkInfo)
            {
                BookingDAO bkdao=new BookingDAO();
                bkdao.deleteMarking(bk);
            }
            
            request.getRequestDispatcher("MovieDisplay?Action=MovieDisplay").forward(request, response);
            
        } finally {            
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
