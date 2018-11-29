package cs3500.animator.provider.model;

import java.util.ArrayList;

import cs3500.animator.model.State;

/**
 * Passed to the view so that the user can view the pictures/states that comprise the animation. to
 * render the animation.
 */
public interface ImmutableModel {

  /**
   * Returns a list of pictures in this model.
   *
   * @return List of Ipictures.
   */
  ArrayList<IPicture> getPictures();


  /**
   * Returns a list of states at given time.
   *
   * @param currentTime the current time to get states
   * @return an arraylist of states
   */
  ArrayList<State> getStatesAtTime(int currentTime);

  /**
   * Returns an arraylist with integers that represent the bounds of the canvas. The order of the
   * list is x,y,width,height
   *
   * @return arraylist of integers that represents the bounds of the canvas.
   */
  ArrayList<Integer> getCanvasBounds();

  /**
   * Returns the last time in the animation. This is used to know when to loop the animation.
   *
   * @return the last time in the animation.
   */
  int lastTime();


}
