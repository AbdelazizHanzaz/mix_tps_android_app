package com.example.makeg.room;



import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
}
