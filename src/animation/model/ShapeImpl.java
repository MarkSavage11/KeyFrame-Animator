package animation.model;

import java.util.ArrayList;
import java.util.List;

public class ShapeImpl implements Shape {

  private String name;
  private ShapeType type;
  private List<Animation> animations;

  public ShapeImpl(String name, ShapeType type) {
    this.name = name;
    this.type = type;
    animations = new ArrayList<>();
  }

  @Override
  public void addAnimation(Animation anim) throws IllegalArgumentException {
    if (anim == null) {
      throw new IllegalArgumentException("Animation cannot be null");
    }
    for (Animation existingAnim : animations) {
      if (existingAnim.conflictsWith(anim)) {
        throw new IllegalArgumentException
                ("The given animation conflicts with an existing animation.");
      }
    }
    animations.add(anim);
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    List<String> result = new ArrayList<>();
    for (Animation anim : animations) {
      result.add("motion " + this.name + " " + anim.toString());
    }
    return String.join("\n", result);
  }

}
