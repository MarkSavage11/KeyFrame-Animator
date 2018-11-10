package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Standard implementation of the shape interface. Holds its type and its animations.
 */
public class ShapeImpl implements Shape {

  private ShapeType type;
  private List<Animation> animations;

  /**
   * Constructs an empty shape with the given type.
   *
   * @param type the type of shape this shape is.
   */
  public ShapeImpl(ShapeType type) {
    this.type = type;
    animations = new ArrayList<>();
  }

  @Override
  public void addAnimation(Animation anim) throws IllegalArgumentException {
    if (anim == null) {
      throw new IllegalArgumentException("Animation cannot be null");
    }

    if (animations.size() > 0 &&
            (anim.startTick() != animations.get(animations.size() - 1).endTick() ||
                    !anim.startState().equals(animations.get(animations.size() - 1).endState()))) {
      throw new IllegalArgumentException("Non-continuous animations");
    }

    animations.add(anim);
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public List<Animation> getAnimations() {
    return new ArrayList<>(animations);
  }

  @Override
  public State getStateAt(int tick) {
    for (Animation anim : animations) {
      if (tick >= anim.startTick() && tick <= anim.endTick()) {
        return anim.getStateAt(tick);
      }
    }
    throw new IllegalArgumentException("Tick out of bounds for animations on this shape.");
  }
}
