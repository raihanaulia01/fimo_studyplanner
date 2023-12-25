package com.example.fimostudyplanner.Flashcards;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    // below line is for setting table name.
    @Entity(tableName = "Flashcard_Subject")
    public class FlashcardModal {

        // below line is to auto increment
        // id for each course.
        @PrimaryKey(autoGenerate = true)

        // variable for our id.
        private int id;

        // below line is a variable
        // for course name.
        private String titleSubjectText;

        @ColumnInfo(name="Flashcard_Chapter")
        private String titleChapterText;

        @ColumnInfo(name="Flashcard_Cards")
        private String Front;
        private String Back;

        public FlashcardModal(String titleSubjectText, String titleChapterText, String front, String back) {
            this.titleSubjectText = titleSubjectText;
            this.titleChapterText = titleChapterText;
            Front = front;
            Back = back;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getTitleSubjectText() {
            return titleSubjectText;
        }

        public String getTitleChapterText() {
            return titleChapterText;
        }

        public String getFront() {
            return Front;
        }

        public String getBack() {
            return Back;
        }
    }
