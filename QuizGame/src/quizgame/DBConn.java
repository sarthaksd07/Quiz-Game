/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quizgame;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author VISH
 */
public class DBConn {

public Connection dbconnect() throws SQLException{
//Class.forName("com.mysql.jdbc.Driver");  

return DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","root");  
}
public void dbOperation(String level){
try{
Scanner input=new Scanner(System.in);
DBConn dbObj= new DBConn();
Connection con=dbObj.dbconnect();
Statement st=con.createStatement();
Statement st1=con.createStatement();
ResultSet rs1=st1.executeQuery("select IFNULL(max(quizid),0)+1 from quiz");
rs1.next();
int quizid=rs1.getInt(1);
String qury="select * from question where level='"+level+"'";
ResultSet rs=st.executeQuery(qury);
String insSQL="INSERT INTO quiz values(?,?,?,?)";
PreparedStatement ps = con.prepareStatement(insSQL);
while(rs.next()){
System.out.println("Question"+rs.getInt(1)+":"+rs.getString(2));
System.out.println("Option 1: "+rs.getString(3));
System.out.println("Option 2: "+rs.getString(4));
System.out.println("Option 3: "+rs.getString(5));
System.out.println("Option 4: "+rs.getString(6));
int ans=input.nextInt();

ps.setInt(1,quizid);
ps.setInt(2,rs.getInt(1));
String uans; 
if(ans==1)
{
System.out.println("Your Answer: "+rs.getString(3));
uans=rs.getString(3);
}
else if(ans==2){
System.out.println("Your Answer: "+rs.getString(4));
uans=rs.getString(4);
}
else if(ans==3){
System.out.println("Your Answer: "+rs.getString(5));
uans=rs.getString(5);
}
else if(ans==4){
System.out.println("Your Answer: "+rs.getString(6));
uans=rs.getString(6);
}
else{
System.out.println("Your Answer: ");
uans="";
}
ps.setString(3,uans);
ps.setString(4,"OK");
int output1=ps.executeUpdate();
System.out.println("Correct Answer: "+rs.getString(7));

}
dbObj.Result(quizid); 
con.close();
}
catch(Exception e)
{
System.out.println(e);
}
}

public void Result(int quizid){
try{
DBConn dbObj= new DBConn();
Connection con=dbObj.dbconnect();
Statement st=con.createStatement();
Statement st1=con.createStatement();
String rsRptSQL="select q.ques,q.answer,q1.selectedopt from question q, quiz q1 where q1.qid=q.qid and q1.quizid="+quizid;
ResultSet rs1=st1.executeQuery(rsRptSQL);
while(rs1.next()){
System.out.println("Question : "+rs1.getString(1));
System.out.println("Your Answer : "+rs1.getString(3)+ "   Correct Answer: "+rs1.getString(2));
}
String rsSQL="select case when count(1)<=1 then 'Poor' "// Report cases according to Answers
+" when count(1)<=2 then 'Bad'"
+" when count(1)<=3 then 'Good'"
+" when count(1)<=4 then 'Strong'"
+" when count(1)>=5 then 'Very Strong'"
+" end result from question q, quiz q1 where q1.qid=q.qid and q1.quizid="+quizid
+" and q1.selectedOpt=q.answer";
ResultSet rs=st.executeQuery(rsSQL);
rs.next();
String result=rs.getString(1);
System.out.println("Result: "+result);// Result according to answers

}
catch(Exception e)
{
System.out.println(e);
}
}

}
