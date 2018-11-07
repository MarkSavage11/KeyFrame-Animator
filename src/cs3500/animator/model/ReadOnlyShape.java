package cs3500.animator.model;

import java.util.List;

public interface ReadOnlyShape {
  /**
   * Gets the type of shape this represents.
   *
   * @return A ShapeType corresponding to the type of shape this is
   */
  ShapeType getType();

  List<Animation> getAnimations();

  State getStateAt(int tick);
}
