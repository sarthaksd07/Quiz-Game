/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quizgame;
import java.util.Scanner;
/**
 *
 * @author Asus
 */
public class QuizGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner input=new Scanner(System.in);
        DBConn db=new DBConn();
        System.out.println("Please Select the Level:");
        System.out.println("1: EASY , 2: MEDIUM , 3: HARD");//Level Selection
        int level=input.nextInt();
        if (level ==1 )
        {
        System.out.println("Level Select: EASY");
        db.dbOperation("EASY");
        } else if (level==2) 
        {
        System.out.println("Level Select: MEDIUM");
        db.dbOperation("MEDIUM");
        } else if(level==3)
        {
        System.out.println("Level Select: HARD");
        db.dbOperation("HARD");
        } else
        {
        System.out.println("Invalid Value . Please select the Level Correctly(1,2,3)");// invalid Attempt condition
        }
    }

}
