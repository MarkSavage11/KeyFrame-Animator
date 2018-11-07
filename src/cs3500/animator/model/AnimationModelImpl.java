package cs3500.animator.model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
}

