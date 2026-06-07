package com.example;
import java.util.*;

import com.example.model.ReviewResponse;
import com.example.AI.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner sc = new Scanner(System.in);
        System.out.println("Give me the code with last line As END");
        StringBuilder sb = new StringBuilder();
        
        String line = sc.nextLine();
        while(!line.equals("END")){
            sb.append(line).append("\n"); // Append a newline so the code doesn't get squashed
            line = sc.nextLine();
        }
        
        String s = sb.toString(); // More idiomatic than new String(sb)
        ReviewResponse cod = new ReviewResponse();
        cod.addInputCode(s);
        Review_Generator res=new Review_Generator();
        res.reviewCode(cod);
        System.out.println(cod.returnResponce());
    }
}