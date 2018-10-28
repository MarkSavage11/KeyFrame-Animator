package animation.model.Homework05;

import java.awt.Color;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

import animation.model.Animation;

/**
 * An interface which exposes more information so that a string representation of the model can be
 * generated.
 */
public interface AccessibleAnimation extends Animation {
  /**
   * Gets the starting position for the animation.
   * @return the position the animation starts
   */
  Point2D startPosn();

  /**
   * Gets the ending position for the animation.
   * @return the position the animation ends
   */
  Point2D endPosn();

  /**
   * Gets the starting size for this animation.
   * @return the size at which the animation starts
   */
  Dimension2D startSize();

  /**
   * Gets the ending size for this animation.
   * @return the size at which the animation ends
   */
  Dimension2D endSize();

  /**
   * Gets the starting color for this animation.
   * @return the color at which the animation starts
   */
  Color startColor();

  /**
   * Gets the ending color for this animation.
   * @return the color at which the animation ends
   */
  Color endColor();
}
