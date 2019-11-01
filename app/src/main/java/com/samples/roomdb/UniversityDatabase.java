package com.samples.roomdb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Student.class}, version = 1)

public abstract class UniversityDatabase extends RoomDatabase {

    public abstract StudentDAO studentDAO();


    private static UniversityDatabase instance;

    public static synchronized UniversityDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, UniversityDatabase.class, "university_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(initialCallback)
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    /**

    //how to create db migration
    private static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
     **/




    //to populate data in the database on creation use room callback

    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialAsyncTask(instance).execute();
        }
    };

    private static class InitialAsyncTask extends AsyncTask<Void,Void,Void> {

        private StudentDAO studentDAO;


        //constructor for asyncTask
        public InitialAsyncTask(UniversityDatabase db) {
            studentDAO = db.studentDAO();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDAO.insert(new Student("Sylvester","ultrasoft101@gmail.com","0557748335"));
            studentDAO.insert(new Student("Favour Akwa","fav@gmail.com","077888335"));

            return null;
        }
    }
}
