package cs3500.animator.model;

/**
 * A model which handles shapes and animations. Can have shapes added to the set of animations it
 * tracks (the shapes must be named so as to be referenced later) and add animations to shapes based
 * on their name.
 */
public interface AnimationModel extends ReadOnlyAnimationModel {
  /**
   * Adds the given shape to the set of shapes tracked by this model, and assigns it the given name.
   * When later referencing the shape, method invocations should use the name assigned here.
   *
   * @param name The name of the shape to be added to the model
   * @param shape The type of shape to add to the model
   * @throws IllegalArgumentException if there already exists a shape with the given name, or if
   *                                  either the shape or the name is null
   */
  void addShape(String name, ShapeType shape) throws IllegalArgumentException;

  /**
   * Adds the given animation to the set of animations to apply to the named shape.
   *
   * @param shapeName The name of the shape to apply the animation to
   * @throws IllegalArgumentException If there exists no shape with the given name,
   * or if the given animation does not fit, or if the tick times are out of order,
   * or if any tick time is negative.
   */
  void addAnimation(String shapeName, Animation anim) throws IllegalArgumentException;

  void addKeyframe(String shapeName, int tick, State keyframe) throws IllegalArgumentException;

  void insertKeyframe(String shapeName, int tick, State keyframe) throws IllegalArgumentException;

  void deleteKeyframe(String shapeName, int tick) throws IllegalArgumentException;
}
