package com.example.seniorp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.core.SharedCamera;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
//import com.google.ar.sceneform.ux.ArFragment;

import java.util.EnumSet;
import java.util.Objects;

public class LessonActivity extends AppCompatActivity {

    Session session;
    SharedCamera sharedCamera;
    String cameraId;
    CameraManager cameraManager;
    //ArFragment arCam;
    String shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        Intent intent = getIntent();
        String type = intent.getExtras().getString("shape");
        shape = type;

        TextView title = (TextView)findViewById(R.id.textView6);
        title.setText(type);
        //CameraDevice.StateCallback wrappedCallback = sharedCamera.createARDeviceStateCallback(cameraDeviceCallback, backgroundHandler);
        //cameraManager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
        //cameraManager.openCamera(cameraId, wrappedCallback, backgroundHandler);
        switch (type) {
            case "rectangular prism":
                lesson_prism();
                break;
            case "pyramid":
                lesson_pyramid();
                break;
            case "sphere":
                lesson_sphere();
                break;
            case "cylinder":
                lesson_cylinder();
                break;
            case "rectangle":
                lesson_quad();
                break;
            case "triangle":
                lesson_triangle();
                break;
            case "circle":
                lesson_circle();
                break;
        }

    }



    public boolean checkSupport(){
        ArCoreApk.Availability availability = ArCoreApk.getInstance().checkAvailability(this);
        switch (availability){
            case SUPPORTED_INSTALLED:
                return true;
            case SUPPORTED_NOT_INSTALLED:
                try {
                    ArCoreApk.InstallStatus installStatus = ArCoreApk.getInstance().requestInstall(this, true);
                    switch (installStatus){
                        case INSTALL_REQUESTED:
                            return false;
                        case INSTALLED:
                            return true;
                    }
                }catch (UnavailableException e){
                    Toast.makeText(this, "Not installed", Toast.LENGTH_SHORT).show();
                }
                return false;
            default:
                return false;
        }
    }

    public void createSession() throws UnavailableDeviceNotCompatibleException, UnavailableSdkTooOldException, UnavailableArcoreNotInstalledException, UnavailableApkTooOldException {
        session = new Session(this, EnumSet.of(Session.Feature.SHARED_CAMERA));

        sharedCamera = session.getSharedCamera();
        cameraId = session.getCameraConfig().getCameraId();
        Config config = new Config(session);

        session.configure(config);
    }

    public void lesson_prism(){
        //displays the equation for surface area
        TextView title = (TextView)findViewById(R.id.textView9);
        title.setText("Surface Area of a Square Prism");

        TextView equation = (TextView)findViewById(R.id.textView7);
        equation.setText("Surface Area = 2 * (the area of the base) + (the perimeter * height) \n" +
                "= 2(Length of base * Width of base) + (2(length + width)) * height");

        //displays the equation for volume
        TextView title2 = (TextView)findViewById(R.id.textView8);
        title2.setText("Volume of a Square Prism");

        TextView equation2 = (TextView)findViewById(R.id.textView10);
        equation2.setText("Volume = (the area of the base)^2 * height = \n" +
                "(Length of base * Width of base) * (Length of base * Width of base) * height");
    }

    public void lesson_pyramid(){
        //displays the equation for surface area
        TextView title = (TextView)findViewById(R.id.textView9);
        title.setText("surface area");

        TextView equation = (TextView)findViewById(R.id.textView7);
        equation.setText("");

        //displays the equation for volume
        TextView title2 = (TextView)findViewById(R.id.textView8);
        title2.setText("volume");

        TextView equation2 = (TextView)findViewById(R.id.textView10);
        equation2.setText("Volume = (Area of the base)^2 *(height)/3 =\n" +
                "(length of base * width of the base) * (height)/3");
    }

    public void lesson_sphere(){
        //displays the equation for surface area
        TextView title = (TextView)findViewById(R.id.textView9);
        title.setText("surface area");

        TextView equation = (TextView)findViewById(R.id.textView7);
        equation.setText("4 * pi * radius^2");

        //displays the equation for volume
        TextView title2 = (TextView)findViewById(R.id.textView8);
        title2.setText("volume");

        TextView equation2 = (TextView)findViewById(R.id.textView10);
        equation2.setText("4/3 * pi * radius^3");
    }

    public void lesson_cylinder(){
        //displays the equation for surface area
        TextView title = (TextView)findViewById(R.id.textView9);
        title.setText("surface area");

        TextView equation = (TextView)findViewById(R.id.textView7);
        equation.setText("(2 * pi * radius * height) + (2 * pi * radius^2)");

        //displays the equation for perimeter
        TextView title2 = (TextView)findViewById(R.id.textView8);
        title2.setText("volume");

        TextView equation2 = (TextView)findViewById(R.id.textView10);
        equation2.setText("pi * radius^2 * height");
    }

    public void lesson_triangle(){
        //displays the equation for area
        TextView title = (TextView)findViewById(R.id.textView9);
        title.setText("area");

        TextView equation = (TextView)findViewById(R.id.textView7);
        equation.setText("Area = (height from the base * length of the base)/2");

        //displays the equation for perimeter
        TextView title2 = (TextView)findViewById(R.id.textView8);
        title2.setText("perimeter");

        TextView equation2 = (TextView)findViewById(R.id.textView10);
        equation2.setText("Perimeter = The sum of the length of all three sides = \n" +
                "side 1 + side 2 + side 3");
    }

    public void lesson_quad(){
        //displays the equation for area
        TextView title = (TextView)findViewById(R.id.textView9);
        title.setText("area for a rectangle");

        TextView equation = (TextView)findViewById(R.id.textView7);
        equation.setText("Area = length * width");

        //displays the equation for perimeter
        TextView title2 = (TextView)findViewById(R.id.textView8);
        title2.setText("perimeter for a rectangle");

        TextView equation2 = (TextView)findViewById(R.id.textView10);
        equation2.setText("Perimeter = 2 * (length + width)");
    }

    public void lesson_circle(){
        //displays the equation for area
        TextView title = (TextView)findViewById(R.id.textView9);
        title.setText("area");

        TextView equation = (TextView)findViewById(R.id.textView7);
        equation.setText("Area = pi * radius^2");

        //displays the equation for perimeter
        TextView title2 = (TextView)findViewById(R.id.textView8);
        title2.setText("perimeter");

        TextView equation2 = (TextView)findViewById(R.id.textView10);
        equation2.setText("perimeter = 2 * pi * radius");
    }

    public void lesson(View view){
        Intent intent = new Intent(this, lessonsAndTest.class);
        intent.putExtra("shape", shape);
        startActivity(intent);
    }
}