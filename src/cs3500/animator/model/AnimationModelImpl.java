package cs3500.animator.model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

  public static final class Builder implements AnimationBuilder<AnimationModel> {
    @Override
    public AnimationModel build() {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
      return null;
    }
    // FILL IN HERE
  }
}

