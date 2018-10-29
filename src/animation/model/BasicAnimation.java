package animation.model;

import java.awt.*;

public class BasicAnimation implements Animation {

  private int startTick;
  private Point startPosition;
  private Dimension startSize;
  private Color startColor;
  private int endTick;
  private Point endPosition;
  private Dimension endSize;
  private Color endColor;

  public BasicAnimation(int startTick, Point startPosition, Dimension startSize, Color startColor,
                        int endTick, Point endPosition, Dimension endSize, Color endColor) {
    this.startTick = startTick;
    this.startPosition = startPosition;
    this.startSize = startSize;
    this.startColor = startColor;
    this.endTick = endTick;
    this.endPosition = endPosition;
    this.endSize = endSize;
    this.endColor = endColor;
  }

  @Override
  public int startTick() {
    return startTick;
  }

  @Override
  public int endTick() {
    return endTick;
  }

  public Point getStartPosition() {
    return startPosition;
  }

  public Dimension getStartSize() {
    return startSize;
  }

  public Color getStartColor() {
    return startColor;
  }

  public Point getEndPosition() {
    return endPosition;
  }

  public Dimension getEndSize() {
    return endSize;
  }

  public Color getEndColor() {
    return endColor;
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

  private boolean doPropertiesConflict(Animation other) {
    return (other.getStartColor() != this.endColor || other.getEndColor() != this.startColor) ||
            (other.getStartPosition() != this.endPosition || other.getEndPosition() != this.startPosition) ||
            (other.getStartSize() != this.endSize || other.getEndSize() != this.startSize);

  }

  private boolean doPropertiesOverlap(Animation other) {
    return other.getStartSize() == null &&
            other.getStartPosition() == null &&
            other.getStartColor() == null;
  }


  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("" + startTick() + " " + startPosition.getX() + " " + startPosition.getY() + " "
            + startSize.getWidth() + " " + startSize.getHeight() + " "
            + startColor.getRed() + " " + startColor.getGreen() + " " + startColor.getBlue());
    result.append("    ");
    result.append("" + endTick() + " " + endPosition.getX() + " " + endPosition.getY() + " "
            + endSize.getWidth() + " " + endSize.getHeight() + " "
            + endColor.getRed() + " " + endColor.getGreen() + " " + endColor.getBlue());

    return result.toString();

  }
}
