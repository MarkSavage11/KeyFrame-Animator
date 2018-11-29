package cs3500.animator.provider;


import cs3500.animator.provider.view.IAnimateView;

/**
 * Controller interface will play the animation and handle any keystrokes. The
 * cs3500.animator.controller is only used with visual view types.
 */
public interface IController {

  /**
   * Plays the animation and handles any keystroke input that the user enters. Will also handle any
   * speed changes that the user inputs.
   *
   * @param animateView The animation view that the Controller will handle actions on
   */
  void launchAnimation(IAnimateView animateView);

}
