package com.example.preperation_final_2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long ID;
    private String name;
    private String lastname;
    private int age;
    private String faculty;
    private int year;

    public Student(String name, String lastname, int age, String faculty, int year) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.faculty = faculty;
        this.year = year;
    }
}
