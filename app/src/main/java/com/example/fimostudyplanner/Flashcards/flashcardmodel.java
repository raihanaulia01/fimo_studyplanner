package com.example.fimostudyplanner.Flashcards;

public class flashcardmodel
{
    String header,desc;

    public flashcardmodel(String header, String desc) {
        this.header = header;
        this.desc = desc;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
