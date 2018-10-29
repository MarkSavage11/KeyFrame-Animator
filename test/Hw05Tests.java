import org.junit.Test;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

import animation.model.Animation;
import animation.model.AnimationModel;
import animation.model.AnimationModelImpl;
import animation.model.BasicAnimation;
import animation.model.Homework05.AccessibleAnimation;
import animation.model.Homework05.AccessibleAnimationImpl;
import animation.model.Homework05.AnimAccessibleShape;
import animation.model.Homework05.AnimAccessibleShapeImpl;
import animation.model.Homework05.StringModelImpl;
import animation.model.Shape;
import animation.model.ShapeImpl;
import animation.model.ShapeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Hw05Tests {


  @Test
  public void testShapeConstructor() {
    Shape rect = new ShapeImpl(ShapeType.RECTANGLE);
    Shape ellipse = new ShapeImpl(ShapeType.ELLIPSE);

    assertEquals(ShapeType.RECTANGLE, rect.getType());
    assertEquals(ShapeType.ELLIPSE, ellipse.getType());
  }

  @Test
  public void testAnimationConstructor() {
    //TODO: Test all the getters

  }

  @Test
  public void testAnimationModel() {
    AnimationModel model = new StringModelImpl();
    Shape r = new AnimAccessibleShapeImpl(ShapeType.RECTANGLE);
    Shape c = new AnimAccessibleShapeImpl(ShapeType.ELLIPSE);
    model.addShape("R", r);
    model.addShape("C", c);
    Animation animR1 =
            new AccessibleAnimationImpl(1, new Point(200, 200), new Dimension(50, 100), Color.RED,
                    10, new Point(200, 200), new Dimension(50, 100), Color.RED);
    Animation animR2 =
            new AccessibleAnimationImpl(10, new Point(200, 200), new Dimension(50, 100), Color.RED,
                    50, new Point(300, 300), new Dimension(50, 100), Color.RED);

    Animation animC1 =
            new AccessibleAnimationImpl(6, new Point(440, 70), new Dimension(120, 60), Color.BLUE,
                    20, new Point(440, 70), new Dimension(120, 60), Color.GREEN);
    Animation animC2 =
            new AccessibleAnimationImpl(20, new Point(440, 70), new Dimension(120, 60), Color.GREEN,
                    50, new Point(440, 250), new Dimension(60, 0), Color.BLACK);
    model.addAnimation("R", animR1);
    model.addAnimation("R", animR2);
    model.addAnimation("C", animC1);
    model.addAnimation("C", animC1);

    assertEquals("Shape R rectangle\n"
            + "motion R 1  200 200 50 100 255 0  0    10  200 200 50 100 255 0  0\n"
            + "motion R 10 200 200 50 100 255 0  0    50  300 300 50 100 255 0  0 \n\n"
            + "shape C ellipse\n"
            + "motion C 6  440 70 120 60 0 0 255      20  440 70  120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255      50  440 250 120 60 0 0 255",
            model.getState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailedAddAnimation1() {
    Shape shape = new ShapeImpl(ShapeType.RECTANGLE);
    Animation a1 = new BasicAnimation(1, new Point(), new Dimension(), Color.black,
            10, new Point(), new Dimension(), Color.black);
    Animation a2 = new BasicAnimation(2, new Point(), new Dimension(), Color.black,
            15, new Pointe(), new Dimension(), Color.black);
    shape.addAnimation(a1);
    shape.addAnimation(a2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailedAddAnimation2() {
    Shape shape = new ShapeImpl(ShapeType.ELLIPSE);
    shape.addAnimation(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelAddShapeFail1() {
    boolean alreadyExistsFail = false;
    boolean nullNameFail = false;
    boolean nullShapeFail = false;

    AnimationModel model = new AnimationModelImpl();
    Shape dummy = new ShapeImpl(ShapeType.RECTANGLE);
    model.addShape("dummy", dummy);

    try {
      model.addShape("dummy", new ShapeImpl(ShapeType.RECTANGLE));
    } catch(IllegalArgumentException e) {
      alreadyExistsFail = true;
    }

    try {
      model.addShape(null, new ShapeImpl(ShapeType.RECTANGLE));
    } catch(IllegalArgumentException e) {
      nullNameFail = true;
    }

    try {
      model.addShape("dummy", null);
    } catch(IllegalArgumentException e) {
      nullShapeFail = true;
    }

    assertFalse(alreadyExistsFail && nullNameFail && nullShapeFail);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelAddShapeFail2() {
    AnimationModel model = new AnimationModelImpl();
    Shape dummy = new ShapeImpl(ShapeType.RECTANGLE);
    model.addShape(null, dummy);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testModelAddAnimationFail() {
    boolean noMatchFail = false;
    boolean nullNameFail = false;
    boolean nullAnimFail = false;

    AnimationModel model = new StringModelImpl();
    Shape r = new AnimAccessibleShapeImpl(ShapeType.RECTANGLE);
    model.addShape("R", r);
    Animation a1 = new BasicAnimation(1, new Point(), new Dimension(), Color.black,
            10, new Point(), new Dimension(), Color.black);

    //Checks that the given shape name isn't in the model
    try{
      model.addAnimation("Doesn't Exist", a1);
    }catch(IllegalArgumentException e) {
      noMatchFail = true;
    }
    //Checks when the given name is null
    try{
      model.addAnimation(null, a1);
    }catch(IllegalArgumentException e) {
      nullNameFail = true;
    }
    //Checks when the given animation is null
    try{
      model.addAnimation("R", null);
    }catch(IllegalArgumentException e) {
      nullAnimFail = true;
    }
    assertTrue(noMatchFail && nullAnimFail && nullNameFail);

  }

}
