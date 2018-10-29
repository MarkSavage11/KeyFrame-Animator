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
}
