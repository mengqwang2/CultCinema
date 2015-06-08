/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.Movie;
import Bean.Section;
import DAO.MovieDAO;
import DAO.SectionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
public class ManageSection extends HttpServlet {

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
            doPost(request, response);
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
            MovieDAO movieDAO = new DAO.MovieDAO();
            SectionDAO secDAO=new SectionDAO();
       try {   
            java.lang.String idTemp = request.getParameter("id");
            int id=Integer.parseInt(idTemp == null || "".equals(idTemp)?"0":idTemp);
            java.lang.String movieIDtemp = request.getParameter("movieID");
            int movieID=Integer.parseInt(movieIDtemp == null || "".equals(movieIDtemp)?"0":movieIDtemp);
            java.lang.String sectionIDtemp = request.getParameter("sectionID");
            int sectionID=Integer.parseInt(sectionIDtemp == null || "".equals(sectionIDtemp)?"0":sectionIDtemp);
            java.lang.String action=request.getParameter("action");    
            java.lang.String venueTemp = request.getParameter("venue");
            int venue=Integer.parseInt(venueTemp == null || "".equals(venueTemp)?"0":venueTemp);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            java.util.Date timeTemp = sdf.parse(request.getParameter("time"));           
            Timestamp time = new Timestamp(timeTemp.getTime());
            
        
            java.lang.String priceTemp = request.getParameter("price");        
            int price = Integer.parseInt(priceTemp == null || "".equals(priceTemp)?"0":priceTemp);
            
        
            Section section =new Section();
            section.setMovieID(movieID);
            section.setPrice(price);           
            section.setVenueID(venue);
            section.setSectionID(sectionID);
            section.setTime(time);

            if(action.equals("DeleteSection")){
                secDAO.deleteSection(sectionID); 
                request.getRequestDispatcher("SectionDisplay?id="+id+"&movieID="+movieID).forward(request, response);
            }
            else if(action.equals("AddSection")){
                secDAO.addSection(section); 
                request.getRequestDispatcher("SectionDisplay?id="+id+"&movieID="+movieID).forward(request, response);
            }
            else if(action.equals("UpdateSection")){
                secDAO.editSection(section); 
                request.getRequestDispatcher("SectionDisplay?id="+id+"&movieID="+movieID).forward(request, response);            
            }else{
                java.lang.String name = request.getParameter("name");                
                request.setAttribute("section",section);   
                request.setAttribute("name",name);
                request.getRequestDispatcher("editSection.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain products from DB", e);
        }catch (ParseException ex) {
            Logger.getLogger(ManageSection.class.getName()).log(Level.SEVERE, null, ex);
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
