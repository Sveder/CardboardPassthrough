package com.sveder.hwopengl;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
/*
* A square drawn in 2 triangles (using TRIANGLE_STRIP).
*/
public class Square {
 private FloatBuffer vertexBuffer;  // Buffer for vertex-array

 private float[] vertices = {  // Vertices for the square
    -1.0f, -1.0f,  0.0f,  // 0. left-bottom
     1.0f, -1.0f,  0.0f,  // 1. right-bottom
    -1.0f,  1.0f,  0.0f,  // 2. left-top
     1.0f,  1.0f,  0.0f   // 3. right-top
 };
 
 float[] texCoords = { // Texture coords for the above face (NEW)
	      0.0f, 1.0f,  // A. left-bottom (NEW)
	      1.0f, 1.0f,  // B. right-bottom (NEW)
	      0.0f, 0.0f,  // C. left-top (NEW)
	      1.0f, 0.0f   // D. right-top (NEW)
	   };

private FloatBuffer texBuffer;

 // Constructor - Setup the vertex buffer
 public Square() {
    // Setup vertex array buffer. Vertices in float. A float has 4 bytes
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

 // Render the shape
 public void draw(GL10 gl) {
    // Enable vertex-array and define its buffer
    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
    
    gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  // Enable texture-coords-array (NEW)
    gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuffer); // Define texture-coords buffer (NEW)
    
    
    // Draw the primitives from the vertex-array directly
    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
    
    
    gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);  // Disable texture-coords-array (NEW)
    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
 }
}