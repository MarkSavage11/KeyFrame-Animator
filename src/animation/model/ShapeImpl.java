package animation.model;

import java.util.ArrayList;
import java.util.List;

public class ShapeImpl implements Shape {



  private ShapeType type;
  //this has to be protected for the purposes of generating a string representation of the state -
  //I don't like it any more than you do.
  protected List<Animation> animations;

  public ShapeImpl(ShapeType type) {
    this.type = type;
    animations = new ArrayList<>();
  }

  @Override
  public void addAnimation(Animation anim) throws IllegalArgumentException {
    for(Animation existingAnim : animations) {
      if (existingAnim.conflictsWith(anim)) {
        throw new IllegalArgumentException("The given animation conflicts with an existing animation.");
      }
    }
    animations.add(anim);
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }
}
