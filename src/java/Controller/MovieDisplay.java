/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.Movie;
import Bean.Section;
import DAO.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 52165627
 */
public class MovieDisplay extends HttpServlet {

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
        MovieDAO movieDAO = new DAO.MovieDAO();
        java.lang.String action=request.getParameter("Action");
        if (action==null)
            action="";
        try {
            HttpSession session = request.getSession();
            List<Movie> movies = movieDAO.getMovieList();            
            session.setAttribute("movies", movies);
            if(action!=null & action.equals("MovieDisplay"))
                request.getRequestDispatcher("movie.jsp").forward(request, response);
            else if(action!=null & action.equals("bydate")){
                movies = movieDAO.getMovieListByDate(request.getParameter("date"));
                  session.setAttribute("movies", movies);
                request.getRequestDispatcher("movie.jsp").forward(request, response);}
            else if(action!=null & action.equals("ManageSeat")){
                request.setAttribute("action", "selectMovie");
                request.getRequestDispatcher("manageSeat.jsp").forward(request, response);
            }
            else if(action!=null & action.equals("Viewstat")){
                request.setAttribute("action", "selectMovie");
                request.getRequestDispatcher("viewstat.jsp").forward(request, response);
            }else
                request.getRequestDispatcher("manageMovie.jsp").forward(request, response);            
        } catch (SQLException e) {
        throw new ServletException("Cannot obtain products from DB", e);
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