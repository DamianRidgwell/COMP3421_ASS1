package COMP3421_ASS1;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

/**
 * A game object that has a circular shape.
 *
 * This class extend GameObject to draw circular shapes.
 *
 * @author damianr
 */
public class CircularGameObject extends GameObject {
    private static final int NUM_TRIANGLES = 32;
    private static final double THETA = Math.toRadians(360.0 / NUM_TRIANGLES);

    private double[] myPoints;
    private double[] myFillColour;
    private double[] myLineColour;

    //Create a CircularGameObject with centre 0,0 and radius 1
    public CircularGameObject(GameObject parent, double[] fillColour, double[] lineColour) {
        super(parent);

        initPoints(1.0);
        myFillColour = fillColour;
        myLineColour = lineColour;
    }

    //Create a CircularGameObject with centre 0,0 and a given radius
    public CircularGameObject(GameObject parent, double radius,double[] fillColour, double[] lineColour) {
        super(parent);

        initPoints(radius);
        myFillColour = fillColour;
        myLineColour = lineColour;
    }

    private void initPoints(double radius) {
        myPoints = new double[(NUM_TRIANGLES + 2) * 2];   //array for 32 triangle circle = 33 vertices
        myPoints[0] = 0;
        myPoints[1] = 0;

        for (int i = 0; i < NUM_TRIANGLES + 1; i++) {
            myPoints[2 + (2 * i)] = radius * Math.cos(i * THETA);
            myPoints[3 + (2 * i)] = radius * Math.sin(i * THETA);
        }
    }

    public double[] getMyFillColour() {
        return myFillColour;
    }

    public void setMyFillColour(double[] myFillColour) {
        this.myFillColour = myFillColour;
    }

    public double[] getMyLineColour() {
        return myLineColour;
    }

    public void setMyLineColour(double[] myLineColour) {
        this.myLineColour = myLineColour;
    }

    @Override
    public void drawSelf(GL2 gl) {
        if (myFillColour != null) {
            gl.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int i = 0; i < (myPoints.length); i += 2) {
                gl.glColor4d(myFillColour[0], myFillColour[1], myFillColour[2], myFillColour[3]);
                gl.glVertex2d(myPoints[i], myPoints[i+1]);
            }
            gl.glEnd();
        }

        if (myLineColour != null) {
            gl.glBegin(GL2.GL_LINE_STRIP);
            gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
            for (int i = 2; i < myPoints.length; i += 2) {
                gl.glColor4d(myLineColour[0], myLineColour[1], myLineColour[2], myLineColour[3]);
                gl.glVertex2d(myPoints[i], myPoints[i + 1]);
            }
            gl.glEnd();
        }
    }
}
