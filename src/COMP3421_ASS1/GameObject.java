package COMP3421_ASS1;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;




/**
 * A GameObject is an object that can move around in the game world.
 * 
 * GameObjects form a scene tree. The root of the tree is the special ROOT object.
 * 
 * Each GameObject is offset from its parent by a rotation, a translation and a scale factor. 
 *
 * TODO: The methods you need to complete are at the bottom of the class
 *
 * @author malcolmr
 */
public class GameObject {

    // the list of all GameObjects in the scene tree
    public final static List<GameObject> ALL_OBJECTS = new ArrayList<GameObject>();
    
    // the root of the scene tree
    public final static GameObject ROOT = new GameObject();
    
    // the links in the scene tree
    private GameObject myParent;
    private List<GameObject> myChildren;

    // the local transformation
    //myRotation should be normalised to the range (-180..180)
    private double myRotation;
    private double myScale;
    private double[] myTranslation;
    
    // is this part of the tree showing?
    private boolean amShowing;

    /**
     * Special private constructor for creating the root node. Do not use otherwise.
     */
    private GameObject() {
        myParent = null;
        myChildren = new ArrayList<GameObject>();

        myRotation = 0;
        myScale = 1;
        myTranslation = new double[2];
        myTranslation[0] = 0;
        myTranslation[1] = 0;

        amShowing = true;
        
        ALL_OBJECTS.add(this);
    }

    /**
     * Public constructor for creating GameObjects, connected to a parent (possibly the ROOT).
     *  
     * New objects are created at the same location, orientation and scale as the parent.
     *
     * @param parent
     */
    public GameObject(GameObject parent) {
        myParent = parent;
        myChildren = new ArrayList<GameObject>();

        parent.myChildren.add(this);

        myRotation = 0;
        myScale = 1;
        myTranslation = new double[2];
        myTranslation[0] = 0;
        myTranslation[1] = 0;

        // initially showing
        amShowing = true;

        ALL_OBJECTS.add(this);
    }

    /**
     * Remove an object and all its children from the scene tree.
     */
    public void destroy() {
        for (GameObject child : myChildren) {
            child.destroy();
        }
        
        myParent.myChildren.remove(this);
        ALL_OBJECTS.remove(this);
    }

    /**
     * Get the parent of this game object
     * 
     * @return
     */
    public GameObject getParent() {
        return myParent;
    }

    /**
     * Get the children of this object
     * 
     * @return
     */
    public List<GameObject> getChildren() {
        return myChildren;
    }

    /**
     * Get the local rotation (in degrees)
     * 
     * @return
     */
    public double getRotation() {
        return myRotation;
    }

    /**
     * Set the local rotation (in degrees)
     * 
     * @return
     */
    public void setRotation(double rotation) {
        myRotation = MathUtil.normaliseAngle(rotation);
    }

    /**
     * Rotate the object by the given angle (in degrees)
     * 
     * @param angle
     */
    public void rotate(double angle) {
        myRotation += angle;
        myRotation = MathUtil.normaliseAngle(myRotation);
    }

    /**
     * Get the local scale
     * 
     * @return
     */
    public double getScale() {
        return myScale;
    }

    /**
     * Set the local scale
     * 
     * @param scale
     */
    public void setScale(double scale) {
        myScale = scale;
    }

    /**
     * Multiply the scale of the object by the given factor
     * 
     * @param factor
     */
    public void scale(double factor) {
        myScale *= factor;
    }

    /**
     * Get the local position of the object 
     * 
     * @return
     */
    public double[] getPosition() {
        double[] t = new double[2];
        t[0] = myTranslation[0];
        t[1] = myTranslation[1];

        return t;
    }

    /**
     * Set the local position of the object
     * 
     * @param x
     * @param y
     */
    public void setPosition(double x, double y) {
        myTranslation[0] = x;
        myTranslation[1] = y;
    }

    /**
     * Move the object by the specified offset in local coordinates
     * 
     * @param dx
     * @param dy
     */
    public void translate(double dx, double dy) {
        myTranslation[0] += dx;
        myTranslation[1] += dy;
    }

    /**
     * Test if the object is visible
     * 
     * @return
     */
    public boolean isShowing() {
        return amShowing;
    }

    /**
     * Set the showing flag to make the object visible (true) or invisible (false).
     * This flag should also apply to all descendents of this object.
     * 
     * @param showing
     */
    public void show(boolean showing) {
        amShowing = showing;
    }

    /**
     * Update the object. This method is called once per frame. 
     * 
     * This does nothing in the base GameObject class. Override this in subclasses.
     * 
     * @param dt The amount of time since the last update (in seconds)
     */
    public void update(double dt) {
        // do nothing
    }

    /**
     * Draw the object (but not any descendants)
     * 
     * This does nothing in the base GameObject class. Override this in subclasses.
     * 
     * @param gl
     */
    public void drawSelf(GL2 gl) {
        // do nothing
    }

    
    // ===========================================
    // COMPLETE THE METHODS BELOW
    // ===========================================
    
    /**
     * Draw the object and all of its descendants recursively.
     * 
     * TODO: Complete this method
     * 
     * @param gl
     */
    public void draw(GL2 gl) {
        // don't draw if it is not showing
        if (!amShowing) {
            return;
        }

        // TODO: setting the model transform appropriately
        // draw the object (Call drawSelf() to draw the object itself)
        // and all its children recursively
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        if (this == ROOT) {
            gl.glLoadIdentity();
        }
        gl.glPushMatrix();
        gl.glTranslated(myTranslation[0], myTranslation[1], 0);
        gl.glRotated(myRotation, 0, 0, 1);
        gl.glScaled(myScale, myScale, 1);
        drawSelf(gl);
        for (GameObject child: myChildren
             ) {
            child.draw(gl);
        }
        gl.glPopMatrix();
    }

    /**
     * Compute the object's position in world coordinates
     * 
     * TODO: Write this method
     * 
     * @return a point in world coordinates in [x,y] form
     */
    public double[] getGlobalPosition() {
        double[] pPos = ROOT.getPosition();
        double pRot = ROOT.getRotation();
        double pScale = ROOT.getScale();

        if (myParent != null) {
            pPos = myParent.getGlobalPosition();
            pRot = myParent.getGlobalRotation();
            pScale = myParent.getGlobalScale();

        } else {
            return pPos;
        }

        double[][] m = MathUtil.translationMatrix(pPos);
        m = MathUtil.multiply(m, MathUtil.rotationMatrix(pRot));
        m = MathUtil.multiply(m, MathUtil.scaleMatrix(pScale));
        double[] myTransVector = new double[3];
        myTransVector[0] = myTranslation[0];
        myTransVector[1] = myTranslation[1];
        myTransVector[2] = 1;
        double[] p = MathUtil.multiply(m, myTransVector);
        double[] globalPos = new double[2];
        globalPos[0] = p[0];
        globalPos[1] = p[1];

        return p;
    }

    /**
     * Compute the object's rotation in the global coordinate frame
     * 
     * TODO: Write this method
     * 
     * @return the global rotation of the object (in degrees) and 
     * normalized to the range (-180, 180) degrees. 
     */
    public double getGlobalRotation() {
        double r = ROOT.getRotation();
        if (myParent != null) {
            r = myParent.getGlobalRotation();
        } else {
            return r;
        }

        return MathUtil.normaliseAngle(r + myRotation);
    }

    /**
     * Compute the object's scale in global terms
     * 
     * TODO: Write this method
     * 
     * @return the global scale of the object 
     */
    public double getGlobalScale() {
        double pScale = ROOT.getScale();
        if (myParent != null) {
            pScale = myParent.getGlobalScale();
        } else {
            return pScale;
        }
        return pScale * myScale;
    }

    /**
     * Change the parent of a game object.
     * 
     * TODO: add code so that the object does not change its global position, rotation or scale
     * when it is reparented. You may need to add code before and/or after 
     * the fragment of code that has been provided - depending on your approach
     * 
     * @param parent
     */
    public void setParent(GameObject parent) {
        double[] globalPos = getGlobalPosition();
        double globalRot = getGlobalRotation();
        double globalScale = getGlobalScale();

        myParent.myChildren.remove(this);
        myParent = parent;
        myParent.myChildren.add(this);

        myRotation = MathUtil.normaliseAngle(globalRot - myParent.getGlobalRotation());
        myScale = globalScale / myParent.getGlobalScale();

        double[] pGlobalPos = myParent.getGlobalPosition();
        double[] pInvGlobalPos = new double[3];
        pInvGlobalPos[0] = pGlobalPos[0] * -1;
        pInvGlobalPos[1] = pGlobalPos[1] * -1;
        pInvGlobalPos[2] = 1;

        double[][] invMat = MathUtil.scaleMatrix(1/myParent.getGlobalScale());
        invMat = MathUtil.multiply(invMat, MathUtil.rotationMatrix(myParent.getGlobalRotation() * -1));
        invMat = MathUtil.multiply(invMat, MathUtil.translationMatrix(pInvGlobalPos));

        double[] localTrans = MathUtil.multiply(invMat, globalPos);
        setPosition(localTrans[0], localTrans[1]);
    }
    

}
