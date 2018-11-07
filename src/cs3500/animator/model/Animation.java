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

  State startState();

  State endState();

  State getStateAt(int tick);
}
