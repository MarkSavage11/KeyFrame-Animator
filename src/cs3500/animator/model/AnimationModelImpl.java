package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import cs3500.animator.util.AnimationBuilder;

/**
 * Standard Animation Model implementation that holds a canvas position and size and its shapes.
 */
public class AnimationModelImpl implements AnimationModel {

  private Map<String, Shape> shapes;
  private Point canvasPosition;
  private Dimension canvasSize;
  private int lastTick;
  private int firstTick;

  /**
   * Constructs an empty animation model with the given canvas dimensions and position.
   *
   * @param canvasPosition the position where a canvas representing this model would appear
   * @param canvasSize     this size a canvas representing this model would appear with
   */
  public AnimationModelImpl(Point canvasPosition, Dimension canvasSize) {
    this.canvasPosition = canvasPosition;
    this.canvasSize = canvasSize;
    shapes = new LinkedHashMap<>();
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
    if (anim.endTick() > lastTick) {
      lastTick = anim.endTick();
    } else if (anim.startTick() < firstTick) {
      firstTick = anim.startTick();
    }
  }

  @Override
  public void addAnimation(String shapeName,
                           int startTick, Point startPosition, Dimension startSize,
                           Color startColor, int endTick, Point endPosition, Dimension endSize,
                           Color endColor)
          throws IllegalArgumentException {
    this.addAnimation(shapeName, new BasicAnimation(startTick,
            new StateImpl(startPosition, startSize, startColor),
            endTick, new StateImpl(endPosition, endSize, endColor)));
  }

  @Override
  public Map<String, ReadOnlyShape> getShapes() {
    return Collections.unmodifiableMap(shapes);
  }

  @Override
  public ReadOnlyShape getShape(String name) {
    return shapes.get(name);
  }

  @Override
  public Point canvasPosition() {
    return new Point(canvasPosition);
  }

  @Override
  public Dimension canvasSize() {
    return new Dimension(canvasSize);
  }

  @Override
  public void insertKeyframe(String shapeName, int tick, State keyframe)
          throws IllegalArgumentException {
    shapes.get(shapeName).insertKeyframe(tick, keyframe);
    if (tick > lastTick) {
      lastTick = tick;
    } else if (tick < firstTick) {
      firstTick = tick;
    }
  }

  @Override
  public void deleteKeyframe(String shapeName, int tick) throws IllegalArgumentException {
    shapes.get(shapeName).deleteKeyframe(tick);
  }

  @Override
  public void deleteShape(String shapeName) throws IllegalArgumentException {
    Objects.requireNonNull(shapeName);
    if (shapes.containsKey(shapeName)) {
      shapes.remove(shapeName);
    }
  }

  @Override
  public int getFirstTick() {
    return firstTick;
  }

  @Override
  public int getLastTick() {
    return lastTick;
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

    /**
     * Makes a builder.
     */
    public Builder() {
      shapes = new LinkedHashMap<>();
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
          prev = anim;
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

      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                      int h1, int r1, int g1, int b1, int t2,
                                                      int x2, int y2, int w2, int h2, int r2,
                                                      int g2, int b2) {

      if (!animations.containsKey(name)) {
        animations.put(name, new TreeMap<>());
      }

      animations.get(name).put(t1, new StateImpl(new Point(x1, y1), new Dimension(w1, h1),
              new Color(r1, g1, b1)));
      animations.get(name).put(t2, new StateImpl(new Point(x2, y2), new Dimension(w2, h2),
              new Color(r2, g2, b2)));

      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w,
                                                        int h, int r, int g, int b) {
      if (!animations.containsKey(name)) {
        animations.put(name, new TreeMap<>());
      }
      animations.get(name).put(t, new StateImpl(new Point(x, y), new Dimension(w, h),
              new Color(r, g, b)));

      return this;
    }
  }
}

