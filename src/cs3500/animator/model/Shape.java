package cs3500.animator.model;

/**
 * A shape which can have animations applied to it. Maintains a list of its animations and can add
 * more if they do not conflict with existing ones.
 */
interface Shape extends ReadOnlyShape {
  /**
   * Adds the given animation to the set to apply to this shape.
   *
   * @param anim The animation to add
   * @throws IllegalArgumentException If the animation overlaps another animation in the shape, if
   *                                  the animation conflicts with the adjacent animations about the
   *                                  state of the shape, or if the animation is null.
   */
  void addAnimation(Animation anim) throws IllegalArgumentException;

  void insertKeyframe(int tick, State frame);

  void deleteKeyframe(int tick);
}
