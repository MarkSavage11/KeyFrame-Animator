package cs3500.animator.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;

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
   * @param name  The name of the shape to be added to the model
   * @param shape The type of shape to add to the model
   * @throws IllegalArgumentException if there already exists a shape with the given name, or if
   *                                  either the shape or the name is null
   */
  void addShape(String name, ShapeType shape) throws IllegalArgumentException;

  /**
   * Adds the given animation to the set of animations to apply to the named shape.
   *
   * @param shapeName The name of the shape to apply the animation to
   * @throws IllegalArgumentException If there exists no shape with the given name, or if the given
   *                                  animation does not fit, or if the tick times are out of order,
   *                                  or if any tick time is negative.
   */
  void addAnimation(String shapeName, Animation anim) throws IllegalArgumentException;

  /**
   * Adds an animation to the set of animations on a shape; the same as calling addAnimation with an
   * animation with all the given properties.
   *
   * @param shapeName     the name of the shape to add the animation to
   * @param startTick     the tick the animation starts
   * @param startPosition the position to start at
   * @param startSize     the size to start at
   * @param startColor    the color to start at
   * @param endTick       the tick the animation ends
   * @param endPosition   the position to end at
   * @param endSize       the size to end at
   * @param endColor      the color to end at
   * @throws IllegalArgumentException if startTick > endTick or if no shape exists with the given
   *                                  name or if the animation is inconsistent with the existing
   *                                  animations on the shape by overlapping or leaving a gap in the
   *                                  shape's timeline
   */
  void addAnimation(String shapeName,
                    int startTick, Point startPosition, Dimension startSize, Color startColor,
                    int endTick, Point endPosition, Dimension endSize, Color endColor)
          throws IllegalArgumentException;

  /**
   * Inserts a keyframe into the named shape's timeline, overwriting an existing keyframe if
   * necessary.
   *
   * @param shapeName the name of the shape to add the keyframe to
   * @param tick      the tick the keyframe occurs
   * @param keyframe  the state of the shape at that tick
   * @throws IllegalArgumentException if no shape exists with the given name
   */
  void insertKeyframe(String shapeName, int tick, State keyframe) throws IllegalArgumentException;

  /**
   * Deletes a keyframe from the named shape's timeline.
   *
   * @param shapeName the shape to remove the keyframe from
   * @param tick      the tick of the keyframe to remove
   * @throws IllegalArgumentException if no shape exists with the given name
   */
  void deleteKeyframe(String shapeName, int tick) throws IllegalArgumentException;

  /**
   * Deletes the shape with the given name from this model.
   *
   * @param shapeName name of the shape to be deleted
   * @throws IllegalArgumentException if the given name is null or not found in this model.
   */
  void deleteShape(String shapeName) throws IllegalArgumentException;

}
