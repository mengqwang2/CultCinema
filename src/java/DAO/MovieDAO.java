/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author 52165627
 */
import Bean.Movie;
import Bean.Section;
import Utility.DBConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieDAO {
   
    public List<Movie> getMovieList() throws SQLException {   
    List<Movie> movieList=new ArrayList<Movie>(); 
    DBConn db=new DBConn(); 
    ResultSet rs = null;
    int uid=0;
    rs=db.doSelect("SELECT * FROM [MOVIE]"); 
         while(rs.next()){        
         int movieID=rs.getInt("movie_id");
         String name=rs.getString("name");
         int duration=rs.getInt("duration");
         String description=rs.getString("description");
         String director = rs.getString("director");
         String cast=rs.getString("cast");
         String category = rs.getString("category");
         String language = rs.getString("language");
         String src=rs.getString("src");

         Movie movie=new Movie();
         movie.setMovieID(movieID);
         movie.setName(name);
         movie.setDuration(duration);
         movie.setDescription(description);
         //movie.setPoster;
         movie.setDirector(director);
         movie.setCast(cast);
         movie.setCategory(category);
         movie.setLanguage(language);
         movie.setSrc(src);
         movie.setUid(uid);
         movieList.add(movie);
         uid++;
        }   
       return movieList;
       }
public List<Movie> getMovieListByDate(String date) throws SQLException {   
    List<Movie> movieList=new ArrayList<Movie>(); 
    DBConn db=new DBConn(); 
    ResultSet rs = null;
    int uid=0;
    String sql="select * from MOVIE where Movie_ID in (select Movie_ID FROM SECTION where Time>='"+date+"' and Time-1<'"+date+"')"; 
    rs=db.doSelect(sql); 
         while(rs.next()){        
         int movieID=rs.getInt("movie_id");
         String name=rs.getString("name");
         int duration=rs.getInt("duration");
         String description=rs.getString("description");
         String director = rs.getString("director");
         String cast=rs.getString("cast");
         String category = rs.getString("category");
         String language = rs.getString("language");
         String src=rs.getString("src");

         Movie movie=new Movie();
         movie.setMovieID(movieID);
         movie.setName(name);
         movie.setDuration(duration);
         movie.setDescription(description);
         //movie.setPoster;
         movie.setDirector(director);
         movie.setCast(cast);
         movie.setCategory(category);
         movie.setLanguage(language);
         movie.setSrc(src);
         movie.setUid(uid);
         movieList.add(movie);
         uid++;
        }   
       return movieList;
       }

   public List<Section> getSectionList(int movieID) throws SQLException {      
        int uid=0;
        List<Section> sectionList=new ArrayList<Section>(); 
        DBConn db=new DBConn(); 
        ResultSet rs = null;
        rs=db.doSelect("SELECT * FROM [SECTION] where Movie_ID=" + movieID);
        while(rs.next()){     
            Timestamp time=rs.getTimestamp("Time");
            int price=rs.getInt("price");
            int SectionID=rs.getInt("Section_ID");
            int VenueID=rs.getInt("Venue_ID");
            Section section = new Section();
            section.setPrice(price);
            section.setTime(time);
            section.setSectionID(SectionID);
            section.setUid(uid);
            section.setVenueID(VenueID);
            sectionList.add(section);
            uid++;
       } 
       return sectionList;
   }
   

   public void addMovie (Movie movie) throws SQLException{
       String name = movie.getName();
       int duration = movie.getDuration();
       String description = movie.getDescription();
       String director = movie.getDirector();
       String cast = movie.getCast();
       String category = movie.getCategory();
       String language = movie.getLanguage();
       String src=movie.getSrc();
       
       DBConn db=new DBConn(); 
       String sql="INSERT INTO [MOVIE](Name,Duration,Description,Director,Cast,Category,Language,src) VALUES('"+name+"','"+duration+"','"+description+"','"+director+"','"+cast+"','"+category+"','"+language+"','"+src+"')";
       db.doInsert(sql);
   }
   public void editMovie (Movie movie) throws SQLException{
       String name = movie.getName();
       int duration = movie.getDuration();
       String description = movie.getDescription();
       String director = movie.getDirector();
       String cast = movie.getCast();
       String category = movie.getCategory();
       String language = movie.getLanguage();
       String src=movie.getSrc();
       int movieID = movie.getMovieID();
       DBConn db=new DBConn(); 
       String sqls="UPDATE [MOVIE] SET Name = '"+name+"', Duration ='"+duration+"',Description='"+description+"',Director='"+director+"',Cast='"+cast+"',Category='"+category+"',Language='"+language+"',src='"+src+"' WHERE Movie_Id="+movieID;
       db.doUpdate(sqls);
   }

   public void deleteMovie(int movieID) throws SQLException{
       DBConn db=new DBConn(); 
       db.doDelete("DELETE FROM [Movie] WHERE Movie_ID =" + movieID);       
       List<Section> sections = getSectionList(movieID); 
        for (Section section: sections ){
            db.doDelete("DELETE FROM [Booking] WHERE Section_ID=" + section.getSectionID());
        }
       db.doDelete("DELETE FROM [Section] WHERE Movie_ID=" + movieID);
   }

   public Movie getMovieInfo(int movieID)
   {
       Movie mv=new Movie();
        try {
            
            DBConn db=new DBConn();
            ResultSet rs=null;
            String sql="SELECT * FROM [MOVIE] WHERE Movie_ID="+movieID;
            rs=db.doSelect(sql);
            while(rs.next())
            {
                int mvID=rs.getInt("Movie_ID");
                String mvName=rs.getString("Name");
                int mvDur=rs.getInt("Duration");
                String mvDes=rs.getString("Description");
                //get image
                String mvDir=rs.getString("Director");
                String mvCast=rs.getString("Cast");
                String mvCat=rs.getString("Category");
                String mvLang=rs.getString("Language");
                mv.setMovieID(mvID);
                mv.setName(mvName);
                mv.setDuration(mvDur);
                mv.setDirector(mvDir);
                mv.setDescription(mvDes);
                mv.setLanguage(mvLang);
                //mv.setPoster();
                mv.setCategory(mvCat);
                mv.setCast(mvCast);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
   }
   

}
