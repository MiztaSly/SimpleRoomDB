package com.samples.roomdb;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    private String name;
    private String email;

    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    /**
     * you can also specify other constructors
     * as you want but they have to be mark
     * with the @Ignore annotation
     * @param name
     * @param email
     * @param phoneNumber
     */
    public Student(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }



    //setter for id

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
