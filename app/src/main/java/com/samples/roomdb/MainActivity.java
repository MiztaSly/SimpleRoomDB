package com.samples.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView displayTxt;
    private UniversityDatabase universityDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTxt = findViewById(R.id.tvShowTxt);

         universityDatabase = UniversityDatabase.getInstance(this);


       // List<Student> allStudents = universityDatabase.studentDAO().getAllStudents();


        LiveData<List<Student>> liveStudent = universityDatabase.studentDAO().liveStudent();
        liveStudent.observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {

                String toastMessage = "";
                for (Student s: students) {
                    toastMessage += s.getName() + "\n";
                }

                displayTxt.setText(toastMessage);

            }
        });

       // universityDatabase.studentDAO().insert(new Student("magaret","maggi@gmail.com","07730039909"));

        new MinicUpdatingDbAsyncTask().execute();



        //Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();

        //sample delete call method
       //  universityDatabase.studentDAO().deleteByName("Sylvester");
    }


    private class MinicUpdatingDbAsyncTask extends AsyncTask<Void,Void,Void> {




        @Override
        protected Void doInBackground(Void... voids) {

            ArrayList<Student> students = new ArrayList<>();
            students.add(new Student("Jerry","kom@gmail.com", "099038303"));
            students.add(new Student("Okon","cool@gmail.com", "099038303"));
            students.add(new Student("Tommy","tommy@gmail.com", "099038303"));
            students.add(new Student("Capstone","cappu@gmail.com", "099038303"));

            for (Student s: students) {
                try {
                    Thread.sleep(2000);
                    universityDatabase.studentDAO().insert(s);

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }
}
