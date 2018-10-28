package animation.model.Homework05;

import java.util.List;

import animation.model.Animation;
import animation.model.Shape;

/**
 * An interface which exposes more information so that a string representation of the model can be
 * generated.
 */
public interface AnimAccessibleShape extends Shape {
  /**
   * Gets a copy of the list of animations for this shape.
   * @return a copy of the list of animations for this shape
   */
  List<Animation> getAnimations();
}
