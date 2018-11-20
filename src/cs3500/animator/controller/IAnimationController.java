package cs3500.animator.controller;

import cs3500.animator.model.ShapeType;
import cs3500.animator.model.State;

public interface IAnimationController {

  /**
   * Deletes the given shape from the model.
   *
   * @param name the name of the shape to delete
   */
  void deleteShape(String name);

  /**
   * Adds a shape with a given name and type.
   *
   * @param name the name of the shape to be added
   * @param type the type of the shape to be added
   */
  void addShape(String name, ShapeType type);

  /**
   * Adds a keyframe with a given tick and state to the given shape.
   *
   * @param shapeName the name of the shape the keyframe is being added to
   * @param tick the tick of the keyframe
   * @param state the state of the keyframe
   */
  void addKeyframe(String shapeName, int tick, State state);

  //May be redundant.
  void editKeyFrame(String shapeName, int tick, State state);

  void deleteKeyFrame(String shapeName, int tick);
}
