import org.junit.Test;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

import animation.model.Animation;
import animation.model.AnimationModel;
import animation.model.AnimationModelImpl;
import animation.model.BasicAnimation;
import animation.model.Shape;
import animation.model.ShapeImpl;
import animation.model.ShapeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Hw05Tests {


  @Test
  public void testShapeConstructor() {
    Shape rect = new ShapeImpl("R", ShapeType.RECTANGLE);
    Shape ellipse = new ShapeImpl("C", ShapeType.ELLIPSE);

    assertEquals(ShapeType.RECTANGLE, rect.getType());
    assertEquals(ShapeType.ELLIPSE, ellipse.getType());

    assertEquals("R", rect.getName());
    assertEquals("C", ellipse.getName());
  }

  @Test
  public void testAnimationConstructor() {
    Animation anim =
            new BasicAnimation(1, new Point(200, 200), new Dimension(50, 100), Color.RED,
                    10, new Point(100, 100), new Dimension(100, 50), Color.WHITE);
    assertEquals(1, anim.startTick());
    assertEquals(10, anim.endTick());

    Point startPosClone = anim.getStartPosition();
    assertEquals(startPosClone.x, 200);
    assertEquals(startPosClone.y, 200);

    startPosClone.translate(50, 50);
    assertEquals(200, anim.getStartPosition().x);
    assertEquals(200, anim.getStartPosition().y);

    Dimension startDimClone = anim.getEndSize();
    assertEquals(100, startDimClone.height);
    assertEquals(50, startDimClone.width);

    assertEquals(Color.RED, anim.getStartColor());

    Point endPosClone = anim.getEndPosition();
    assertEquals(endPosClone.x, 100);
    assertEquals(endPosClone.y, 100);

    Dimension endDimClone = anim.getEndSize();
    assertEquals(100, endDimClone.width);
    assertEquals(50, endDimClone.height);

    endDimClone.setSize(50, 50);
    assertEquals(100, anim.getEndSize().height);
    assertEquals(50, anim.getEndSize().width);

    assertEquals(Color.WHITE, anim.getEndColor());

  }

  @Test
  public void testAnimationModel() {
    AnimationModel model = new AnimationModelImpl();
    Shape r = new ShapeImpl("R", ShapeType.RECTANGLE);
    Shape c = new ShapeImpl("C", ShapeType.ELLIPSE);
    model.addShape(r);
    model.addShape(c);
    Animation animR1 =
            new BasicAnimation(1, new Point(200, 200), new Dimension(50, 100), Color.RED,
                    10, new Point(200, 200), new Dimension(50, 100), Color.RED);
    Animation animR2 =
            new BasicAnimation(10, new Point(200, 200), new Dimension(50, 100), Color.RED,
                    50, new Point(300, 300), new Dimension(50, 100), Color.RED);

    Animation animC1 =
            new BasicAnimation(6, new Point(440, 70), new Dimension(120, 60), Color.BLUE,
                    20, new Point(440, 70), new Dimension(120, 60), Color.GREEN);
    Animation animC2 =
            new BasicAnimation(20, new Point(440, 70), new Dimension(120, 60), Color.GREEN,
                    50, new Point(440, 250), new Dimension(60, 0), Color.BLACK);
    model.addAnimation("R", animR1);
    model.addAnimation("R", animR2);
    model.addAnimation("C", animC1);
    model.addAnimation("C", animC1);

    assertEquals("Shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0    10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0    50 300 300 50 100 255 0 0 \n\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255    20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255    50 440 250 120 60 0 0 255",
            model.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailedAddAnimation1() {
    Shape shape = new ShapeImpl("R", ShapeType.RECTANGLE);
    Animation a1 = new BasicAnimation(1, new Point(), new Dimension(), Color.black,
            10, new Point(), new Dimension(), Color.black);
    Animation a2 = new BasicAnimation(2, new Point(), new Dimension(), Color.black,
            15, new Point(), new Dimension(), Color.black);
    shape.addAnimation(a1);
    shape.addAnimation(a2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailedAddAnimation2() {
    Shape shape = new ShapeImpl("R", ShapeType.ELLIPSE);
    shape.addAnimation(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelAddShapeFail1() {
    boolean alreadyExistsFail = false;
    boolean nullShapeFail = false;

    AnimationModel model = new AnimationModelImpl();
    Shape dummy = new ShapeImpl("dummy", ShapeType.RECTANGLE);
    model.addShape(dummy);

    try {
      model.addShape(new ShapeImpl("dummy", ShapeType.RECTANGLE));
    } catch(IllegalArgumentException e) {
      alreadyExistsFail = true;
    }

    try {
      model.addShape(null);
    } catch(IllegalArgumentException e) {
      nullShapeFail = true;
    }

    assertFalse(alreadyExistsFail && nullShapeFail);
  }


  @Test (expected = IllegalArgumentException.class)
  public void testModelAddAnimationFail() {
    boolean noMatchFail = false;
    boolean nullNameFail = false;
    boolean nullAnimFail = false;

    AnimationModel model = new AnimationModelImpl();
    Shape r = new ShapeImpl("R", ShapeType.RECTANGLE);
    model.addShape(r);
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
