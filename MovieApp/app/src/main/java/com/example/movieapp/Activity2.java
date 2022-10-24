package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Activity2 extends AppCompatActivity{

    TextView moviedetails;
    TextView movietitle;
    TextView moviecategory;
    ImageView poster;

    TextView textDerece;
    TextView textYear;
    TextView textActors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent=getIntent();
        String texttitle=intent.getStringExtra("title");
        String textdetail=intent.getStringExtra("details");
        String textcategory=intent.getStringExtra("category");
        String textyear=intent.getStringExtra("year");
        String texderece=intent.getStringExtra("imdb");
        String textactors=intent.getStringExtra("actors");
        String textimage=intent.getStringExtra("image");


        poster=findViewById(R.id.poster);
        movietitle=findViewById(R.id.movietitle);
        moviedetails=findViewById(R.id.moviedetails);
        moviecategory=findViewById(R.id.moviecategory);

        textDerece=findViewById(R.id.textDerece);
        textYear=findViewById(R.id.textYear);
        textActors=findViewById(R.id.textActors);

        movietitle.setText(texttitle);
        moviedetails.setText(textdetail);
        moviecategory.setText(textcategory);

        textDerece.setText(String.format("Imdb:  %s", texderece));
        textYear.setText(String.format("Year:  %s", textyear));
        textActors.setText(String.format("Actors:  %s", textactors));



        Picasso.get().load(textimage).into(poster);


    }
}