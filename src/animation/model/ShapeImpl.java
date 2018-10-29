package animation.model;

import java.util.ArrayList;
import java.util.List;

public class ShapeImpl implements Shape {

  private ShapeType type;
  private List<Animation> animations;

  public ShapeImpl(ShapeType type) {
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
  public Shape copy() {
    Shape clone = new ShapeImpl(this.type);
    for (Animation anim : this.animations) {
      clone.addAnimation(anim);
    }
    return clone;
  }

  @Override
  public List<Animation> getAnimations() {
    List<Animation> animationsCopy = new ArrayList<>();
    for (Animation anim : this.animations) {
      animationsCopy.add(anim.copy());
    }
    return animationsCopy;
  }
}
