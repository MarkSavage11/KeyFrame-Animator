package cs3500.animator.controller;

import cs3500.animator.model.ShapeType;
import cs3500.animator.model.State;
import cs3500.animator.view.AnimationView;

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
   * @param tick      the tick of the keyframe
   * @param state     the state of the keyframe
   */
  void addKeyframe(String shapeName, int tick, State state);

  /**
   * Edits the keyframe of the given shape at the given tick to have the given state.
   *
   * @param shapeName the name of the shape the keyframe belongs to
   * @param tick      the tick at which the keyframe occurs
   * @param state     the state of the keyframe
   */
  void editKeyFrame(String shapeName, int tick, State state);

  /**
   * Deletes the keyframe of the given shape at the given tick.
   *
   * @param shapeName the name of the shape that the keyframe belongs to
   * @param tick      the tick the keyframe occurs
   */
  void deleteKeyFrame(String shapeName, int tick);

  /**
   * Runs the controller, displaying to the given view.
   *
   * @param view the view to use to display
   */
  void run(AnimationView view);
}
