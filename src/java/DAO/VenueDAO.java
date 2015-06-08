/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Venue;
import Utility.DBConn;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mengqwang
 */
public class VenueDAO {
    public void setVenueObj(Venue sv) throws SQLException
    {
        int vid=sv.getVenueID();
        DBConn db=new DBConn();
        ResultSet rs=null;
        String sql="SELECT * FROM [VENUE] WHERE [Venue_ID]="+vid;
        rs=db.doSelect(sql);
        String house=null;
        int row=0;
        int col=0;
        int seats=0;
        if(rs.next())
        {
            house=rs.getString("House");
            row=rs.getInt("RowNo");
            col=rs.getInt("ColumnNo");
            seats=rs.getInt("Seats");
        }
        sv.setColumn(col);
        sv.setRow(row);
        sv.setHouse(house);
        sv.setSeats(seats);
    }
}
