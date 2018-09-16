package com.example.rs.caughther;


import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bharath on 9/22/2016.
 */
public class propertyArrayAdapter extends ArrayAdapter {

    private Context context;
    private List<Property> bookProperties;

    public propertyArrayAdapter(Context context, int resource,ArrayList<Property> objects) {
        super(context, resource,objects);

        this.context = context;
        this.bookProperties = objects;
    }

    public View getView(final int position, View convertView, ViewGroup parent){

        Property property = bookProperties.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.property_layout, null);

        TextView sender=(TextView)view.findViewById(R.id.sender);
        TextView message=(TextView)view.findViewById(R.id.message);


        sender.setText(""+property.getSender());
        message.setText("" + property.getMessage());



        return view;

    }
}
