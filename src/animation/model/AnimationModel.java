package animation.model;

public interface AnimationModel {
  /**
   * Adds the given shape to the set of shapes tracked by this model, and assigns it the given name.
   * When later referencing the shape, method invocations should use the name assigned here.
   *
   * @param name  The name to give the shape
   * @param shape The shape to add to the model
   * @throws IllegalArgumentException if there already exists a shape with the given name, or if
   *                                  either the shape or the name is null
   */
  void addShape(String name, Shape shape) throws IllegalArgumentException;

  /**
   * Adds the given animation to the set of animations to apply to the named shape.
   *
   * @param shapeName The name of the shape to apply the animation to
   * @param anim      The animation to apply
   * @throws IllegalArgumentException If there exists no shape with the given name, or if either
   *                                  argument is null.
   */
  void addAnimation(String shapeName, Animation anim) throws IllegalArgumentException;
}
