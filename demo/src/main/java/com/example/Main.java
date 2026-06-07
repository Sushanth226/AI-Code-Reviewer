package com.example;
import java.util.*;

import com.example.model.ReviewResponse;
import com.example.AI.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner sc = new Scanner(System.in);
        //Taking the Code from the user.
        System.out.println("Give me the code with last line As END");
        StringBuilder sb = new StringBuilder();// String builder variable to save the input code.
        try{
        //Take the input code until the new line is "END".
        String line = sc.nextLine();
        while(!line.equals("END")){
            sb.append(line).append("\n"); // Append a newline so the code doesn't get squashed
            line = sc.nextLine();
        }
        
        //Convert the stringBuilder variable which stores the code , to the string variable.
        String s = sb.toString(); // More idiomatic than new String(sb)

        //Create a object for the Review Response.
        ReviewResponse cod = new ReviewResponse();
        cod.addInputCode(s);//save the input code into the object.
        Review_Generator res=new Review_Generator();// Create a Object to Generate the review on the input code.
        res.reviewCode(cod);//Method call in the Review_Generator to generate the review for the input code by the llm.
        System.out.println(cod.returnResponce());//Output the Review on the given code in the terminal.    
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        }
}