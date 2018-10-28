package animation.model;

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

  /**
   * Does the end of this Animation align with the start of other? (i.e., does this end at the same
   * time and with all the same properties as the given animation starts?)
   *
   * @return whether the Animations align
   */
  boolean alignsWith(Animation other);
}
