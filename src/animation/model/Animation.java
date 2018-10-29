package animation.model;

import java.awt.*;

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
   * Gets the starting position for the animation.
   * @return the position the animation starts
   */
  Point getStartPosition();

  /**
   * Gets the ending position for the animation.
   * @return the position the animation ends
   */
  Point getEndPosition();

  /**
   * Gets the starting size for this animation.
   * @return the size at which the animation starts
   */
  Dimension getStartSize();

  /**
   * Gets the ending size for this animation.
   * @return the size at which the animation ends
   */
  Dimension getEndSize();

  /**
   * Gets the starting color for this animation.
   * @return the color at which the animation starts
   */
  Color getStartColor();

  /**
   * Gets the ending color for this animation.
   * @return the color at which the animation ends
   */
  Color getEndColor();

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
