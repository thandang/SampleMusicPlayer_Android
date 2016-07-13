package com.elisoft.samplemusicplayer;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Choreographer;
import android.view.Display;
import android.widget.Toast;

public class MainActivity extends Activity {

    private GLSurfaceView glSurfaceView;
    private boolean rendererSet;
    private Choreographer displayLink;

    private double level0 = 0.1;
    private double level1 = 0.25;
    private double level2 = 0.5;
    private double level3 = 1.0;
    private double level4 = 1.5;
    private double level5 = 2.0;

    private double timeElapsed = 0.0;
    private int addedLevel = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager activityManager
                = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();

        final boolean supportsEs2 =
                configurationInfo.reqGlEsVersion >= 0x20000 || isProbablyEmulator();


        if (supportsEs2) {
            glSurfaceView = new GLSurfaceView(this);

            if (isProbablyEmulator()) {
                // Avoids crashes on startup with some emulator images.
                glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
            }

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            if (width == 0) {
                width = 320;
            }
            int height = size.y;
            if (height == 0) {
                height = 480;
            }

            final RenderSurface renderSurface = new RenderSurface(this, width,
                    height);
            glSurfaceView.setEGLContextClientVersion(2);
            glSurfaceView.setRenderer(renderSurface);
            rendererSet = true;
            displayLink = Choreographer.getInstance();
            displayLink.postFrameCallback(new Choreographer.FrameCallback() {
                @Override
                public void doFrame(long frameTimeNanos) {
                    timeElapsed += 0.1;
                    Log.d("update frame", "update frame");
                    if (timeElapsed >= level5) {
                        timeElapsed = 0;
                        if (addedLevel != 5) {
                            addedLevel = 5;
                            renderSurface.updateBlockAtIndex(5);
                        }
                    } else if (timeElapsed < level5 && timeElapsed >= level4) {
                        if (addedLevel != 4) {
                            addedLevel = 4;
                            renderSurface.updateBlockAtIndex(4);
                        }
                    } else if (timeElapsed < level4 && timeElapsed >= level3) {
                        if (addedLevel != 3) {
                            addedLevel = 3;
                            renderSurface.updateBlockAtIndex(3);
                        }
                    } else if (timeElapsed < level3 && timeElapsed >= level2) {
                        if (addedLevel != 2) {
                            addedLevel = 2;
                            renderSurface.updateBlockAtIndex(2);
                        }
                    } else if (timeElapsed < level2 && timeElapsed >= level1) {
                        if (addedLevel != 1) {
                            addedLevel = 1;
                            renderSurface.updateBlockAtIndex(1);
                        }
                    } else if (timeElapsed < level1 && timeElapsed >= level0) {
                        if (addedLevel != 0) {
                            renderSurface.updateBlockAtIndex(0);
                            addedLevel = 0;
                        }
                    }
                }
            });

            setContentView(glSurfaceView);
        } else {
            // Should never be seen in production, since the manifest filters
            // unsupported devices.
            Toast.makeText(this, "This device does not support OpenGL ES 2.0.",
                    Toast.LENGTH_LONG).show();
            return;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        if (rendererSet) {
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (rendererSet) {
            glSurfaceView.onResume();
        }
    }

    //Private function
    private boolean isProbablyEmulator() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86"));
    }
}
