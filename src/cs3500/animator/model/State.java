package cs3500.animator.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Represents the state of a shape, holding a position, size, and color.
 */
public interface State {

  /**
   * Gets the position.
   *
   * @return the position
   */
  Point getPosition();

  /**
   * Gets the size.
   *
   * @return the size
   */
  Dimension getSize();

  /**
   * Gets the color.
   *
   * @return the color
   */
  Color getColor();
}
