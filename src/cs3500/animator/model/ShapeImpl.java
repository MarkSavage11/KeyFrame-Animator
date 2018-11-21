package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.UnaryOperator;

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
  public void insertKeyframe(int tick, State frame) {
    if (animations.size() == 0) {
      animations.add(new BasicAnimation(tick, frame, tick, frame));
    } else if (tick < animations.get(0).startTick()) {
      animations.add(0, new BasicAnimation(tick, frame, animations.get(0).startTick(),
              animations.get(0).startState()));
    } else if (tick > animations.get(animations.size() - 1).endTick()) {
      animations.add(new BasicAnimation(animations.get(animations.size() - 1).endTick(),
              animations.get(animations.size() - 1).endState(), tick, frame));
    } else {
      replaceOverlap(tick, (overlap) -> Arrays.asList(
              new BasicAnimation(overlap.get(0).startTick(),
                      overlap.get(0).startState(), tick, frame),
              new BasicAnimation(tick, frame, overlap.get(overlap.size() - 1).endTick(),
                      overlap.get(overlap.size() - 1).endState())));
    }
  }

  @Override
  public void deleteKeyframe(int tick) {
    if (animations.size() == 0) {
      return;
    } else if (animations.size() == 1 &&
            animations.get(0).startTick() == animations.get(0).endTick()) {
      animations.remove(0);
    }
    if (tick == animations.get(0).startTick()) {
      if (animations.size() == 1) {
        animations.set(0, new BasicAnimation(animations.get(0).endTick(),
                animations.get(0).endState(), animations.get(0).endTick(),
                animations.get(0).endState()));
      } else {
        animations.remove(0);
      }
    } else if (tick == animations.get(animations.size() - 1).endTick()) {
      if (animations.size() == 1) {
        animations.set(0, new BasicAnimation(animations.get(0).startTick(),
                animations.get(0).startState(), animations.get(0).startTick(),
                animations.get(0).startState()));
      } else {
        animations.remove(animations.size() - 1);
      }
    } else if (tick > animations.get(0).startTick() &&
            tick < animations.get(animations.size() - 1).endTick()) {
      replaceOverlap(tick, (overlap) -> Collections.singletonList(
              new BasicAnimation(overlap.get(0).startTick(),
                      overlap.get(0).startState(),
                      overlap.get(overlap.size() - 1).endTick(),
                      overlap.get(overlap.size() - 1).endState())));
    }
  }

  private void replaceOverlap(int tick, UnaryOperator<List<Animation>> replacer) {
    List<Animation> overlap = new ArrayList<>();
    for (Animation anim : animations) {
      if (tick >= anim.startTick() && tick <= anim.endTick()) {
        overlap.add(anim);
      }
    }
    int index = animations.indexOf(overlap.get(0));
    animations.removeAll(overlap);
    animations.addAll(index, replacer.apply(overlap));
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public List<Animation> getAnimations() {
    return Collections.unmodifiableList(animations);
  }

  @Override
  public SortedMap<Integer, State> getKeyframes() {
    SortedMap<Integer, State> out = new TreeMap<>();
    if (animations.size() > 0) {
      out.put(animations.get(0).startTick(), animations.get(0).startState());
    }
    for (Animation anim : animations) {
      out.put(anim.endTick(), anim.endState());
    }
    return out;
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
