package cs3500.animator.provider.model;

import java.util.ArrayList;

public interface IMotion {

  /**
   * Prints out the String representation of a motion.
   *
   * @return motion as a String.
   */
  String asString();

  /**
   * Gets the start time of this motion.
   *
   * @return start time
   */
  int getStartTime();

  /**
   * Gets the end time of this motion.
   *
   * @return end time
   */
  int getEndTime();

  /**
   * Returns the start state of this motion to get pass the initial appearence of the shape.
   *
   * @return the start state
   */
  State getStartState();

  /**
   * Returns the end state of this motion to get pass the initial appearence of the shape.
   *
   * @return the end state
   */
  State getEndState();

  /**
   * Gets the intermediate shapes to be drawn between a motion.
   *
   * @return ArrayList of states
   */
  ArrayList<State> interpolateShapes();

  /**
   * Renders this motion as a string in SVG format. If this motion has no change across it, returns
   * an empty string because animate tag only cares if there is a change.
   *
   * @param xOffset offset for X variables.
   * @param yOffset offset for Y variables.
   * @return the string representation of this motion in SVG format.
   */
  String svgRender(int xOffset, int yOffset, int speed);



}
