import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.BasicAnimation;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.StateImpl;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextAnimationView;

import static org.junit.Assert.assertEquals;

public class Hw06Tests {

  AnimationModel model;

  @Before
  public void init() {
    this.model = new AnimationModelImpl(new Point(200, 200), new Dimension(600, 600));
    model.addShape("R", ShapeType.RECTANGLE);
    model.addShape("C", ShapeType.ELLIPSE);
    Animation animR1 =
            new BasicAnimation(1, new StateImpl(new Point(200, 200),
                    new Dimension(50, 100), Color.RED), 10,
                    new StateImpl(new Point(200, 200), new Dimension(50, 100), Color.RED));
    Animation animR2 =
            new BasicAnimation(10, new StateImpl(new Point(200, 200),
                    new Dimension(50, 100), Color.RED), 50,
                    new StateImpl(new Point(300, 300), new Dimension(50, 100), Color.RED));

    Animation animC1 =
            new BasicAnimation(6, new StateImpl(new Point(440, 70),
                    new Dimension(120, 60), Color.BLUE), 20,
                    new StateImpl(new Point(440, 70), new Dimension(120, 60), Color.GREEN));
    Animation animC2 =
            new BasicAnimation(20, new StateImpl(new Point(440, 70),
                    new Dimension(120, 60), Color.GREEN), 50,
                    new StateImpl(new Point(440, 250), new Dimension(60, 0), Color.BLACK));
    model.addAnimation("R", animR1);
    model.addAnimation("R", animR2);
    model.addAnimation("C", animC1);
    model.addAnimation("C", animC2);
  }

  @Test
  public void testTextView() {
    Appendable ap = new StringBuilder();
    AnimationView view = new TextAnimationView(ap);
    view.display(model);

    assertEquals("canvas 200 200 600 600\n"
            + "shape R rectangle\n"
            + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
            + "shape C ellipse\n"
            + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 255 0\n"
            + "motion C 20 440 70 120 60 0 255 0 50 440 250 60 0 0 0 0\n", ap.toString());
  }

  @Test
  public void testSVGView() {
    Appendable ap = new StringBuilder();
    AnimationView view = new SVGAnimationView(ap, 1);
    view.display(model);

    assertEquals("<svg width=\"600\" height=\"600\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(255,0,0)\">\n"
            + "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" attributeName=\"x\" from=\"200\" to=\"300\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" attributeName=\"y\" from=\"200\" to=\"300\"/>\n"
            + "</rect>\n"
            + "<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"120\" ry=\"60\" fill=\"rgb(0,255,0)\">\n"
            + "<animate attributeType=\"xml\" begin=\"6000ms\" dur=\"14000ms\" attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"30000ms\" attributeName=\"cy\" from=\"70\" to=\"250\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"30000ms\" attributeName=\"rx\" from=\"120\" to=\"60\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"30000ms\" attributeName=\"ry\" from=\"60\" to=\"0\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"30000ms\" attributeName=\"fill\" from=\"rgb(0,255,0)\" to=\"rgb(0,0,0)\"/>\n"
            + "</ellipse>\n"
            + "</svg>", ap.toString());
  }


}
