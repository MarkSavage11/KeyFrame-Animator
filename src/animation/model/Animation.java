package animation.model;

/**
 * An animation, which has a start and end time, and may define a change (or lack thereof) to any of
 * position, size, and color. If it does not modify a property, both the getStart and getEnd methods
 * for that property will return null.
 */
public interface Animation {
  /**
   * Gets the tick on which this animation begins.
   *
   * @return the tick this animation begins on
   */
  int startTick();

  /**
   * Gets the tick on which this animation ends.
   *
   * @return the tick this animation ends on
   */
  int endTick();

  boolean hasProperty(AnimatableProperty prop);

  Object getPropertyAtStart(AnimatableProperty prop);

  Object getPropertyAtEnd(AnimatableProperty prop);

  /**
   * Would the given animation conflict with this animation if they were placed in the same
   * timeline? Returns false if the animations try to change the same property at the same time, and
   * false otherwise. Also returns false if the animations overlap by one frame (one starts where
   * the other ends) and they demand different values for a property at that time.
   *
   * @param other the other animation to compare to
   * @return true if the animations conflict, false otherwise
   */
  boolean conflictsWith(Animation other);
}
