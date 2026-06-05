package com.example;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner sc=new Scanner(System.in);
        System.out.println("Give me the code with last line As END");
        StringBuilder sb=new StringBuilder();
        String line="";
        line=sc.nextLine();
        while(line.equals("END")){
            sb.append(line);
            line=sc.nextLine();
        }
        

    }
}