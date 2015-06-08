/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

/**
 *
 * @author Zhang Yanlin
 */
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DB {
  public static DataSource getDataSource() throws NamingException {
    Context initCtx = new InitialContext();
    Context envCtx = (Context)initCtx.lookup("java:comp/env");
    return (DataSource)envCtx.lookup("jdbc/CultCinema");
  }
}
