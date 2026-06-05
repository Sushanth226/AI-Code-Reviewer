package com.example.model;

public class ReviewResponse {
    private String inputCode;
    private String outputResponse;
    ReviewResponse(){
        this.inputCode="";
        this.outputResponse="";
    }
    void addInputCode(String inputCode){
        this.inputCode=inputCode;
    }

    void addOutputResponse(String outputResponse){
        this.outputResponse=outputResponse;
    }
    String returnResponnce(){
        return this.outputResponse;
    }
    String returnInput(){
        return this.inputCode;
    }

}
