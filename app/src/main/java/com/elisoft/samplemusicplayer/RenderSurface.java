package com.elisoft.samplemusicplayer;


import android.content.Context;
import android.graphics.Point;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import models.InputData;

/**
 * Created by thandang on 7/11/16.
 */
public class RenderSurface implements Renderer {
    static {
        System.loadLibrary("player");
    }

    private final Context context;
    private InputData datas[] = new InputData[6];
    private int width;
    private int height;

    public RenderSurface (Context context, int viewWidth, int viewHeight) {
        this.context = context;
        width = viewWidth;
        height = viewHeight;
        PlatformFileUtils.init_asset_manager(context.getAssets());
        for (int i = 0; i < 6; i++) {
            addNewBlock(new Point((5 - i) * 40 + 40, 0), width, height, i);
        }
        initial_data(datas);
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        setup_screen();
    }

    private void addNewBlock(Point position, int width, int height, int index) {
        double x = ((double)position.x / (double) width) * 2.0f - 1.0f;
        InputData item = new InputData();
        item.positionX = (float)x;
        item.positionY = position.y;
        item.secondPostionY = position.y;
        datas[index] = item;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        on_surface_changed(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        render_blocks();
    }

    public void updateBlockAtIndexNative(int index) {
        updateBlockAtIndex(index);
    }

    public void updateBlocks() {
        update_blocks();
    }

    //Private
    private  static  native void setup_screen();
    private  static  native void render_blocks();
    private  static  native void update_blocks();
    private  static  native void on_surface_changed(int width, int height);
    private  static  native void initial_data(InputData inputDatas[]);
    private  static  native void updateBlockAtIndex(int index);
}
