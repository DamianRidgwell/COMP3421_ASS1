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
        double[] points = new double[]{-0.45, 0.7, -0.45, -1.0, 0.45, -1.0, 0.45, 0.7};
        PolygonalGameObject torso = new PolygonalGameObject(this, points, fillColour, lineColour);
        getChildren().add(torso);

        double[] points2 = new double[]{0, 0, 1.5, 0, 1.5, 0.5, 0, 0.5};
        PolygonalGameObject shoulders = new PolygonalGameObject(torso, points2, fillColour, lineColour);
        shoulders.setPosition(-0.75, 0.2);
        torso.getChildren().add(shoulders);

        double[] points3 = new double[]{0, 0, 0.4, 0, 0.4, 1.3, 0, 1.3};
        PolygonalGameObject leftArm = new PolygonalGameObject(shoulders, points3, fillColour, lineColour);
        leftArm.set


    }

    public void drawSelf(GL2 gl) {

    }
}
