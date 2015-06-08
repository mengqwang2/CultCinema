package Utility;  
  
import java.io.*;     
import java.sql.*;     
  
public class DBConn {     
        public static String driver;//定义驱动     
        public static String url;//定义URL     
        public static String user;//定义用户名     
        public static String password;//定义密码     
        public static Connection conn;//定义连接     
        public static Statement stmt;//定义STMT     
        public ResultSet rs;//定义结果集     
        //设置CONN     
        static{     
            try {   
                driver="com.microsoft.jdbc.sqlserver.SQLServerDriver";  
               url="jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad017_db;";
                // url="jdbc:microsoft:sqlserver://w2ksa.cs.cityu.edu.hk:1433;DatabaseName=aiad017_db";  
                user="aiad017";  
                password="aiad017";  
                Class.forName(driver);     
                conn = DriverManager.getConnection(url,user,password);  
            } catch(ClassNotFoundException classnotfoundexception) {     
                  classnotfoundexception.printStackTrace();     
                System.err.println("db: " + classnotfoundexception.getMessage());     
            } catch(SQLException sqlexception) {     
                System.err.println("db.getconn(): " + sqlexception.getMessage());     
            }     
        }     
   
        public DBConn(){     
            this.conn=this.getConn();  
        }     
    
        public Connection getConn(){     
            return this.conn;     
        }     
    
           public void doInsert(String sql) {     
            try {    
                conn=DriverManager.getConnection(url,user,password); 
                stmt = conn.createStatement();     
                int i = stmt.executeUpdate(sql);     
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeInset:" + sqlexception.getMessage());     
            }finally{     
                     
            }     
        }     
   
        public void doDelete(String sql) {     
            try {   
                conn=DriverManager.getConnection(url,user,password); 
                stmt = conn.createStatement();     
                int i = stmt.executeUpdate(sql);     
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeDelete:" + sqlexception.getMessage());     
            }     
        }     
    
        public void doUpdate(String sql) {     
            try {     
                conn=DriverManager.getConnection(url,user,password); 
                stmt = conn.createStatement();     
                int i = stmt.executeUpdate(sql);     
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeUpdate:" + sqlexception.getMessage());     
            }     
        }     
   
        public ResultSet doSelect(String sql) {     
            try {  
                conn=DriverManager.getConnection(url, user, password);
                stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);       
                rs = stmt.executeQuery(sql);   
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeQuery: " + sqlexception.getMessage());     
            }     
            return rs;     
        }     
        /**    
           @Function: Close all the statement and conn int this instance and close the parameter ResultSet   
           @Param: ResultSet   
           @Exception: SQLException,Exception   
          **/    
         public void close(ResultSet rs) throws SQLException, Exception {     
        
           if (rs != null) {     
             rs.close();     
             rs = null;     
           }     
        
           if (stmt != null) {     
             stmt.close();     
             stmt = null;     
           }     
        
           if (conn != null) {     
             conn.close();     
             conn = null;     
           }     
         }     
           
         public void close() throws SQLException, Exception {     
           if (stmt != null) {     
             stmt.close();     
             stmt = null;     
           }     
        
           if (conn != null) {     
             conn.close();     
             conn = null;     
           }     
         }     
        
    }    