package cs3500.animator.model;

/**
 * An animation, which has a start and end time, and may define any of position, size, and color. If
 * it does not modify a property, both the getStart and getEnd methods for that property will return
 * null.
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

  State startState();

  State endState();

  /**
   * Would the given animation conflict with this animation if they were placed in the same
   * timeline? Details of what "conflicting" mean dependent on the implementation, but generally
   * speaking will return true if the animations try to change the same property at the same time,
   * and false otherwise.
   *
   * @param other the other animation to compare to
   * @return true if the animations conflict, false otherwise
   */
  boolean conflictsWith(Animation other);

  //tentative

  State getStateAt(int tick);
}
