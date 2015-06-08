/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.Timestamp;

/**
 *
 * @author Zhang Yanlin
 */
public class Section {
    private int sectionID;

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int MovieID) {
        this.MovieID = MovieID;
    }

    public Timestamp getTime() {
        return Time;
    }

    public void setTime(Timestamp Time) {
        this.Time = Time;
    }

    public int getVenueID() {
        return venueID;
    }

    public void setVenueID(int venueID) {
        this.venueID = venueID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getUid(){
        return uid;
    }
    public void setUid(int uid){
        this.uid=uid;
    }
    private int MovieID;
    private Timestamp Time;
    private int venueID;
    private int price;
    private int uid;
    
}
