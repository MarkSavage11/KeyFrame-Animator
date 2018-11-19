package cs3500.animator.model;

import java.awt.*;

/**
 * Implementation of the State interface. Its used by animations to present all of a shapes
 * information at a given time.
 */
public class StateImpl implements State {

  private Point position;
  private Dimension size;
  private Color color;

  /**
   * Constructs a state with a given position, size, and color.
   *
   * @param position the position in this state
   * @param size the size in this state
   * @param color the color in this state
   */
  public StateImpl(Point position, Dimension size, Color color) {
    this.position = position;
    this.size = size;
    this.color = color;
  }

  @Override
  public Point getPosition() {
    return new Point(position);
  }

  @Override
  public Dimension getSize() {
    return new Dimension(size);
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof State)) {
      return false;
    } else {
      State otherState = (State) other;
      return otherState.getPosition().equals(this.getPosition()) &&
              otherState.getSize().equals(this.getSize()) &&
              otherState.getColor().equals(this.getColor());
    }
  }

  @Override
  public int hashCode() {
    return position.hashCode() + size.hashCode() + color.hashCode();
  }
}
