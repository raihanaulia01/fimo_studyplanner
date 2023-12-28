package com.example.fimostudyplanner.Flashcards;

public class flashcard_data {
        private String folderTitle;
        private String chapterTitle;
        private List<CardSet> cardSets;
        private String front;
        private String back;
        private boolean isStarred;

        // Constructors, getters, and setters go here

        // Constructor
        public Flashcard(String folderTitle, String chapterTitle, String front, String back) {
            this.folderTitle = folderTitle;
            this.chapterTitle = chapterTitle;
            this.front = front;
            this.back = back;
            this.cardSets = new ArrayList<>();
            this.isStarred = false;
        }

        // Getters and setters
        // ...

}
