package org.andresoviedo.app.model3D.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import org.andresoviedo.app.model3D.controller.FaceTouchController;
import org.andresoviedo.app.model3D.controller.TouchController;

import java.io.IOException;

/**
 * This is the actual opengl view. From here we can detect touch gestures for example
 *
 * @author andresoviedo
 */
public class FaceModelSurfaceView extends GLSurfaceView {

    private FaceModelActivity parent;
    private FaceModelRenderer mRenderer;
    private FaceTouchController touchHandler;

    public FaceModelSurfaceView(FaceModelActivity parent) throws IllegalAccessException, IOException {
        super(parent);

        // parent component
        this.parent = parent;

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);
        // xiachao 这里设置背景色透明
        // 设置背景透明，否则一般加载时间长的话会先黑一下，但是也有问题，就是在它之上无法再有View了，因为它是top的，用的时候需要注意，必要的时候将其设置为false
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);
        // This is the actual renderer of the 3D space
        mRenderer = new FaceModelRenderer(this);

        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        // TODO: enable this?
        // setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        touchHandler = new FaceTouchController(this, mRenderer);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return touchHandler.onTouchEvent(event);
    }

    public FaceModelActivity getModelActivity() {
        return parent;
    }

    public FaceModelRenderer getModelRenderer() {
        return mRenderer;
    }

}