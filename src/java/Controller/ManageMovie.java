/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.Movie;
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
public class ManageMovie extends HttpServlet {

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
        java.lang.String idTemp = request.getParameter("id");
        int id=Integer.parseInt(idTemp == null || "".equals(idTemp)?"0":idTemp);
        java.lang.String movieIDtemp = request.getParameter("movieID");
        int movieID=Integer.parseInt(movieIDtemp == null || "".equals(movieIDtemp)?"0":movieIDtemp);
        java.lang.String action=request.getParameter("action"); 
        java.lang.String name=request.getParameter("Name");
        java.lang.String durationTemp=request.getParameter("Duration");
        int duration=Integer.parseInt(durationTemp == null || "".equals(durationTemp)?"0":durationTemp);
        java.lang.String description=request.getParameter("Description");
        java.lang.String director=request.getParameter("Director");
        java.lang.String cast=request.getParameter("Cast");
        java.lang.String category=request.getParameter("Category");
        java.lang.String language=request.getParameter("Language");
        java.lang.String src=(request.getParameter("src")!=null?request.getParameter("src"):"");
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDuration(duration);
        movie.setDescription(description);
        movie.setDirector(director);
        movie.setLanguage(language);
        movie.setCast(cast);
        movie.setCategory(category);
        movie.setMovieID(movieID);
        movie.setSrc(src);
        try {      
            if(action.equals("DeleteMovie")){
                movieDAO.deleteMovie(movieID); 
                request.getRequestDispatcher("MovieDisplay").forward(request, response);
            }
            else if(action.equals("AddMovie")){
                movieDAO.addMovie(movie); 
                request.getRequestDispatcher("MovieDisplay").forward(request, response);
            }
            else if(action.equals("UpdateMovie")){
                movieDAO.editMovie(movie); 
                request.getRequestDispatcher("MovieDisplay").forward(request, response);            
            }
            else{
                List<Movie> movies = movieDAO.getMovieList();              
                Movie selectedMovie = movies.get(id);
                request.setAttribute("movie",selectedMovie);
                request.getRequestDispatcher("editMovie.jsp").forward(request, response);            
            }
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
