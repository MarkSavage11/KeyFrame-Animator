package animation.model;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;

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

  @Override
  public Point getStartPosition() {
    return startPosition.getLocation();
  }

  @Override
  public Dimension getStartSize() {
    return startSize.getSize();
  }

  @Override
  public Color getStartColor() {
    return new Color(startColor.getRGB());
  }

  @Override
  public Point getEndPosition() {
    return endPosition.getLocation();
  }

  @Override
  public Dimension getEndSize() {
    return endSize.getSize();
  }

  @Override
  public Color getEndColor() {
    return new Color(endColor.getRGB());
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
    if (other.endTick() == this.startTick) {
      return !(other.getEndColor().equals(this.startColor) &&
              other.getEndPosition().equals(this.startPosition) &&
              other.getEndSize().equals(this.startSize));
    } else if (other.startTick() == this.endTick) {
      return !(other.getStartColor().equals(this.endColor) &&
              other.getStartPosition().equals(this.endPosition) &&
              other.getStartSize().equals(this.endSize));
    }
    return true;
  }

  private boolean doPropertiesOverlap(Animation other) {
    return other.getStartSize() != null ||
            other.getStartPosition() != null ||
            other.getStartColor() != null;
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
