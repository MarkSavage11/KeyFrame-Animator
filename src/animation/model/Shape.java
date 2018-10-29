package animation.model;

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
   * @return A ShapeType corresponding to the type of shape this is
   */
  ShapeType getType();

  /**
   * Gets the name of this shape.
   * @return the name of the shape
   */
  String getName();
}
