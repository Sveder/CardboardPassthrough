package com.sveder.hwopengl;


import java.io.IOException;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements SurfaceTexture.OnFrameAvailableListener
{

    private Camera mCamera;
	private MyGLSurfaceView glSurfaceView;
    MyGLRenderer renderer;
	private SurfaceTexture surface;

 
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

	     getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	     
	     glSurfaceView = new MyGLSurfaceView(this);           // Allocate a GLSurfaceView
	     renderer = glSurfaceView.getRenderer();

	     this.setContentView(glSurfaceView);                // This activity sets to GLSurfaceView
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startCamera(int texture)
    {
        surface = new SurfaceTexture(texture);
        surface.setOnFrameAvailableListener(this);
        renderer.setSurface(surface);

        mCamera = Camera.open();

        mCamera.setPreviewTexture(surface);
		mCamera.startPreview();
    }

	@Override
	public void onFrameAvailable(SurfaceTexture arg0) {
    	Log.w("hi","MEOWWWWW");
		glSurfaceView.requestRender();
		
	}

}
