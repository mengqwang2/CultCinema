/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Member;
import Utility.DBConn;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mengqwang
 */
public class MemberDAO {
    public boolean checkUserLogin(Member user) throws Exception{  
            ResultSet rs=null;  
            DBConn db=new DBConn();  
              
            try {  
                    String password=user.getPassword();
                    String password2="";
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    md5.update(password.getBytes());
                    BigInteger hash = new BigInteger(1, md5.digest());
                    String hashPass = hash.toString(16);
                    if(!user.getPassword().equals("")||user.getPassword()!=null){
                        String sql="select [Password] from [Member] where [Member_ID]="+user.getMemberID();
                        rs=db.doSelect(sql);  
                        if(rs.next())
                        {
                            password2=rs.getString(1);
                        }
                        db.close(rs);
                    }
                    boolean res=hashPass.equals(password2);
                    return (res);
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        
            return false;
          
    }  
    
    public boolean doUserReg(Member user) throws SQLException, Exception
    { 
        DBConn db=new DBConn(); 
        
        if((!user.getName().equals("")||user.getName()!=null)&&(!user.getPassword().equals("")||user.getPassword()!=null)&&(!user.getAddress().equals("")||user.getAddress()!=null)&&(!user.getGender().equals("")||user.getGender()!=null)&&(!user.getTel().equals("")||user.getTel()!=null)&&(!user.getMail().equals("")||user.getMail()!=null))   
        {
            db.doUpdate("INSERT INTO [Member] VALUES ('"+user.getPassword()+"','"+user.getName()+"','"+user.getAddress()+"','"+user.getTel()+"','"+user.getGender()+"','"+user.getMail()+"',1000)");
           
            db.close();
            return true;
        }
               
        return false;
    }
    
    public int getMemberID(Member m)
    {
        int memberID=0;
        try {
            DBConn db=new DBConn(); 
            String sql="SELECT [Member_ID] FROM [MEMBER] WHERE [Mail]='"+m.getMail()+"'";
            ResultSet rs=null;
            rs=db.doSelect(sql);
            
            if(rs.next())
            {
                memberID=rs.getInt("Member_ID");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memberID;
    }
    
    public void getMember(Member m)
    {
        try {
            ResultSet rs=null;  
            DBConn db=new DBConn(); 
            String sql="SELECT * FROM [Member] where [Member_ID]="+m.getMemberID();
            
            String pwd = null;
            String name = null;
            String adr = null;
            String tel = null;
            String gender = null;
            String mail = null;
            int loyalty=0;
            rs=db.doSelect(sql);
            if(rs.next())
            {
                pwd=rs.getString("Password");
                name=rs.getString("Name");
                adr=rs.getString("Address");
                tel=rs.getString("Tel");
                gender=rs.getString("Gender");
                mail=rs.getString("Mail");
                loyalty=rs.getInt("Loyalty");
            }
            m.setName(name);
            m.setAddress(adr);
            m.setGender(gender);
            m.setPassword(pwd);
            m.setMail(mail);
            m.setTel(tel);
            m.setLoyalty(loyalty);
            
        } catch (SQLException ex) {
            Logger.getLogger(MemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setMember(Member m,String pwd,String name,String adr,String tel,String gender,String mail,int loyalty)
    {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pwd.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            String hashPass = hash.toString(16);
            DBConn db=new DBConn(); 
            String sql="UPDATE [MEMBER] SET [Password]='"+hashPass+"',[Name]='"+name+"',[Address]='"+adr+"',[Tel]='"+tel+"',[Gender]='"+gender+"',[Mail]='"+mail+"',[Loyalty]="+loyalty+" WHERE [Member_ID]="+m.getMemberID();
            db.doUpdate(sql);
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(MemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
