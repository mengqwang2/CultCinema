/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Zhang Yanlin
 */
public class Loyalty {
    private int Loyalty;
    private int memberID;
    public int getLoyalty(){
        return this.Loyalty;
    }
    public void setLoyalty(int loyalty){
        this.Loyalty=loyalty;
    }
    public int getMemberID(){
        return this.memberID;
    }
    public void setMemberID(int memberID){
        this.memberID=memberID;
    }
    
}
