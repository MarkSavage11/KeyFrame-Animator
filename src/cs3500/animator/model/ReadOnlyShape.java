package cs3500.animator.model;

import java.util.List;
import java.util.SortedMap;

/**
 * A read only shape. Maintains a list of its animations, its type, and can return its state given a
 * tick.
 */
public interface ReadOnlyShape {
  /**
   * Gets the type of shape this represents.
   *
   * @return A ShapeType corresponding to the type of shape this is
   */
  ShapeType getType();

  /**
   * Gets the list of animations in this shape.
   *
   * @return a list of animations in this shape.
   */
  List<Animation> getAnimations();

  /**
   * Gets a list of animations in this shape expressed as keyframes.
   *
   * @return a list of keyframes in this shape.
   */
  SortedMap<Integer, State> getKeyframes();

  /**
   * Gets the state of this shape at a given tick.
   *
   * @param tick the time in ticks.
   * @return the state of this shape at the given tick.
   */
  State getStateAt(int tick);
}
