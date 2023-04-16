package com.example.staffsupportsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SheetListActivity extends AppCompatActivity {
  private ListView sheetList;
  private ArrayAdapter adapter;
  private  long cid;

  private ArrayList listItems = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_list);
        cid = getIntent().getLongExtra("cid",-1);
        Log.i("1234567890","onCreate: "+cid);
        loadListItems();
        sheetList = findViewById(R.id.sheetlist);
        adapter = new ArrayAdapter(this,R.layout.sheet_list,R.id.date_list_item,listItems);
        sheetList.setAdapter(adapter);

    }

    private void loadListItems() {
        Cursor cursor = new DbHelper(this).getDistinctMonths(cid);

        while(cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.DATE_KEY));
            listItems.add(date.substring(3));
        }
    }
}