package com.samples.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    void insert(Student student);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);

    @Query("SELECT * FROM students")
    List<Student> getAllStudents();

    @Query("SELECT * FROM students WHERE _id=:ids")
    Student getStudentById (int[] ids);

    @Query("DELETE FROM students WHERE name=:name")
    void deleteByName (String name);


    //lifeData
    @Query("SELECT * FROM students")
    LiveData<List<Student>> liveStudent();

}
