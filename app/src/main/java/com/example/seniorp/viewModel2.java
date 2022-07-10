package com.example.seniorp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
//import com.google.ar.sceneform.ux.ArFragment;

import java.util.EnumSet;

public class viewModel2 extends AppCompatActivity {

    //private ArFragment arCam;
    String shape;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model2);

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



    private void lesson_prism() {
    }

    private void lesson_circle() {
    }

    private void lesson_pyramid(){

    }

    private void lesson_sphere(){

    }

    private void lesson_cylinder(){

    }

    private void lesson_quad(){

    }

    private void lesson_triangle(){

    }
}