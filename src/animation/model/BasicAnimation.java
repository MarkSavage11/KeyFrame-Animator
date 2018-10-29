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
    throw new UnsupportedOperationException("Not implemented yet");
  }


  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("" + startTick() + " " + startPosition.getX() + " " + startPositon.getY() + " "
            + startSize.getWidth() + " " + startSize.getHeight() + " "
            + startColor.getRed() + " " + startColor.getGreen() + " " + startColor.getBlue());
    result.append("    ");
    result.append("" + endTick() + " " + endPosition.getX() + " " + endPositon.getY() + " "
            + endSize.getWidth() + " " + endSize.getHeight() + " "
            + endColor.getRed() + " " + endColor.getGreen() + " " + endColor.getBlue());

    return result.toString();

  }
}
