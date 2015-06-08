/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.Booking;
import Bean.Member;
import Bean.Movie;
import Bean.Reserve;
import Bean.Section;
import Bean.Venue;
import DAO.BookingDAO;
import DAO.MemberDAO;
import DAO.MovieDAO;
import DAO.ReserveDAO;
import DAO.SectionDAO;
import DAO.VenueDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 52209388
 */
public class CartRetrieve extends HttpServlet {

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
       doGet(request,response);
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session=request.getSession();
            int memberID = 0;
            if(session.getAttribute("memberID")!=null)
                memberID=(Integer)session.getAttribute("memberID");
            else if(session.getAttribute("managerID")!=null)
                memberID=1;
            else if(session.getAttribute("officerID")!=null)
                memberID=2;
            Reserve v = new Reserve();
            Booking bking=new Booking();
            List<Reserve> rvBooking = new ArrayList<Reserve>();
            List<Movie> movie = new ArrayList<Movie>();
            List<Section> section = new ArrayList<Section>();
            List<Venue> venue = new ArrayList<Venue>();
            List<Booking> bk = new ArrayList<Booking>();
            boolean []refundSec=new boolean[9999];
            v.setMemberID(memberID);
            v.setSectionID(0);
            bking.setMemberID(memberID);
            
            ReserveDAO rvdao=new ReserveDAO();
            rvBooking=rvdao.getReserveList(v);
            //booking info
            BookingDAO bkdao=new BookingDAO();
            bk=bkdao.getBookingList(memberID);
            
            String action = request.getParameter("action");
           
            //member info
            Member mInfo=new Member();
            mInfo.setMemberID(memberID);
            MemberDAO mdao =new MemberDAO();
            mdao.getMember(mInfo);
            
            if(action.equals("shoppingCart"))
            {
            for(Reserve rvb:rvBooking)
            {
                //var declaration
                int sectionID;
                int seat;
                
                //section info
                Section selectSection=new Section();
                SectionDAO sdao=new SectionDAO();
            
                //movie info
                MovieDAO mvdao=new MovieDAO();
            
                //venue info
                Venue vInfo=new Venue();
                VenueDAO vdao=new VenueDAO();
                sectionID=rvb.getSectionID();
                seat=rvb.getSeat();
                sdao.getSection(sectionID, selectSection);
                Movie mvInfo=mvdao.getMovieInfo(selectSection.getMovieID());
                vInfo.setVenueID(selectSection.getVenueID());
                vdao.setVenueObj(vInfo);
             
                movie.add(mvInfo);
                venue.add(vInfo);
                section.add(selectSection);

            }
            }
            else if(action.equals("bkRecord"))
            {
                int count=0;
                for(Booking bks:bk)
                {
                //var declaration
                int sectionID;
                int seat;
                
                //section info
                Section selectSection=new Section();
                SectionDAO sdao=new SectionDAO();
                
                //movie info
                MovieDAO mvdao=new MovieDAO();
            
                //venue info
                Venue vInfo=new Venue();
                VenueDAO vdao=new VenueDAO();
                sectionID=bks.getSectionID();
                seat=bks.getSeat();
                sdao.getSection(sectionID, selectSection);
                if(sdao.isRefundSection(selectSection))
                    refundSec[count]=true;
                else
                    refundSec[count]=false;
                
                Movie mvInfo=mvdao.getMovieInfo(selectSection.getMovieID());
                vInfo.setVenueID(selectSection.getVenueID());
                vdao.setVenueObj(vInfo);
             
                movie.add(mvInfo);
                venue.add(vInfo);
                section.add(selectSection);
                
                count++;
                }
            }
            
            request.setAttribute("memberInfo",mInfo);
            request.setAttribute("movieInfo", movie);
            request.setAttribute("sectionInfo", section);
            request.setAttribute("venueInfo", venue);
            request.setAttribute("bookingInfo", bk);
            request.setAttribute("lsReserve", rvBooking);
            request.setAttribute("refundSec", refundSec);
            if(action.equals("editInfor"))
                request.getRequestDispatcher("editInfor.jsp").forward(request, response);
            else if(action.equals("confirmInfor"))
                request.getRequestDispatcher("confirmInfor.jsp").forward(request, response);
            else if(action.equals("loyalty"))
                request.getRequestDispatcher("loyaltyEnq.jsp").forward(request, response);
            else if(action.equals("bkRecord"))
                request.getRequestDispatcher("bkRecord.jsp").forward(request, response);
            else
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            
        } catch (Exception ex) {
            Logger.getLogger(CartRetrieve.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
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
         doGet(request,response);
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
