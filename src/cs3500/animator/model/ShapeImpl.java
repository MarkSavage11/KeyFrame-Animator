package cs3500.animator.model;

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
      if (animationsConflict(existingAnim, anim)) {
        throw new IllegalArgumentException
                ("The given animation conflicts with an existing animation.");
      }
    }
    animations.add(anim);
  }

  private boolean animationsConflict(Animation a, Animation b) {
    if ((a.endTick() > b.startTick() && a.endTick() < b.endTick()) ||
            (a.startTick() > b.startTick() && a.startTick() < b.endTick())) {
      return true;
    } else if (a.endTick() == b.startTick()) {
      return !a.endState().equals(b.startState());
    } else if (a.startTick() == b.endTick()) {
      return !a.startState().equals(b.endState());
    } else {
      return false;
    }
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
    for (Animation anim: animations) {
      if (tick >= anim.startTick() && tick <= anim.endTick()) {
        return anim.getStateAt(tick);
      }
    }
    throw new IllegalArgumentException("Tick out of bounds for animations on this shape.");
  }
}
