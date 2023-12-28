package com.example.fimostudyplanner.Flashcards;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class flashcardmodel extends ViewModel
{

    private String title;
    private int cardSetCount;

    public flashcardmodel(String header, String desc) {
        this.title = title;
        this.cardSetCount = cardSetCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCardSetCount() {
        return cardSetCount;
    }

    public void setCardSetCount(int cardSetCount) {
        this.cardSetCount = cardSetCount;
    }

    public flashcardmodel get(Class<flashcardmodel> flashcardmodelClass) {
    }
}
