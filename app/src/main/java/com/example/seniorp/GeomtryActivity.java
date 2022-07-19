package com.example.seniorp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


// This is the menu activity
// Holds all the buttons and changes to the geometry menu
public class GeomtryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geomtry);
    }

    // these map to the lesson activity with the name sent to the activity
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

    // Test buttons send to lesson3 activity
    public void prismTest(View view){
        lessonTest(view, "rectangular prism");
    }
    public void sphereTest(View view){
        lessonTest(view, "sphere");
    }
    public void pyramidTest(View view){
        lessonTest(view, "pyramid");
    }
    public void cylinderTest(View view){
        lessonTest(view, "cylinder");
    }

    public void quadTest(View view){
        lessonTest(view, "rectangle");
    }
    public void triangleTest(View view){
        lessonTest(view, "triangle");
    }
    public void circleTest(View view){
        lessonTest(view, "circle");
    }

    public void lesson(View view, String type){
        Intent intent = new Intent(this, LessonActivity.class);
        intent.putExtra("shape", type);
        startActivity(intent);
    }


    public void lessonTest(View view, String type){
        Intent intent = new Intent(this, lessons3.class);
        intent.putExtra("shape", type);
        startActivity(intent);
    }
}