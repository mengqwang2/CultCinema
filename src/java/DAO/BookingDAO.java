/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Booking;
import Bean.Movie;
import Bean.Section;
import Utility.DBConn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mengqwang
 */
public class BookingDAO {
     public List<Booking> getBookingList(Section s) throws SQLException
     {
         List<Booking> bookRecord=new ArrayList<Booking>(); 
    DBConn db=new DBConn(); 
    ResultSet rs = null;
    int uid=0;
    String sql="SELECT * FROM [BOOKING] WHERE Section_ID="+s.getSectionID();
    rs=db.doSelect(sql); 
         while(rs.next()){        
         int sectionID=rs.getInt("Section_ID");
         int seatNO=rs.getInt("Seat");
         int payment=rs.getInt("Payment");
         int member=rs.getInt("Member_ID");
         String status=rs.getString("Status");
         
         Booking bk=new Booking();
         bk.setMemberID(member);
         bk.setPayment(payment);
         bk.setSeat(seatNO);
         bk.setSectionID(sectionID);
         bk.setStatus(status);
                 
         bookRecord.add(bk);
         uid++;
         
        }   
       return bookRecord;
       }
     
     public List<Booking> getBookingList(int memberID) throws SQLException
     {
         List<Booking> bookRecord=new ArrayList<Booking>(); 
        DBConn db=new DBConn(); 
        ResultSet rs = null;
        String sql="SELECT * FROM [BOOKING] WHERE Member_ID="+memberID;
        rs=db.doSelect(sql); 
         while(rs.next()){        
         int seatNO=rs.getInt("Seat");
         int payment=rs.getInt("Payment");
         int sectionID=rs.getInt("Section_ID");
         String status=rs.getString("Status");
         int issueID=rs.getInt("Issue_ID");
         
         Booking bk=new Booking();
         bk.setMemberID(memberID);
         bk.setPayment(payment);
         bk.setSeat(seatNO);
         bk.setSectionID(sectionID);
         bk.setStatus(status);
         bk.setIssueID(issueID);
                 
         bookRecord.add(bk);
         
        }   
       return bookRecord;
       }
     
       public void addBkRecord(Booking bk)
       {
         try {
             DBConn db=new DBConn(); 
             int seat=bk.getSeat();
             int section=bk.getSectionID();
             int memberID=bk.getMemberID();
             String status=bk.getStatus();
             int payment=bk.getPayment();
             String sql="INSERT INTO [BOOKING](Section_ID,Seat,Member_ID,Status,Payment) VALUES ('"+section+"','"+seat+"','"+memberID+"','"+status+"','"+payment+"')";
             db.doUpdate(sql);
             db.close();
         } catch (SQLException ex) {
             Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
         } catch (Exception ex) {
             Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
           
       }
       
       public void deleteMarking(Booking bk)
       {
        try {
            DBConn db=new DBConn(); 
            String sql="DELETE FROM [BOOKING] WHERE [Section_ID]="+bk.getSectionID()+" AND [Seat]="+bk.getSeat();
            db.doDelete(sql);
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       
       public void changeRefundStatus(Booking bk)
       {
        try {
            DBConn db=new DBConn(); 
            String sql="SELECT [Status] FROM [BOOKING] WHERE [Issue_ID]="+bk.getIssueID();
            ResultSet rs;
            rs=db.doSelect(sql);
            String status = null;
            if(rs.next())
            {
                status=rs.getString("Status");
                if(status.equals("P"))
                    status="RP";
                else if(status.equals("RP"))
                    status="P";
            }
            sql="UPDATE [BOOKING] SET [Status]='"+status+"' WHERE [Issue_ID]="+bk.getIssueID();
            db.doUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
}

