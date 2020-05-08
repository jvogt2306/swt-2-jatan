package de.jatan.analysisapplication.services;

public class HelloDto {
    private  String text;

    public HelloDto(String text){
        this.text = text;
    }
    public String getText() {
        return  this.text;
    }
}
