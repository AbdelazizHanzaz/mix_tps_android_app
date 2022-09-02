package com.example.makeg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.makeg.adapter.CustomNamesAdapter;
import com.example.makeg.componenet.MyBoundService;
import com.example.makeg.componenet.MyForegroundService;
import com.example.makeg.pojo.TestStatic;
import com.example.makeg.ui.CarsActivity;
import com.example.makeg.ui.UseViewPagerActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomNamesAdapter namesAdapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(getBaseContext(), MyForegroundService.class);
        listView = findViewById(R.id.names_listView);


        //namesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        namesAdapter = new CustomNamesAdapter(this);
        listView.setAdapter(namesAdapter);

        namesAdapter.setOnNameClickListener(new CustomNamesAdapter.OnNameClickListener() {
            @Override
            public void onNameClick(String name) {
                makeToast(name);
            }

            @Override
            public void onNameLongClick(String name) {
                 makeToast("LongClick: "+name);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
          switch(item.getItemId()){
              case R.id.clear_list: {
                     stopService(intent);
              }
                   break;

              case R.id.add_name:{

                  makeBottomSheetDialog();

              }
                  break;
              case R.id.notify:{

                  //startForegroundService(intent);
                  ContextCompat.startForegroundService(getBaseContext(), intent);

              }
              break;
              case R.id.cars_activity:{

                  intent = new Intent(this, CarsActivity.class);
                  startActivity(intent);

              }
              break;

              case R.id.viewpager_activity:{
                  //ToastUtil.getMessage(this, "Cars Number is : "+db.getCarsCount());
                  Intent intent = new Intent(getBaseContext(), UseViewPagerActivity.class);
                  startActivity(intent);

              }
              break;

          }

         return true;
    }

    private void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private AlertDialog makeAlertDialog(){
       // View customTitleView = LayoutInflater.from(this).inflate(R.layout.custom_title, null);
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("Message")
                .setIcon(R.drawable.ic_baseline_add_24)
                .setCancelable(false)
                //.setCustomTitle(customTitleView)
                .setTitle("Add Name")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        makeToast("NegativeButton");
                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                makeToast("PositiveButton");
            }
        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                makeToast("NeutralButton");
            }
        }).create();

        return alertDialog;

    }

    private void makeBottomSheetDialog(){
       BottomSheetDialog sheetDialog =  new BottomSheetDialog(this);
                sheetDialog.setContentView(R.layout.bottomsheet_layout);
                sheetDialog.show();


    }


}