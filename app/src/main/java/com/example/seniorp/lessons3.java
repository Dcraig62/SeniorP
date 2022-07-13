package com.example.seniorp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Objects;

public class lessons3 extends AppCompatActivity {

    private ArFragment arCam;
    //private ModelRenderable modelRenderable;
    int clickNum = 0;
    String shape;
    double answer1;
    double answer2;
    String one;
    String two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons3);
        Toast.makeText(this, "Round to the nearest tenth", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        String type = intent.getExtras().getString("shape");
        shape = type;

        arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
        Random rand = new Random();
        int random  = rand.nextInt(2);
        switch (type) {
            case "rectangular prism":
                lesson_prism(random);
                break;
            case "pyramid":
                lesson_pyramid(random);
                break;
            case "sphere":
                lesson_sphere(random);
                break;
            case "cylinder":
                lesson_cylinder(random);
                break;
            case "rectangle":
                lesson_quad(random);
                break;
            case "triangle":
                lesson_triangle(random);
                break;
            case "circle":
                lesson_circle(random);
                break;
        }

    }

    private void addModel(Anchor anchor, ModelRenderable modelRenderable){
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arCam.getArSceneView().getScene());
        TransformableNode transform = new TransformableNode(arCam.getTransformationSystem());
        transform.setParent(anchorNode);
        transform.setRenderable(modelRenderable);
        transform.select();
    }

    public static boolean checkSystemSupport(Activity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().getGlEsVersion();

            if (Double.parseDouble(openGlVersion) >= 3.0){
                return true;
            } else {
                activity.finish();
                return false;
            }
        } else {
            activity.finish();
            return false;
        }
    }

    public void lesson_prism(int num){
        one = "Surface Area";
        two = "Volume";
        if (num == 1){
            lesson_prism1();
        } else{
            lesson_prism2();
        }
    }

    public void lesson_pyramid(int num){
        one = "Surface Area";
        two = "Volume";
        if (num == 1){
            lesson_pyramid1();
        } else{
            lesson_pyramid2();
        }
    }

    public void lesson_sphere(int num){
        one = "Surface Area";
        two = "Volume";
        if (num == 1){
            lesson_sphere1();
        } else{
            lesson_sphere2();
        }
    }

    public void lesson_cylinder(int num){
        one = "Surface Area";
        two = "Volume";
        if (num == 1){
            lesson_cylinder1();
        } else{
            lesson_cylinder2();
        }
    }

    public void lesson_triangle(int num){
        one = "Area";
        two = "Perimeter";
        if (num == 1){
            lesson_triangle1();
        } else{
            lesson_triangle2();
        }
    }

    public void lesson_quad(int num){
        one = "Area";
        two = "Perimeter";
        if (num == 1){
            lesson_rectangle1();
        } else{
            lesson_rectangle2();
        }
    }

    public void lesson_circle(int num){
        one = "Area";
        two = "Perimeter";
        if (num == 1){
            lesson_circle1();
        } else{
            lesson_circle2();
        }
    }

    public void verify(View view){
        EditText one = (EditText)findViewById(R.id.editTextNumber);
        EditText two = (EditText)findViewById(R.id.editTextNumber2);
        double sa_area = Double.parseDouble(one.getText().toString());
        double volume_perimeter = Double.parseDouble(two.getText().toString());
        boolean saa = false; // the check for the surface area or area
        boolean vp = false; // the check for the volume or perimeter
        if (sa_area == answer1)
            saa = true;
        else
            Toast.makeText(this, one + " is not correct", Toast.LENGTH_SHORT).show();
        if (volume_perimeter == answer2)
            vp = true;
        else
            Toast.makeText(this, two + " is not correct", Toast.LENGTH_SHORT).show();
        if (saa && vp){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            finish();
        }

    }


    public void lesson(){
        Intent intent = new Intent(this, GeomtryActivity.class);
        intent.putExtra("shape", shape);
        startActivity(intent);
    }

    public void lesson_circle1(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Area = pi * r^2 =");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("perimeter = 2r * pi =");

        answer1 = 12.6;
        answer2 = 12.6;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.circler2)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_circle2() {
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Area = pi * r^2 = ");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("perimeter = 2r * pi =");

        answer1 = 38.5;
        answer2 = 22;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.circler3_5)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_cylinder1(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Surface Area = (pi*2rh) + (pi*2*r^2) =");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = pi * r^2 * h =");

        answer1 = 175.9;
        answer2 = 150.8;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.cylinder4r_3h)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_cylinder2(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Surface Area = (pi*2rh) + (pi*2*r^2) =");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = pi * r^2 * h =");

        answer1 = 414.7;
        answer2 = 565.5;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.cylinder6r_5h)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_cylinder3(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Surface Area = (pi*2rh) + (pi*2*r^2) =");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = pi * r^2 * h =");

        answer1 = 219.9;//219.9 //157.1
        answer2 = 157.1;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.cylinder5r_2h)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_pyramid1(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = ((lw)^2)*h/3 =");

        answer1 = 0;
        answer2 = 10.7;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.pyramid2x2x2)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_pyramid2(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = ((lw)^2)*h/3 =");

        answer1 = 0;
        answer2 = 426.7;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.pyramid5x4)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_sphere1(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Surface Area = 4*pi*r^2 ="); // 50.3

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = 4/3 * pi * r^3 =");

        answer1 = 78.5;
        answer2 = 65.4;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.spherer2_5)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_sphere2(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Surface Area = 4*pi*r^2 ="); // 50.3

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = 4/3 * pi * r^3 =");

        answer1 = 201.1;
        answer2 = 268.1;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.spherer4)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_prism1(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Surface Area = 2lw + 2(l + w)h =" ); //232 + 2(2+3)4 = 12 + 40 = 52 meters^2

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = h * ((lw)^2) =");

        answer1 = 24;
        answer2 = 32;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.rectang2x2x2)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_prism2(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Surface Area = 2lw + 2(l + w)h =" ); // 42 + 160

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = h * ((lw)^2) ="); //h = 8 l = 7 w = 3

        answer1 = 202;
        answer2 = 3528;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.rectang7x6x3)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_rectangle1(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Area = lw ="); //8

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Perimeter = 2(l + w) =");

        answer1 = 15;
        answer2 = 16;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.rect3x5)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_rectangle2(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Area = lw ="); //8

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Perimeter = 2(l + w) =");

        answer1 = 28;
        answer2 = 22;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.rect7x4)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_triangle1(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Area = hl/2 ="); //2

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Perimeter = side 1 + side 2 + side 3 =");

        answer1 = 6;
        answer2 = 16;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.triangle5x6x2)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
    public void lesson_triangle2(){
        // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Area = hl/2 ="); //2

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Perimeter = side 1 + side 2 + side 3 =");

        answer1 = 10;
        answer2 = 16;

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.triangle5x6x4)
                        .setIsFilamentGltf(true)
                        .build()
                        .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
                        .exceptionally(throwable -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Something went wrong" + throwable.getMessage()).show();
                            return null;
                        });
            }
        }));
    }
}