package cs3500.animator.provider.view;


import cs3500.animator.provider.model.ImmutableModel;

/**
 * The view interface for an Animation. A view has can either be textual or SVG {the sister class is
 * visual view}. A view has one job, to display the animation given the input.
 */

public interface IView {

  /**
   * Renders the given animation model.
   *
   * @param model  immutable model to be rendered.
   * @param output where the output should be sent.
   */
  void displayAnimation(ImmutableModel model, Appendable output);


}
