/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utility.DBConn;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 52165627
 */
public class StatDAO {
    public int getReservedTicket(int sectionID) throws SQLException {
        int ticket=0;
        DBConn db=new DBConn();
        ResultSet rs=null;        
        rs=db.doSelect("SELECT Count(*) AS RC From [Booking] where Section_ID =" + sectionID +" AND Member_ID='1'");
        rs.next();
            ticket=rs.getInt("RC");        
        return ticket;
    }
    public int getSoldTicket(int sectionID) throws SQLException {
        int ticket=0;
        DBConn db=new DBConn();
        ResultSet rs=null;        
        rs=db.doSelect("SELECT Count(*) AS RC From [Booking] where Section_ID =" + sectionID +" AND Member_ID!='1'");
        rs.next();
            ticket=rs.getInt("RC");        
        return ticket;
    }
    public int getTotalTicket(int sectionID) throws SQLException{
        int ticket=0;
        DBConn db=new DBConn();
        ResultSet rs=null;
        String sql="SELECT * FROM [Section] WHERE [Section_ID]="+sectionID;
        rs=db.doSelect(sql);
        int venueID=0;
        if(rs.next())
            venueID=rs.getInt("Venue_ID");        
        ResultSet rs1=null;
        rs1=db.doSelect("SELECT * FROM [Venue] WHERE [Venue_ID]="+venueID);
        if(rs1.next())
            ticket = rs1.getInt("Seats");
        return ticket;
    }
}
