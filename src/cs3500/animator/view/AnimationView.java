package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyAnimationModel;

/**
 * A view that displays an Animation model in some fashion.
 */
public interface AnimationView {
  /**
   * Displays the information in the given model. The way it does this depends on the
   * implementation.
   *
   * @param model the animation model to display.
   * @throws IllegalStateException reasons for this error depends on the implementation.
   */
  void display(ReadOnlyAnimationModel model) throws IllegalStateException;

}
