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
   * Would the given animation conflict with this animation if they were placed in the same
   * timeline? Details of what "conflicting" mean dependent on the implementation, but generally
   * speaking will return false if the animations try to change the same property at the same time,
   * and false otherwise.
   *
   * @param other the other animation to compare to
   * @return true if the animations conflict, false otherwise
   */
  boolean conflictsWith(Animation other);
}
