package com.elisoft.samplemusicplayer;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView;


import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by thandang on 7/11/16.
 */
public class RenderSurface implements GLSurfaceView.Renderer {

    static {
        System.loadLibrary("player");
    }

    private final Context context;
    private ArrayList<InputData>datas;

    public RenderSurface (Context context, int viewWidth, int viewHeight) {
        this.context = context;
        for (int i = 0; i < 6; i++) {
            addNewBlock(new Point((5 - i) * 20 + 20, 0), viewWidth, viewHeight);
        }
        initial_data(datas);
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        PlatformFileUtils.init_asset_manager(context.getAssets());
        setup_screen();
    }

    private void addNewBlock(Point position, int width, int height) {
        Point glPoint = new Point(position.x/width, position.y/height);
        double x = glPoint.x * 2.0 - 1.0;
        InputData item = new InputData((positionX: x, positionY: position.y, sizeStart: 32.0, sizeEnd: 32.0,
                delta: 0.1, isDown: 0, delta2: 0.0,
                currentPositionY: 0.0, secondPostionY: position.y, numberOfStepItem: 5, nextPosition: 0.0);
        datas.add(item);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        on_surface_changed(width, height);
        update_blocks();
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
    private  static  native void initial_data(ArrayList<InputData>inputDatas);
    private  static  native void update_block_at_index(int index);
}