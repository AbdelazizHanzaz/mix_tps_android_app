package com.example.makeg.sqldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.makeg.pojo.Car;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String NAME = "car_db";
    public static final int VERSION = 3;

    private static final String CARS_TABLE_NAME = "cars";
    private static final String CARS_COL_ID = "id";
    private static final String CARS_COL_MODEL = "model";
    private static final String CARS_COL_COLOR = "color";
    private static final String CARS_COL_DPL = "dpl";

    private static final String CREATE_CARS_TABLE = "CREATE TABLE "+CARS_TABLE_NAME+" ("+CARS_COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CARS_COL_MODEL+" TEXT, "+CARS_COL_COLOR+" TEXT, "+CARS_COL_DPL+" REAL )";

    public MyDatabase(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL(CREATE_CARS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
           sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+CARS_TABLE_NAME);
           onCreate(sqLiteDatabase);
    }

    public boolean insertCar(Car car){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(CARS_COL_ID, car.getId());
        values.put(CARS_COL_MODEL, car.getModel());
        values.put(CARS_COL_COLOR, car.getColor());
        values.put(CARS_COL_DPL, car.getDpl());

        long res = db.insert(CARS_TABLE_NAME, null, values);
        return res != -1;
    }

    public long getCarsCount(){
        SQLiteDatabase db = getReadableDatabase();

        return DatabaseUtils.queryNumEntries(db, CARS_TABLE_NAME);
    }

    public void deleteCar(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {""+id};
        db.delete(CARS_TABLE_NAME, "id = ?", args);

    }

    public Car getCar(int id){

        SQLiteDatabase db = getReadableDatabase();
        String[] args = {""+id};


        Cursor cursor = db.rawQuery("SELECT * FROM "+CARS_TABLE_NAME+" WHERE id = ?", args);

        //int idIndex = cursor.getColumnIndex(CARS_COL_ID);
        int modelIndex = cursor.getColumnIndex(CARS_COL_MODEL);
        int colorIndex = cursor.getColumnIndex(CARS_COL_COLOR);
        int dplIndex = cursor.getColumnIndex(CARS_COL_DPL);

        if(cursor.moveToNext()){
            //int cardId = cursor.getInt(idIndex);
            String model = cursor.getString(modelIndex);
            String color = cursor.getString(colorIndex);
            double dpl = cursor.getDouble(dplIndex);

            return new Car(model, color, dpl);
        }
        return null;

    }

    public void deleteAllCars(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(CARS_TABLE_NAME, null, null);
    }

    public List<Car> getCars(){
        SQLiteDatabase db = getReadableDatabase();
        List<Car> carList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+CARS_TABLE_NAME, null);

        int idIndex = cursor.getColumnIndex(CARS_COL_ID);
        int modelIndex = cursor.getColumnIndex(CARS_COL_MODEL);
        int colorIndex = cursor.getColumnIndex(CARS_COL_COLOR);
        int dplIndex = cursor.getColumnIndex(CARS_COL_DPL);

        while (cursor.moveToNext()){
            int cardId = cursor.getInt(idIndex);
            String model = cursor.getString(modelIndex);
            String color = cursor.getString(colorIndex);
            double dpl = cursor.getDouble(dplIndex);
            carList.add(new Car(cardId, model, color, dpl));
        }
        return carList;
    }
}
