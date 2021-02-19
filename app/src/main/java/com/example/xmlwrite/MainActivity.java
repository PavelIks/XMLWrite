package com.example.xmlwrite;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        /*XML*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview1 = (ListView)findViewById(R.id.ListViewXMLID);
        XmlPullParser xpp = getResources().getXml(R.xml.users);
        UserResourceParser parser = new UserResourceParser();
        if(parser.parse(xpp))
        {
           for(User user : parser.getUsers())
           {
               Log.d("XML", user.toString());
           }
           ArrayAdapter<User> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, parser.getUsers());
           listview1.setAdapter(adapter);
        }
    }

    /*SQLite*/
    public void onClickINSERT(View v)
    {
        ListView SQLLISTVIEW = (ListView) findViewById(R.id.ListViewSQLiteID);
        ArrayList<String> product_array_list = new ArrayList<String>();

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER)");
        db.execSQL("INSERT INTO users VALUES('TestName-1', 1)");
        //db.execSQL("DELETE FROM users WHERE name='TestName-1'");
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);

        while (cursor.moveToNext())
        {
            ArrayAdapter<ArrayList> adapter;
            adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, product_array_list);
            product_array_list.add("\nName\t"+ cursor.getString(0) + " Age\t" + cursor.getInt(1));
            SQLLISTVIEW.setAdapter(adapter);
        }
        cursor.close();
        db.close();
    }

    public void onClickSELECT(View v)
    {
        ListView SQLLISTVIEW = (ListView) findViewById(R.id.ListViewSQLiteID);
        ArrayList<String> product_array_list = new ArrayList<String>();

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER)");
        db.execSQL("SELECT * FROM users");
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);

        while (cursor.moveToNext())
        {

        }
        cursor.close();
        db.close();
    }
}