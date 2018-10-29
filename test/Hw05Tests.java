import org.junit.Test;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

import animation.model.Animation;
import animation.model.BasicAnimation;
import animation.model.Homework05.AccessibleAnimationImpl;
import animation.model.Homework05.AnimAccessibleShapeImpl;
import animation.model.Homework05.StringModel;
import animation.model.Homework05.StringModelImpl;
import animation.model.Shape;
import animation.model.ShapeImpl;
import animation.model.ShapeType;

import static org.junit.Assert.assertEquals;

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
    StringModel model = new StringModelImpl();
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
            15, new Point(), new Dimension(), Color.black);
    shape.addAnimation(a1);
    shape.addAnimation(a2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailedAddAnimation2() {
    Shape shape = new ShapeImpl(ShapeType.ELLIPSE);
    shape.addAnimation(null);
  }

}
