package com.example.padraigcurtin.gl10tutorial;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class OpenGLRenderer implements Renderer {


    Group root = new Group();
    Cube cube = new Cube(1, 1, 1);

    public OpenGLRenderer() {
        // Initialize our cube.
        Group group = new Group();

        cube.rx = 45;
        cube.ry = 45;
        group.add(cube);
        root = group;
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background color to black ( rgba ).
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        // Enable Smooth Shading, default not really needed.
        gl.glShadeModel(GL10.GL_SMOOTH);
        // Depth buffer setup.
        gl.glClearDepthf(1.0f);
        // Enables depth testing.
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // The type of depth testing to do.
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // Really nice perspective calculations.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_NICEST);
    }

    public void onDrawFrame(GL10 gl) {
        gl.glLoadIdentity();
        // Translates 7 units into the screen and 1.5 units up.
        gl.glTranslatef(0, 1.5f, -7);
        // Draw our flat square.
        //square.draw(gl);
        // Draw our cube.
        //gl.glRotatef(angle, 1.0f, 1.0f, 1.0f);
        cube.rx+=2.0f;
        cube.ry+=2.0f;
        cube.rz+=2.0f;
        //cube.draw(gl);
        root.draw(gl);

        //angle+=2.0f;
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Sets the current view port to the new size.
        gl.glViewport(0, 0, width, height);
        // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // Reset the projection matrix
        gl.glLoadIdentity();
        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 45.0f,
                (float) width / (float) height,
                0.1f, 100.0f);
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // Reset the modelview matrix
        gl.glLoadIdentity();
    }
}