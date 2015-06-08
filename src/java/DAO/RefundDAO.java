/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Booking;
import Bean.Movie;
import Utility.DBConn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 52165627
 */
public class RefundDAO {
    public List<Booking> getRefundList() throws SQLException {   
    List<Booking> refundList =new ArrayList<Booking>(); 
    DBConn db=new DBConn(); 
    ResultSet rs = null;
    int uid=0;
    rs=db.doSelect("SELECT * FROM [BOOKING] where Status = 'RP'"); 
         while(rs.next()){        
         int issueID=rs.getInt("Issue_ID");
         int sectionID=rs.getInt("Section_ID");
         int seat=rs.getInt("Seat");
         int payment=rs.getInt("payment");
         int memberID=rs.getInt("member_ID");

         Booking booking=new Booking();
         booking.setIssueID(issueID);
         booking.setSectionID(sectionID);
         booking.setSeat(seat);
         booking.setPayment(payment);
         booking.setMemberID(memberID);
         booking.setUid(uid);
         refundList.add(booking);
         uid++;
        }   
       return refundList;
    }
    public void deleteBooking(int IssueID) throws SQLException{
        DBConn db=new DBConn(); 
        db.doDelete("DELETE FROM [BOOKING] WHERE Issue_ID =" + IssueID);
    }
}
