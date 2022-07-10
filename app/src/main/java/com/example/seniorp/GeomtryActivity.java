package com.example.seniorp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GeomtryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geomtry);
    }

    public void prism(View view){
        lesson(view, "rectangular prism");
    }
    public void sphere(View view){
        lesson(view, "sphere");
    }
    public void pyramid(View view){
        lesson(view, "pyramid");
    }
    public void cylinder(View view){
        lesson(view, "cylinder");
    }

    public void quad(View view){
        lesson(view, "rectangle");
    }
    public void triangle(View view){
        lesson(view, "triangle");
    }
    public void circle(View view){
        lesson(view, "circle");
    }

    public void lesson(View view, String type){
        Intent intent = new Intent(this, LessonActivity.class);
        intent.putExtra("shape", type);
        startActivity(intent);
    }
}