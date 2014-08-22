package com.sveder.hwopengl;

import android.content.Context;
import android.opengl.GLSurfaceView;


class MyGLSurfaceView extends GLSurfaceView
{
	MyGLRenderer renderer;
	
    public MyGLSurfaceView(Context context)
    {
        super(context);

        setEGLContextClientVersion(1);

        renderer = new MyGLRenderer((MainActivity)context);
        setRenderer((GLSurfaceView.Renderer)renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }
    public MyGLRenderer getRenderer()
    {
        return renderer;
    }
}