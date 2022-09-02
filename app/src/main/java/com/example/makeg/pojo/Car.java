package com.example.makeg.pojo;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.provider.BaseColumns;

public class Car {

    private int id;
    private String model;
    private String color;
    private double  dpl;
    private Cursor cursor;

    //for constructor for update by id
    public Car(int id, String model, String color, double dpl) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.dpl = dpl;
    }

    //Constructor for Insert without id (auto).
    public Car(String model, String color, double dpl) {
        this.model = model;
        this.color = color;
        this.dpl = dpl;
    }

    @SuppressLint("Range")
    public Car(Cursor cursor) {
        this.cursor = cursor;
        if(this.cursor != null){
            while (cursor.moveToNext()){
                this.id = cursor.getInt(cursor.getColumnIndex("id"));
                this.model = cursor.getString(cursor.getColumnIndex("model"));
                this.color = cursor.getString(cursor.getColumnIndex("color"));
                this.dpl = cursor.getDouble(cursor.getColumnIndex("dpl"));
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getDpl() {
        return dpl;
    }

    public void setDpl(double dpl) {
        this.dpl = dpl;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }
}
