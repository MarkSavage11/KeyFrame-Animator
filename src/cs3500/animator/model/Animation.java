package cs3500.animator.model;

/**
 * An animation, which has a start and end time and state.
 * It will be attached to a shape to define its behavior over a specific duration.
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

  /**
   * Gets the state with which this animation begins.
   *
   * @return the state with which this animation begins
   */
  State startState();

  /**
   * Gets the state with which this animation ends.
   *
   * @return the state with which this animation ends
   */
  State endState();

  /**
   * Gets the state of this animation on the given tick.
   *
   * @param tick time in ticks in this animation
   * @return the state on the given tick
   */
  State getStateAt(int tick);
}
