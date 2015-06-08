/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Manager;
import Bean.Member;
import Utility.DBConn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mengqwang
 */
public class ManagerDAO {
    
    public boolean checkUserLogin(Manager user) throws Exception{  
            ResultSet rs=null;  
            DBConn db=new DBConn();  
              
            try {  
                    String password=user.getPassword();
                    String password2="";
                    if(!user.getPassword().equals("")||user.getPassword()!=null){
                        String sql="select [Password] from [MANAGER] where [Manager_ID]='"+user.getManagerID()+"'";
                        rs=db.doSelect(sql);  
                        if(rs.next())
                        {
                            password2=rs.getString(1);
                        }
                        db.close(rs);
                    }
                    boolean res=password.equals(password2);
                    return (res);
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        
            return false;
          
    }  
    
    public int getLogin(Manager user)
    {
        int login = 0;
        try {
            ResultSet rs=null;
            DBConn db=new DBConn();
            String sql="SELECT [Login] FROM [MANAGER] WHERE [Manager_ID]='"+user.getManagerID()+"'";
            rs=db.doSelect(sql);
            if(rs.next())
            {
                login=rs.getInt("Login");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return login;
    }
    
    public void setLogin(Manager user)
    {
            DBConn db=new DBConn();
            int login=0;
            if(user.getLogin()==0)
                login=1;
            else
                login=0;
            String sql="UPDATE [MANAGER] SET [Login]="+login+" WHERE [Manager_ID]='"+user.getManagerID()+"'";
            db.doUpdate(sql);
    }
    
}
    
