package animation.model;

import java.util.Map;

/**
 * A model which handles shapes and animations. Can have shapes added to the set of animations it
 * tracks (the shapes must be named so as to be referenced later) and add animations to shapes based
 * on their name.
 */
public interface AnimationModel {
  /**
   * Adds the given shape to the set of shapes tracked by this model, and assigns it the given name.
   * When later referencing the shape, method invocations should use the name assigned here.
   *
   * @param name  the name to give the shape
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

  /**
   * Returns a copy of the state of the model so it can be displayed by the view.
   *
   * @return a copy of this model's state.
   */
  Map<String, Shape> getState();
}
