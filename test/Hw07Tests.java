import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.State;
import cs3500.animator.model.StateImpl;

public class Hw07Tests {

  private AnimationModel model;

  @Before
  public void createModel() {
    model = new AnimationModelImpl(new Point(0, 0), new Dimension(500, 500));
    model.addShape("r1", ShapeType.RECTANGLE);
    model.addShape("r2", ShapeType.RECTANGLE);
    model.addShape("c1", ShapeType.ELLIPSE);

    model.addAnimation("r1",
            1, new Point(200, 200), new Dimension(50, 100), Color.RED,
            10, new Point(200, 200), new Dimension(50, 100), Color.RED);
    model.addAnimation("r1",
            10, new Point(200, 200), new Dimension(50, 100), Color.RED,
            50, new Point(300, 300), new Dimension(50, 100), Color.RED);
    model.addAnimation("c1",
            6, new Point(440, 70), new Dimension(120, 60), Color.BLUE,
            20, new Point(440, 70), new Dimension(120, 60), Color.GREEN);
  }

  @Test
  public void testGetKeyframes() {
    SortedMap<Integer, State> r1Frames = new TreeMap<>();
    r1Frames.put(1, new StateImpl(new Point(200, 200), new Dimension(50, 100), Color.RED));
    r1Frames.put(10, new StateImpl(new Point(200, 200), new Dimension(50, 100), Color.RED));
    r1Frames.put(50, new StateImpl(new Point(300, 300), new Dimension(50, 100), Color.RED));
    assertEquals(model.getShape("r1").getKeyframes(), r1Frames);

    SortedMap<Integer, State> c1Frames = new TreeMap<>();
    c1Frames.put(6, new StateImpl(new Point(440, 70), new Dimension(120, 60), Color.BLUE));
    c1Frames.put(20, new StateImpl(new Point(440, 70), new Dimension(120, 60), Color.GREEN));
    assertEquals(model.getShape("c1").getKeyframes(), c1Frames);

    assertEquals(model.getShape("r2").getKeyframes(), Collections.emptySortedMap());
  }

  @Test
  public void testInsertKeyframe() {
    SortedMap<Integer, State> c1Frames = new TreeMap<>();

    c1Frames.put(6, new StateImpl(new Point(440, 70), new Dimension(120, 60), Color.BLUE));
    c1Frames.put(20, new StateImpl(new Point(440, 70), new Dimension(120, 60), Color.GREEN));

    assertEquals(model.getShape("c1").getKeyframes(), c1Frames);

    State s1 = new StateImpl(new Point(10, 10), new Dimension(120, 60), Color.BLUE);
    State s10 = new StateImpl(new Point(10, 50), new Dimension(10, 10), Color.GREEN);
    State s30 = new StateImpl(new Point(4, 70), new Dimension(120, 60), Color.GREEN);

    model.insertKeyframe("c1", 1, s1);
    model.insertKeyframe("c1", 10, s10);
    model.insertKeyframe("c1", 30, s30);

    c1Frames.put(1, s1);
    c1Frames.put(10, s10);
    c1Frames.put(30, s30);
    assertEquals(model.getShape("c1").getKeyframes(), c1Frames);
  }

  @Test
  public void testInsertIntoEmptyAnimations() {
    SortedMap<Integer, State> r2Frames = new TreeMap<>();
    r2Frames.put(10, new StateImpl(new Point(0, 0), new Dimension(10, 0), Color.RED));

    model.insertKeyframe("r2", 10,
            new StateImpl(new Point(0, 0), new Dimension(10, 0), Color.RED));
    assertEquals(model.getShape("r2").getKeyframes(), r2Frames);
  }

  @Test
  public void testDeleteKeyframeMiddle() {
    SortedMap<Integer, State> r1Frames = new TreeMap<>();
    r1Frames.put(1, new StateImpl(new Point(200, 200), new Dimension(50, 100), Color.RED));
    r1Frames.put(10, new StateImpl(new Point(200, 200), new Dimension(50, 100), Color.RED));
    r1Frames.put(50, new StateImpl(new Point(300, 300), new Dimension(50, 100), Color.RED));
    assertEquals(model.getShape("r1").getKeyframes(), r1Frames);

    model.deleteKeyframe("r1", 3);
    assertEquals(model.getShape("r1").getKeyframes(), r1Frames);

    model.deleteKeyframe("r1", 10);
    r1Frames.remove(10);
    assertEquals(model.getShape("r1").getKeyframes(), r1Frames);
  }

  @Test
  public void testDeleteKeyframeStart() {
    SortedMap<Integer, State> r1Frames = new TreeMap<>();
    r1Frames.put(1, new StateImpl(new Point(200, 200), new Dimension(50, 100), Color.RED));
    r1Frames.put(10, new StateImpl(new Point(200, 200), new Dimension(50, 100), Color.RED));
    r1Frames.put(50, new StateImpl(new Point(300, 300), new Dimension(50, 100), Color.RED));
    assertEquals(model.getShape("r1").getKeyframes(), r1Frames);

    model.deleteKeyframe("r1", 0);
    assertEquals(model.getShape("r1").getKeyframes(), r1Frames);

    model.deleteKeyframe("r1", 1);
    r1Frames.remove(1);
    assertEquals(model.getShape("r1").getKeyframes(), r1Frames);
  }

  @Test
  public void testDeleteKeyframeEnd() {
    SortedMap<Integer, State> r1Frames = new TreeMap<>();
    r1Frames.put(1, new StateImpl(new Point(200, 200), new Dimension(50, 100), Color.RED));
    r1Frames.put(10, new StateImpl(new Point(200, 200), new Dimension(50, 100), Color.RED));
    r1Frames.put(50, new StateImpl(new Point(300, 300), new Dimension(50, 100), Color.RED));
    assertEquals(model.getShape("r1").getKeyframes(), r1Frames);

    model.deleteKeyframe("r1", 55);
    assertEquals(model.getShape("r1").getKeyframes(), r1Frames);

    model.deleteKeyframe("r1", 50);
    r1Frames.remove(50);
    assertEquals(model.getShape("r1").getKeyframes(), r1Frames);
  }

  @Test
  public void testDeleteOneKeyframeRemaining() {
    SortedMap<Integer, State> c1Frames = new TreeMap<>();
    c1Frames.put(6, new StateImpl(new Point(440, 70), new Dimension(120, 60), Color.BLUE));
    c1Frames.put(20, new StateImpl(new Point(440, 70), new Dimension(120, 60), Color.GREEN));
    assertEquals(model.getShape("c1").getKeyframes(), c1Frames);

    model.deleteKeyframe("c1", 55);
    assertEquals(model.getShape("c1").getKeyframes(), c1Frames);

    model.deleteKeyframe("c1", 20);
    c1Frames.remove(20);
    assertEquals(model.getShape("c1").getKeyframes(), c1Frames);
  }

  @Test
  public void testDeleteNoAnimations() { //delete should not complain when there are no animations
    try {
      model.deleteKeyframe("r2", 20);
    } catch (Exception e) {
      fail();
    }
  }
}
