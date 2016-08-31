package COMP3421_ASS1;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

/**
 * A game object that has a polygonal shape.
 * 
 * This class extend GameObject to draw polygonal shapes.
 *
 * TODO: The methods you need to complete are at the bottom of the class
 *
 * @author malcolmr
 */
public class PolygonalGameObject extends GameObject {

    private double[] myPoints;
    private double[] myFillColour;
    private double[] myLineColour;

    /**
     * Create a polygonal game object and add it to the scene tree
     * 
     * The polygon is specified as a list of doubles in the form:
     * 
     * [ x0, y0, x1, y1, x2, y2, ... ]
     * 
     * The line and fill colours can possibly be null, in which case that part of the object
     * should not be drawn.
     *
     * @param parent The parent in the scene tree
     * @param points A list of points defining the polygon
     * @param fillColour The fill colour in [r, g, b, a] form
     * @param lineColour The outline colour in [r, g, b, a] form
     */
    public PolygonalGameObject(GameObject parent, double points[],
            double[] fillColour, double[] lineColour) {
        super(parent);

        myPoints = points;
        myFillColour = fillColour;
        myLineColour = lineColour;
    }

    /**
     * Get the polygon
     * 
     * @return
     */
    public double[] getPoints() {        
        return myPoints;
    }

    /**
     * Set the polygon
     * 
     * @param points
     */
    public void setPoints(double[] points) {
        myPoints = points;
    }

    /**
     * Get the fill colour
     * 
     * @return
     */
    public double[] getFillColour() {
        return myFillColour;
    }

    /**
     * Set the fill colour.
     * 
     * Setting the colour to null means the object should not be filled.
     * 
     * @param fillColour The fill colour in [r, g, b, a] form 
     */
    public void setFillColour(double[] fillColour) {
        myFillColour = fillColour;
    }

    /**
     * Get the outline colour.
     * 
     * @return
     */
    public double[] getLineColour() {
        return myLineColour;
    }

    /**
     * Set the outline colour.
     * 
     * Setting the colour to null means the outline should not be drawn
     * 
     * @param lineColour
     */
    public void setLineColour(double[] lineColour) {
        myLineColour = lineColour;
    }

    // ===========================================
    // COMPLETE THE METHODS BELOW
    // ===========================================
    

    /**
     * TODO: Draw the polygon
     * 
     * if the fill colour is non-null, fill the polygon with this colour
     * if the line colour is non-null, draw the outline with this colour
     * 
     * @see COMP3421_ASS1.GameObject#drawSelf(javax.media.opengl.GL2)
     */
    @Override
    public void drawSelf(GL2 gl) {

        // TODO: Write this method
        if (myFillColour != null) {
            gl.glBegin(GL2.GL_POLYGON);
                gl.glColor4d(myFillColour[0], myFillColour[1], myFillColour[2], myFillColour[3]);
                for (int i = 0; i < (myPoints.length); i += 2) {
                    gl.glVertex2d(myPoints[i], myPoints[i+1]);
                }
            gl.glEnd();
        }

        if (myLineColour != null) {
            gl.glBegin(GL2.GL_LINE_LOOP);
                gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
                gl.glColor4d(myLineColour[0], myLineColour[1], myLineColour[2], myLineColour[3]);
                for (int i = 0; i < myPoints.length; i += 2) {
                    gl.glVertex2d(myPoints[i], myPoints[i + 1]);
                }
            gl.glEnd();
        }
    }


}
