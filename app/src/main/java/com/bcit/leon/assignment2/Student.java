package com.bcit.leon.assignment2;

/**
 * Created by Jens-Lenovo on 2015-03-27.
 */
public class Student {
    public String first_name;
    public String last_name;
    public String email_address;
    public int student_number;

    public Student(String fname, String lname, String email, int sno) {
        first_name = fname;
        last_name = lname;
        email_address = email;
        student_number = sno;
    }

    public String toString() {
        return first_name + " " + last_name + ": " + email_address + " " + student_number;
    }


}
