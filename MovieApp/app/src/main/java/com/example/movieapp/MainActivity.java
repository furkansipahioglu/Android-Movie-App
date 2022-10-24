package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {




    TextView moviedetails;
    TextView movietitle;
    TextView moviecategory;
    ImageView poster;
    EditText moviesearch;

    public String imageurl;
    public String movieyear;
    public String movieimdb;
    public String movieactors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poster=findViewById(R.id.poster);
        moviesearch=findViewById(R.id.moviesearch);
        movietitle=findViewById(R.id.movietitle);
        moviedetails=findViewById(R.id.moviedetails);
        moviecategory=findViewById(R.id.moviecategory);

        moviedetails.setMovementMethod(new ScrollingMovementMethod());


    }

    /* POSTER TIKLANDIĞINDA */
    public void activity2open(View view) {

        String text1=movietitle.getText().toString();
        String text2=moviedetails.getText().toString();
        String text3=moviecategory.getText().toString();


        Intent intent=new Intent(MainActivity.this,Activity2.class);

        intent.putExtra("title",text1);
        intent.putExtra("details",text2);
        intent.putExtra("category",text3);
        intent.putExtra("image",imageurl);

        intent.putExtra("year",movieyear);
        intent.putExtra("imdb",movieimdb);
        intent.putExtra("actors",movieactors);

        startActivity(intent);

    }
    /* BUTTON TIKLANDIĞINDA */
    public void search(View view) {
        poster.setVisibility(View.VISIBLE);

        String moviename=moviesearch.getText().toString();
        if(moviename.isEmpty()){
            moviesearch.setError("Bir isim girmediniz");
            return;
        }
        String url="https://www.omdbapi.com/?t="+moviename+"&apikey=30e6398d";
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject movie=new JSONObject(response);
                            String result =movie.getString("Response");

                            if(result.equals("True"))
                            {
                                Toast.makeText(MainActivity.this,"Found",Toast.LENGTH_SHORT).show();
                                String title=movie.getString("Title");
                                movietitle.setText(title);
                                String genre=movie.getString("Genre");
                                moviecategory.setText(genre);
                                String details=movie.getString("Plot");
                                moviedetails.setText(details);
                                String movieposter=movie.getString("Poster");
                                imageurl=movieposter;
                                Picasso.get().load(movieposter).into(poster);

                                /*diğer sayfaya gönderilcek burada gösterilmicek*/
                                movieyear=movie.getString("Year");

                                movieimdb=movie.getString("imdbRating");

                                movieactors=movie.getString("Actors");

                                /*##############################################*/
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Movie Not Found", Toast.LENGTH_SHORT).show();
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(request);
    }}

