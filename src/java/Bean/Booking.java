/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Zhang Yanlin
 */
public class Booking {
    private int issueID;
    private int sectionID;
    private int seat;
    private int payment;
    private String status;
    private int memberID;
    private int uid;
    public int getIssueID(){
    return this.issueID;
    }
    public void setIssueID(int issueID){
    this.issueID=issueID;
    }
    public int getSectionID(){
        return this.sectionID;
    }
    public void setSectionID(int movieID){
        this.sectionID=movieID;
    }
    public int getSeat(){
        return this.seat;
    }
    public void setSeat(int seat){
        this.seat=seat;
    }
    public int getPayment(){
        return this.payment;
    }
    public void setPayment(int payment){
        this.payment=payment;
    }
    public int getMemberID(){
        return this.memberID;
    }
    public void setMemberID(int memberID){
        this.memberID=memberID;
    }   
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public int getUid(){
        return this.uid;
    }
    public void setUid(int uid){
        this.uid=uid;
    }
}