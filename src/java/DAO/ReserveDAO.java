/*

 * To change this template, choose Tools | Templates

 * and open the template in the editor.

 */

package DAO;




import Bean.Reserve;
 
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

public class ReserveDAO {

    public void setReserve(Reserve r)

    {

        try {
 
            DBConn db=new DBConn();

            int memberID=r.getMemberID();

            int sectionID=r.getSectionID();

            int seat=r.getSeat();

            

             String sql="INSERT INTO [RESERVE](Member_ID,Section_ID,Seat,Time) VALUES ('"+memberID+"','"+sectionID+"','"+seat+"',GETDATE())";

            db.doUpdate(sql);
 
            db.close();

        } catch (SQLException ex) {

            Logger.getLogger(ReserveDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {

             Logger.getLogger(ReserveDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    

    public List<Reserve> getReserveList(Reserve v) throws Exception
 
    {

        List<Reserve> rvRecord = null;

        try {

             DBConn db=new DBConn();

             rvRecord=new ArrayList<Reserve>();

             ResultSet rs = null;
 
             String sql="";

             if(v.getSectionID()!=0)

                sql="SELECT * FROM [RESERVE] WHERE [Section_ID]="+v.getSectionID()+" AND DATEDIFF(minute,Time,GETDATE())<5";
 
             else if(v.getMemberID()!=0)

                sql="SELECT * FROM [RESERVE] WHERE [Member_ID]="+v.getMemberID()+" AND DATEDIFF(minute,Time,GETDATE())<5";

             rs=db.doSelect(sql);
 
             while(rs.next())

             {

                 int memberID=rs.getInt("Member_ID");

                 int seat=rs.getInt("Seat");

                 int sectionID=rs.getInt("Section_ID");
 
                 Reserve r=new Reserve();

                 r.setMemberID(memberID);

                 r.setSeat(seat);

                 r.setSectionID(sectionID);

                 rvRecord.add(r);
 
             }

             db.close();

        } catch (SQLException ex) {

            Logger.getLogger(ReserveDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
 
        return rvRecord;

    }

    

    public void removeReserve(Reserve r)

    {

        DBConn db=new DBConn();

        String sql="DELETE FROM RESERVE where Section_ID='"+r.getSectionID()+"' AND Seat='"+r.getSeat()+"'";
 
        db.doDelete(sql);

        

    }

    

}