package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationModelImpl implements AnimationModel {

  private Map<String, Shape> shapes;

  public AnimationModelImpl() {
    shapes = new HashMap<>();
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Shape can't be null");
    }
    if (shapes.containsKey(shape.getName())) {
      throw new IllegalArgumentException("A shape with that name already exists.");
    }

    shapes.put(shape.getName(), shape);
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
  public String toString() {
    List<String> result = new ArrayList<>();

    for (Map.Entry<String, Shape> entry : shapes.entrySet()) {
      result.add("shape " + entry.getKey() + " " +
              entry.getValue().getType().toString() + "\n" + entry.getValue().toString());
    }
    return String.join("\n\n", result);
  }

}

