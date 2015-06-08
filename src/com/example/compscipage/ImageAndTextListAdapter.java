package com.example.compscipage;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageAndTextListAdapter extends ArrayAdapter<VCard> {

    public ImageAndTextListAdapter(Activity activity, ArrayList<VCard> vCardList) {
        super(activity, 0, vCardList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();
       
        LayoutInflater inflater = activity.getLayoutInflater();

        // Inflate the views from XML
        View imageAndTextView = inflater.inflate(R.layout.imageandtext, null);
        VCard vCard = getItem(position);

        // Load the image and set it on the ImageView
        ImageView imageView = (ImageView) imageAndTextView.findViewById(R.id.image5);
        imageView.setImageBitmap(vCard.getPicture(activity));

        // Set the text on the TextView
        TextView textView = (TextView) imageAndTextView.findViewById(R.id.text13);
        textView.setText(vCard.getDetailSummary());
          
        return imageAndTextView;
    }
}