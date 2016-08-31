package COMP3421_ASS1;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

/**
 * A game object that has a line shape.
 *
 * This class extend GameObject to draw circular shapes.
 *
 * @author damianr
 */
public class LineGameObject extends GameObject {
    private double[] myPoints;
    private double[] myLineColour;

    //Create a LineGameObject from (0,0) to (1,0)
    public LineGameObject(GameObject parent, double[] lineColour) {
        super(parent);
        initPoints(0,0,1,0);
        myLineColour = lineColour;
    }

    //Create a LineGameObject from (x1,y1) to (x2,y2)
    public LineGameObject(GameObject parent,  double x1, double y1,
                          double x2, double y2,
                          double[] lineColour) {
        super(parent);
        initPoints(x1, y1, x2, y2);
        myLineColour = lineColour;
    }

    private void initPoints(double x1, double y1, double x2, double y2) {
        myPoints = new double[]{x1, y1, x2, y2};
    }

    public double[] getMyPoints() {
        return myPoints;
    }

    public void setMyPoints(double[] myPoints) {
        this.myPoints = myPoints;
    }

    public double[] getMyLineColour() {
        return myLineColour;
    }

    public void setMyLineColour(double[] myLineColour) {
        this.myLineColour = myLineColour;
    }

    @Override
    public void drawSelf(GL2 gl) {
        if (myLineColour != null) {
            gl.glBegin(GL2.GL_LINE_STRIP);
                gl.glColor4d(myLineColour[0], myLineColour[1], myLineColour[2], myLineColour[3]);
                gl.glVertex2d(myPoints[0], myPoints[1]);
                gl.glVertex2d(myPoints[2], myPoints[3]);
            gl.glEnd();
        }
    }
}
