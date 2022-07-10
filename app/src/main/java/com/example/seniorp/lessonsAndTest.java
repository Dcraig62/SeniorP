package com.example.seniorp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.core.SharedCamera;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.EnumSet;
import java.util.Objects;

public class lessonsAndTest extends AppCompatActivity {

    private ArFragment arCam;
    //private ModelRenderable modelRenderable;
    int clickNum = 0;
    String shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons_and_test);

        Intent intent = getIntent();
        String type = intent.getExtras().getString("shape");
        shape = type;

        arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);


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

    public void lesson_prism(){
        //arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Surface Area = 2lw + 2(l + w)h =" );

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = h * ((lw)^2) =");

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.rectang2_h4_w2_l3)
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

    public void lesson_pyramid(){
       // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);


        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = ((lw)^2)*h/3 =");

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.rectang_h4_w2_l3)
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

    public void lesson_sphere(){
       // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Surface Area = 4*pi*r^2 =");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = 4/3 * pi * r^3 =");

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.rectang_h4_w2_l3)
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

    public void lesson_cylinder(){
       // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Surface Area = (pi*2rh) + (pi*2*r^2) =");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Volume = pi * r^2 * h =");

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

    public void lesson_triangle(){
       // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Area = hl/2 =");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Perimeter = side 1 + side 2 + side 3 =");

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.trianglew2_h2)
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

    public void lesson_quad(){
       // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Area = lw =");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("Perimeter = 2(l + w) =");

        arCam.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            clickNum++;
            if (clickNum == 1) {
                Anchor anchor = hitResult.createAnchor();
                ModelRenderable.builder()
                        .setSource(this, R.raw.rectw2_h4)
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

    public void lesson_circle(){
       // arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);

        TextView title = (TextView)findViewById(R.id.textView11);
        title.setText("Area = pi * r^2 =");

        title = (TextView)findViewById(R.id.textView12);
        title.setText("perimeter = 2r * pi =");

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

    public void verify(){
        switch (shape) {
            case "rectangular prism":

                break;
            case "pyramid":

                break;
            case "sphere":

                break;
            case "cylinder":

                break;
            case "rectangle":

                break;
            case "triangle":

                break;
            case "circle":

                break;
        }
    }


    public void lesson(View view){
        Intent intent = new Intent(this, GeomtryActivity.class);
        intent.putExtra("shape", shape);
        startActivity(intent);
    }
}