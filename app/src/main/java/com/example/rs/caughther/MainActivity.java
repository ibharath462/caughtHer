package com.example.rs.caughther;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Property> messages;
    static ArrayAdapter<Property> adapter=null;
    static databaseHandler data;
    ListView listView=null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messages = new ArrayList<>();
        data = new databaseHandler(this);

        Intent clipBoardService = new Intent(MainActivity.this,notificationReceiver.class);
        startForegroundService(clipBoardService);


        refresh();

        //startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
    }

    public void refresh(){
        messages = new ArrayList<>();
        try {
            JSONArray res = data.get();
            //Log.d("DAMN",res.toString());
            listView = (ListView) findViewById(R.id.customListView);
            if(res.length()==0){
                Toast.makeText(getApplicationContext(),"Make new entries buddy!",Toast.LENGTH_SHORT).show();
            }
            else {
                for (int i =0; i<res.length(); i++) {
                    JSONObject t = res.getJSONObject(i);
                    messages.add(new Property(t.getString("sender"), t.getString("message")));
                }
                if(adapter == null){
                    adapter = new propertyArrayAdapter(this, 0, messages);
                }else{
                    //Log.d("DAMN","HIIII " + messages);
                    adapter.clear();
                    //Log.d("DAMN","HIIII " + messages);
                    for(Property t : messages){
                        adapter.add(t);
                        //Log.d("DAMN",t.getSender() + "\n");
                    }
                    adapter.notifyDataSetChanged();
                }
                listView.setAdapter(adapter);



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.navigation_home){
            Toast.makeText(getApplicationContext(),"Refreshing...",Toast.LENGTH_SHORT).show();
            Log.d("DAMN","HIIII");
            refresh();

        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds ite`ms to the action bar if it is
        // present.
        //favView = findViewById(R.id.navigation_favorites);
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }
}
