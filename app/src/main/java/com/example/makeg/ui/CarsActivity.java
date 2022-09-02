package com.example.makeg.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.makeg.R;
import com.example.makeg.adapter.CarsAdapter;
import com.example.makeg.pojo.Car;
import com.example.makeg.sqldb.MyDatabase;
import com.example.makeg.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class CarsActivity extends AppCompatActivity {

    private RecyclerView rv;
    private CarsAdapter carsAdapter;
    private MyDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        rv = findViewById(R.id.cars_recyclerView);

         //initialize database
         db = new MyDatabase(this);



        carsAdapter = new CarsAdapter(this);
        carsAdapter.setCars(db.getCars());

        rv.setAdapter(carsAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        carsAdapter.setOnItemClickListener(new CarsAdapter.OnItemClickListener() {
            @Override
            public void onClick(String itemId) {
                ToastUtil.getMessage(getBaseContext(), itemId);
            }

            @Override
            public void onIdClick(String id) {
                ToastUtil.getMessage(getBaseContext(), id);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }


    @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.clear_list: {
               //db.deleteCar(2);
                db.deleteAllCars();
                carsAdapter.setCars(db.getCars());
                carsAdapter.notifyDataSetChanged();
            }
            break;

            case R.id.add_name:{

              db.insertCar(new Car("M2019 Fr", "Red", 690.8));
              carsAdapter.setCars(db.getCars());
              carsAdapter.notifyDataSetChanged();

            }
            break;
            case R.id.notify:{
                ToastUtil.getMessage(this, "Cars Number is : "+db.getCarsCount());


            }
            break;





        }

        return true;
    }
}