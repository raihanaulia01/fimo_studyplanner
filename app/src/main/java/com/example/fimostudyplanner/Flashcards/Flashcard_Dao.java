package com.example.fimostudyplanner.Flashcards;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Adding annotation
// to our Dao class
@androidx.room.Dao
public interface Flashcard_Dao {

    // below method is use to
    // add data to database.
    @Insert
    void insert(FlashcardModal model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(FlashcardModal model);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(FlashcardModal model);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM Flashcard_Subject)")
    void deleteAllFlashcard();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM Flashcard_Subject ORDER BY titleSubjectText DESC")
    LiveData<List<FlashcardModal>> getAllFlashcard();
}
