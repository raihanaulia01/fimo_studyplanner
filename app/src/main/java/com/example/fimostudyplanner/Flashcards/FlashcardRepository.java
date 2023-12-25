package com.example.fimostudyplanner.Flashcards;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FlashcardRepository {

    // below line is the create a variable
    // for dao and list for all courses.
    private Flashcard_Dao flashcardDao;
    private LiveData<List<FlashcardModal>> allFlashcard;

    // creating a constructor for our variables
    // and passing the variables to it.
    public FlashcardRepository(Application application) {
        FlashcardDatabase database = FlashcardDatabase.getInstance(application);
        flashcardDao = database.flashcardDao();
        allFlashcard = flashcardDao.getAllFlashcard();
    }

    // creating a method to insert the data to our database.
    public void insert(FlashcardModal model) {
        new InsertFlashcardAsyncTask(flashcardDao).execute(model);
    }

    // creating a method to update data in database.
    public void update(FlashcardModal model) {
        new UpdateFlashcardAsyncTask(flashcardDao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(FlashcardModal model) {
        new DeleteFlashcardAsyncTask(flashcardDao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteAllFlashcard() {
        new DeleteAllFlashcardAsyncTask(flashcardDao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<FlashcardModal>> getAllFlashcard() {
        return allFlashcard;
    }

    // we are creating a async task method to insert new course.
    private static class InsertFlashcardAsyncTask extends AsyncTask<FlashcardModal, Void, Void> {
        private Flashcard_Dao flashcardDao;

        private InsertFlashcardAsyncTask(Flashcard_Dao flashcardDao) {
            this.flashcardDao = flashcardDao;
        }

        @Override
        protected Void doInBackground(FlashcardModal... model) {
            // below line is use to insert our modal in dao.
            flashcardDao.insert(model[0]);
            return null;
        }
    }

    private static class UpdateFlashcardAsyncTask extends AsyncTask<FlashcardModal, Void, Void> {
        private Flashcard_Dao flashcardDao;

        private UpdateFlashcardAsyncTask(Flashcard_Dao flashcardDao) {
            this.flashcardDao = flashcardDao;
        }

        @Override
        protected Void doInBackground(FlashcardModal... model) {
            // below line is use to insert our modal in dao.
            flashcardDao.update (model[0]);
            return null;
        }
    }
    private static class DeleteFlashcardAsyncTask extends AsyncTask<FlashcardModal, Void, Void> {
        private Flashcard_Dao flashcardDao;

        private DeleteFlashcardAsyncTask(Flashcard_Dao flashcardDao) {
            this.flashcardDao = flashcardDao;
        }

        @Override
        protected Void doInBackground(FlashcardModal... model) {
            // below line is use to insert our modal in dao.
            flashcardDao.delete(model[0]);
            return null;
        }
    }
    private static class DeleteAllFlashcardAsyncTask extends AsyncTask<FlashcardModal, Void, Void> {
        private Flashcard_Dao flashcardDao;

        private DeleteAllFlashcardAsyncTask(Flashcard_Dao flashcardDao) {
            this.flashcardDao = flashcardDao;
        }

        @Override
        protected Void doInBackground(FlashcardModal... model) {
            // below line is use to insert our modal in dao.
            flashcardDao.deleteAllFlashcard();
            return null;
        }
    }
}

