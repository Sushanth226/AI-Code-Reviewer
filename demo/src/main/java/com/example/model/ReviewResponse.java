package com.example.model;

public class ReviewResponse {
    private String inputCode;// Variable for the input code.
    private String outputResponse;// Variable for placing the review got from the AI.
    
    //Constructor of the class.
    public ReviewResponse(){
        this.inputCode="";
        this.outputResponse="";
    }

    //Function to Intialize the Input code variable.
    public void addInputCode(String inputCode){
        this.inputCode=inputCode;
    }
    //Function to Intialize the review variable.
    public void addOutputResponse(String outputResponse){
        this.outputResponse=outputResponse;
    }

    //Function to return the Reviewed Output.
    public String returnResponce(){
        return this.outputResponse;
    }

    //Function to return the Query needed code.
    public String returnInput(){
        return this.inputCode;
    }

}
