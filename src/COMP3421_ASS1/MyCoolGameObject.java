package COMP3421_ASS1;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

/**
 * A game object that is really cool.
 *
 * This class extend GameObject to draw cool shapes.
 *
 * @author damianr
 */
public class MyCoolGameObject extends GameObject {

    public MyCoolGameObject(GameObject parent) {
        super(parent);
        createShapes();
    }

    public MyCoolGameObject() {
        super(GameObject.ROOT);
        createShapes();
    }

    private void createShapes() {
        double[] fillColour = new double[]{1,0,0,1};
        double[] lineColour = new double[]{1,1,1,1};
        double[] points = new double[]{-0.45, 0.85, -0.45, -0.85, 0.45, -0.85, 0.45, 0.85};
        PolygonalGameObject torso = new PolygonalGameObject(this, points, fillColour, lineColour);

        CircularGameObject head = new CircularGameObject(torso, 0.4, fillColour, lineColour);
        head.setPosition(0, 1.35);

        double[] points2 = new double[]{-0.85, 0.25, -0.85, -0.25, 0.85, -0.25, 0.85, 0.25};
        PolygonalGameObject shoulders = new PolygonalGameObject(torso, points2, fillColour, lineColour);
        shoulders.setPosition(0, 0.6);

        double[] points3 = new double[]{-0.2, 0.65, -0.2, -0.65, 0.2, -0.65, 0.2, 0.65};
        PolygonalGameObject leftArm = new PolygonalGameObject(shoulders, points3, fillColour, lineColour);
        leftArm.setPosition(0.75, -0.45);

        PolygonalGameObject rightArm = new PolygonalGameObject(shoulders, points3, fillColour, lineColour);
        rightArm.setPosition(-0.75, -0.45);

        double[] points4 = new double[]{-0.2, 0.7, -0.2, -0.8, 0.2, -0.8, 0.2, 0.7};
        PolygonalGameObject leftLeg = new PolygonalGameObject(torso, points4, fillColour, lineColour);
        leftLeg.setPosition(0.25, -1.25);

        PolygonalGameObject rightLeg = new PolygonalGameObject(torso, points4, fillColour, lineColour);
        rightLeg.setPosition(-0.25, -1.25);
    }

    public void drawSelf(GL2 gl) {
        //do nothing
    }
}
