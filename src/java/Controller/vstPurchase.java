/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.Booking;
import Bean.Movie;
import Bean.Reserve;
import Bean.Section;
import Bean.Venue;
import DAO.BookingDAO;
import DAO.MovieDAO;
import DAO.ReserveDAO;
import DAO.SectionDAO;
import DAO.VenueDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 52209388
 */
public class vstPurchase extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
           int sectionID=Integer.parseInt(request.getParameter("secid"));
           String seat=request.getParameter("seat_v");
           int price=Integer.parseInt(request.getParameter("pay"));
           int nSeats=0;
           String[] indiSeats=seat.split(",");
           nSeats=indiSeats.length;
            //seat
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
                indibk.setMemberID(0);
                indibk.setStatus("P");
                indibk.setPayment(price);
                bkInfo.add(indibk);
            }
            
            //update booking info
            for(Booking bk:bkInfo)
            {
                BookingDAO bkdao=new BookingDAO();
                bkdao.addBkRecord(bk);
            }
            
            Section selectSection=new Section();
            SectionDAO sdao=new SectionDAO();
            sdao.getSection(sectionID, selectSection);
            MovieDAO mvdao=new MovieDAO();
            Movie mvInfo=mvdao.getMovieInfo(selectSection.getMovieID());
            Venue vInfo=new Venue();
            vInfo.setVenueID(selectSection.getVenueID());
            VenueDAO vdao=new VenueDAO();
            vdao.setVenueObj(vInfo);
            
            request.setAttribute("bookingInfo", bkInfo);
            request.setAttribute("movieInfo", mvInfo);
            request.setAttribute("sectionInfo", selectSection);
            request.setAttribute("venueInfo", vInfo);
            request.setAttribute("nSeats", nSeats);
            request.setAttribute("seats", seat);
            
            request.getRequestDispatcher("purchase.jsp").forward(request, response);
           
           
        } catch (SQLException ex) {
            Logger.getLogger(vstPurchase.class.getName()).log(Level.SEVERE, null, ex);
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
