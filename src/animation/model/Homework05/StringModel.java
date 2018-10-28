package animation.model.Homework05;

import animation.model.AnimationModel;

/**
 * A model which can generate a string representation of its state for analysis.
 */
public interface StringModel extends AnimationModel {
  /**
   * Gets a string representation of the state of the model. Format follows that described in the
   * assignment.
   *
   * @return a string representing the state of the model
   */
  String getState();
}
