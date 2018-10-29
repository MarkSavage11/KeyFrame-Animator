package animation.model;

import java.awt.*;

public class BasicAnimation implements Animation {

  BasicAnimation(int startTick, Point startPosition, Dimension startSize, Color startColor,
                 int endTick, Point endPosition, Dimension endSize, Color endColor) {

  }

  @Override
  public int startTick() {
    return 0;
  }

  @Override
  public int endTick() {
    return 0;
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
    return false;
  }
}
