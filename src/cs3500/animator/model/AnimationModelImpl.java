package cs3500.animator.model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;

public class AnimationModelImpl implements AnimationModel {

  private Map<String, Shape> shapes;
  private Point canvasPosition;
  private Dimension canvasSize;

  public AnimationModelImpl(Point canvasPosition, Dimension canvasSize) {
    this.canvasPosition = canvasPosition;
    this.canvasSize = canvasSize;
    shapes = new HashMap<>();
  }

  @Override
  public void addShape(String name, ShapeType shape) throws IllegalArgumentException {
    if (name == null || shape == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    if (shapes.containsKey(name)) {
      throw new IllegalArgumentException("A shape with that name already exists.");
    }

    shapes.put(name, new ShapeImpl(shape));
  }

  @Override
  public void addAnimation(String shapeName, Animation anim) throws IllegalArgumentException {
    if (shapeName == null || anim == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    if (!shapes.containsKey(shapeName)) {
      throw new IllegalArgumentException("No shape with that name exists");
    }

    shapes.get(shapeName).addAnimation(anim);
  }

  @Override
  public Map<String, ReadOnlyShape> getShapes() {
    return new HashMap<>(shapes);
  }

  @Override
  public Point canvasPosition() {
    return new Point(canvasPosition);
  }

  @Override
  public Dimension canvasSize() {
    return new Dimension(canvasSize);
  }


  /**
   * Builder for animation model.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {

    Map<String, ShapeType> shapes;
    Map<String, SortedMap<Integer, State>> animations;

    int x;
    int y;
    int width;
    int height;

    public Builder() {
      shapes = new HashMap<>();
      animations = new HashMap<>();

      x = 0;
      y = 0;
      width = 0;
      height = 0;
    }

    @Override
    public AnimationModel build() {
      AnimationModel out = new AnimationModelImpl(new Point(x, y), new Dimension(width, height));
      for (String name : shapes.keySet()) {
        out.addShape(name, shapes.get(name));
      }

      for (String name : animations.keySet()) {
        int prev = animations.get(name).firstKey();
        for (int anim : animations.get(name).keySet()) {
          if (anim != prev) {
            out.addAnimation(name, new BasicAnimation(prev, animations.get(name).get(prev),
                    anim, animations.get(name).get(anim)));
          }
        }
      }

      return out;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;

      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      if (shapes.containsKey(name)) {
        throw new IllegalStateException("Cannot add new shape with same name as existing one");
      }
      if (type.equals("rectangle")) {
        shapes.put(name, ShapeType.RECTANGLE);
      } else if (type.equals("ellipse")) {
        shapes.put(name, ShapeType.ELLIPSE);
      }

      if (!animations.containsKey(name)) {
        animations.put(name, new TreeMap<>());
      }

      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {

      if (!animations.containsKey(name)) {
        animations.put(name, new TreeMap<>());
      }

      animations.get(name).put(t1, new StateImpl(new Point(x1, y1), new Dimension(w1, h1), new Color(r1, g1, b1)));
      animations.get(name).put(t2, new StateImpl(new Point(x2, y2), new Dimension(w2, h2), new Color(r2, g2, b2)));

      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
      if (!animations.containsKey(name)) {
        animations.put(name, new TreeMap<>());
      }
      animations.get(name).put(t, new StateImpl(new Point(x, y), new Dimension(w, h), new Color(r, g, b)));

      return this;
    }
  }
}

