package com.sveder.hwopengl;


import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES11Ext;
import android.opengl.GLUtils;
import android.util.Log;
/*
* A cube with texture. 
* Define the vertices for only one representative face.
* Render the cube by translating and rotating the face.
*/
public class Cube {
	private static final int GL_TEXTURE_EXTERNAL_OES = 0x8D65;

 private FloatBuffer vertexBuffer; // Buffer for vertex-array
 private FloatBuffer texBuffer;    // Buffer for texture-coords-array (NEW)

 private float[] vertices = { // Vertices for a face
    -1.0f, -1.0f, 0.0f,  // 0. left-bottom-front
     1.0f, -1.0f, 0.0f,  // 1. right-bottom-front
    -1.0f,  1.0f, 0.0f,  // 2. left-top-front
     1.0f,  1.0f, 0.0f   // 3. right-top-front
 };

 float[] texCoords = { // Texture coords for the above face (NEW)
    0.0f, 1.0f,  // A. left-bottom (NEW)
    1.0f, 1.0f,  // B. right-bottom (NEW)
    0.0f, 0.0f,  // C. left-top (NEW)
    1.0f, 0.0f   // D. right-top (NEW)
 };
 int[] textureIDs = new int[1];   // Array for 1 texture-ID (NEW)
private MyGLRenderer parent;
   
 // Constructor - Set up the buffers
 public Cube(MyGLRenderer myGLRenderer) {
	parent = myGLRenderer;
    // Setup vertex-array buffer. Vertices in float. An float has 4 bytes
    ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
    vbb.order(ByteOrder.nativeOrder()); // Use native byte order
    vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
    vertexBuffer.put(vertices);         // Copy data into buffer
    vertexBuffer.position(0);           // Rewind

    // Setup texture-coords-array buffer, in float. An float has 4 bytes (NEW)
    ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4);
    tbb.order(ByteOrder.nativeOrder());
    texBuffer = tbb.asFloatBuffer();
    texBuffer.put(texCoords);
    texBuffer.position(0);
 }
 
 // Draw the shape
 public void draw(GL10 gl) {
    gl.glFrontFace(GL10.GL_CCW);    // Front face in counter-clockwise orientation
    gl.glEnable(GL10.GL_CULL_FACE); // Enable cull face
    gl.glCullFace(GL10.GL_BACK);    // Cull the back face (don't display) 
 
    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
    
    gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  // Enable texture-coords-array (NEW)
    gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuffer); // Define texture-coords buffer (NEW)
    
    // front
    gl.glPushMatrix();
    gl.glTranslatef(0.0f, 0.0f, 1.0f);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    gl.glPopMatrix();

    // left
    gl.glPushMatrix();
    gl.glRotatef(270.0f, 0.0f, 1.0f, 0.0f);
    gl.glTranslatef(0.0f, 0.0f, 1.0f);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    gl.glPopMatrix();

    // back
    gl.glPushMatrix();
    gl.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
    gl.glTranslatef(0.0f, 0.0f, 1.0f);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    gl.glPopMatrix();

    // right
    gl.glPushMatrix();
    gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
    gl.glTranslatef(0.0f, 0.0f, 1.0f);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    gl.glPopMatrix();

    // top
    gl.glPushMatrix();
    gl.glRotatef(270.0f, 1.0f, 0.0f, 0.0f);
    gl.glTranslatef(0.0f, 0.0f, 1.0f);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    gl.glPopMatrix();

    // bottom
    gl.glPushMatrix();
    gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
    gl.glTranslatef(0.0f, 0.0f, 1.0f);
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    gl.glPopMatrix();

    gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  // Disable texture-coords-array (NEW)
    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    gl.glDisable(GL10.GL_CULL_FACE);
	Log.w("hi","lala");
 }

 // Load an image into GL texture
 public void loadTexture(GL10 gl, MainActivity context) {
	gl.glEnable(GL_TEXTURE_EXTERNAL_OES);
    gl.glGenTextures(1, textureIDs, 0); // Generate texture-ID array
    context.startCamera(textureIDs[0]);
    
    gl.glBindTexture(GL_TEXTURE_EXTERNAL_OES, textureIDs[0]);   // Bind to texture ID
    // Set up texture filters
    gl.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
    gl.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
    
	Log.w("hi","moo");
   
//    // Construct an input stream to texture image "res\drawable\nehe.png"
//    InputStream istream = context.getResources().openRawResource(R.drawable.nehe);
//    Bitmap bitmap;
//    try {
//       // Read and decode input as bitmap
//       bitmap = BitmapFactory.decodeStream(istream);
//    } finally {
//       try {
//          istream.close();
//       } catch(IOException e) { }
//    }
//
//    // Build Texture from loaded bitmap for the currently-bind texture ID
//    GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
//    bitmap.recycle();
//    
 
 }
}