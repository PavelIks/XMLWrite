package com.example.xmlwrite;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView v = (ListView)findViewById(R.id.userView);

        XmlPullParser xpp = getResources().getXml(R.xml.users);
        UserResourceParser parser = new UserResourceParser();
        if(parser.parse(xpp)){
           for(User user : parser.getUsers()) {
               Log.d("XML", user.toString());
           }

           ArrayAdapter<User> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, parser.getUsers());
           v.setAdapter(adapter);
        }
    }
}