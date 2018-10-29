package animation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * INVARIANT: if startState has a key, endState should also have a key of that type.
 */
public class BasicAnimation implements Animation {

  private int startTick;
  private int endTick;

  private Map<AnimateableProperty, List<Integer>> startState;
  private Map<AnimateableProperty, List<Integer>> endState;

  public BasicAnimation(int startTick, int endTick) {
    if (endTick < 0 || startTick < 0 || endTick - startTick <= 0) {
      throw new IllegalArgumentException("Illegal start-end tick pair");
    }
    this.startTick = startTick;
    this.endTick = endTick;

    this.startState = new HashMap<>();
    this.endState = new HashMap<>();
  }

  @Override
  public int startTick() {
    return startTick;
  }

  @Override
  public int endTick() {
    return endTick;
  }

  @Override
  public boolean hasProperty(AnimateableProperty prop) {
    return this.startState.containsKey(prop);
  }

  @Override
  public List<Integer> getPropertyAtStart(AnimateableProperty prop) {
    if (this.startState.containsKey(prop)) {
      return this.startState.get(prop);
    } else {
      throw new IllegalArgumentException("Unsupported property");
    }
  }

  @Override
  public List<Integer> getPropertyAtEnd(AnimateableProperty prop) {
    if (this.endState.containsKey(prop)) {
      return this.endState.get(prop);
    } else {
      throw new IllegalArgumentException("Unsupported property");
    }
  }

  @Override
  public Animation copy() {
    Animation clone = new BasicAnimation(startTick, endTick);
    for (AnimateableProperty prop : this.startState.keySet()) {
      clone.addAnimation(prop,
              new ArrayList<>(startState.get(prop)),
              new ArrayList<>(startState.get(prop)));
    }
    return clone;
  }

  public void addAnimation(AnimateableProperty prop, List<Integer> start, List<Integer> end) {
    if (prop.validState(start) && prop.validState(end)) {
      this.startState.put(prop, start);
      this.endState.put(prop, end);
    }
  }

  /**
   * Determines whether the animation conflicts with another animation. Will return false if the
   * animation tries to modify position, color, or size at the same time as this, or if such an
   * animation overlaps at the edge (by exactly one tick) but defines a different property for that
   * tick.
   *
   * @param other the other Animation to compare to
   * @return true if the animations conflict, false otherwise
   */
  public boolean conflictsWith(Animation other) {
    if ((other.startTick() > this.startTick && other.startTick() < this.endTick) ||
            (other.endTick() < this.endTick && other.endTick() > this.startTick)) { //overlap
      for (AnimateableProperty prop : this.startState.keySet()) {
        if (other.hasProperty(prop)) {
          return true;
        }
      }
    } else { //meet at an edge
      if (this.startTick == other.endTick()) {
        for (AnimateableProperty prop : this.startState.keySet()) {
          if (other.hasProperty(prop) && !this.startState.get(prop)
                  .equals(other.getPropertyAtEnd(prop))) {
            return true;
          }
        }
      } else {
        for (AnimateableProperty prop : this.startState.keySet()) {
          if (other.hasProperty(prop) && !this.endState.get(prop)
                  .equals(other.getPropertyAtStart(prop))) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
