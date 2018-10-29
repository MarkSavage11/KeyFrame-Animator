package animation.model;

import java.util.List;

/**
 * A shape which can have animations applied to it. Maintains a list of its animations and can add
 * more if they do not conflict with existing ones.
 */
public interface Shape {
  /**
   * Adds the given animation to the set to apply to this shape.
   *
   * @param anim The animation to add
   * @throws IllegalArgumentException If the animation overlaps another animation in the shape, if
   *                                  the animation conflicts with the adjacent animations about the
   *                                  state of the shape, or if the animation is null.
   */
  void addAnimation(Animation anim) throws IllegalArgumentException;

  /**
   * Gets the type of shape this represents.
   *
   * @return A ShapeType corresponding to the type of shape this is
   */
  ShapeType getType();

  Shape copy();

  List<Animation> getAnimations();
}
