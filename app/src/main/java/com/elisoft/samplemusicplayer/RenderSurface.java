package com.elisoft.samplemusicplayer;


import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView.Renderer;

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

    public RenderSurface (Context context, int viewWidth, int viewHeight) {
        this.context = context;
        for (int i = 0; i < 6; i++) {
            addNewBlock(new Point((5 - i) * 20 + 20, 0), viewWidth, viewHeight, i);
        }
        initial_data(datas);
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        PlatformFileUtils.init_asset_manager(context.getAssets());
        setup_screen();
    }

    private void addNewBlock(Point position, int width, int height, int index) {
        Point glPoint = new Point(position.x/width, position.y/height);
        double x = glPoint.x * 2.0 - 1.0;
        InputData item = new InputData();
        item.positionX = (float)x;
        item.positionY = (float)position.y;
        item.secondPostionY = (float)position.y;
        datas[index] = item;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        on_surface_changed(width, height);
//        update_blocks();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        render_blocks();
    }

    public void updateBlockAtIndex(int index) { //TODO: Invoke from displaylink
        update_block_at_index(index);
    }

    //Private
    private  static  native void setup_screen();
    private  static  native void render_blocks();
    private  static  native void update_blocks();
    private  static  native void on_surface_changed(int width, int height);
    private  static  native void initial_data(InputData inputDatas[]);
    private  static  native void update_block_at_index(int index);
}
