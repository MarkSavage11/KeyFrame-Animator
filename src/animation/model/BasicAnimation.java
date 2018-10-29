package animation.model;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import java.util.Map;

public class BasicAnimation implements Animation {

  private int startTick;
  private int endTick;

  private Map<AnimatableProperty, >

  public BasicAnimation(int startTick, int endTick) {
    this.startTick = startTick;
    this.endTick = endTick;
  }

  @Override
  public int startTick() {
    return startTick;
  }

  @Override
  public int endTick() {
    return endTick;
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
    if (other.endTick() < this.startTick || other.startTick() > this.endTick) {
      return false;
    } else if ((other.startTick() > this.startTick && other.startTick() < this.endTick) ||
            (other.endTick() < this.endTick && other.endTick() > this.startTick)) {
      return doPropertiesOverlap(other);
    } else {
      return doPropertiesConflict(other);
    }
  }


  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("" + startTick() + " " + startPosition.x + " " + startPosition.y + " "
            + startSize.width + " " + startSize.height + " "
            + startColor.getRed() + " " + startColor.getGreen() + " " + startColor.getBlue());
    result.append("    ");
    result.append("" + endTick() + " " + endPosition.x + " " + endPosition.y + " "
            + endSize.width + " " + endSize.height + " "
            + endColor.getRed() + " " + endColor.getGreen() + " " + endColor.getBlue());

    return result.toString();

  }
}
