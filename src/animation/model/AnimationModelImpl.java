package animation.model;

import java.util.HashMap;
import java.util.Map;

public class AnimationModelImpl implements AnimationModel {

  private Map<String,Shape> shapes;

  public AnimationModelImpl() {
    shapes = new HashMap<>();
  }

  @Override
  public void addShape(String name, Shape shape) throws IllegalArgumentException {
    if (shapes.containsKey(name)) {
      throw new IllegalArgumentException("A shape with that name already exists.");
    }
  }

  @Override
  public void addAnimation(String shapeName, Animation anim) throws IllegalArgumentException {
    
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();

    for(Map.Entry<String, Shape> entry : shapes.entrySet()) {
      result.append("shape " + entry.getKey() + " " + entry.getValue().getType().toString() + "\n");
      result.append(entry.getValue().toString());
      result.append("\n");
    }

    return result.toString();
  }

}

