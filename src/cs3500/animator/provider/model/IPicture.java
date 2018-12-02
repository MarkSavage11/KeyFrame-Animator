package cs3500.animator.provider.model;

import java.util.ArrayList;

/**
 * Interface that represents a Picture. A picture is a piece of an animation that has as list of
 * {@link IMotion} motions. Each picture is associated with its list of motions that must be ordered
 * by time for future execution. A picture supports printing itself and adding motions, and getting
 * motions specified at a certain time.
 */
public interface IPicture {

  /**
   * Prints the picture as a string (the motions associated).
   *
   * @return The full table of motions in the Picture as a String
   */
  String asString();

  /**
   * Adds a motion to the list of a Picture's motions.
   *
   * @param m The motion to be added.
   * @throws IllegalArgumentException if the motion could not be added
   */
  void addMotion(IMotion m) throws IllegalArgumentException;

  /**
   * Retreives a list of motions at time t from a picture. Also initializes the pictures features if
   * it is the first motion added.
   *
   * @param time specified time
   * @return a list of motions from a picture at the specified time
   */
  ArrayList<IMotion> getMotionsAtTime(int time);

  /**
   * Determines if this is, in fact the picture we are looking for.
   *
   * @param s alias we are looking for
   * @return boolean if this is the picture that matches the string.
   */
  boolean isThisPicture(String s);


  /**
   * Gets the type of shape this picture is.
   *
   * @return type of shape
   */
  String shapeType();

  /**
   * Gets the red component.
   *
   * @return the red component.
   */
  int getR();

  /**
   * Gets the blue component.
   *
   * @return the G component.
   */
  int getG();


  /**
   * Gets the blue component.
   *
   * @return the blue component.
   */
  int getB();

  /**
   * Gets the x coordinate.
   *
   * @return the x coordinate int.
   */
  int getx();

  /**
   * Gets the y coordinate.
   *
   * @return the y coordinate int.
   */
  int gety();

  /**
   * Gets the width of the picture.
   *
   * @return the width of the picture
   */
  int getWidth();


  /**
   * Gets the height of the picture.
   *
   * @return the height of the picture
   */
  int getHeight();


  /**
   * Removes the last motion in this picture.
   */
  void removeLastMotion();

  /**
   * Gets all the states in this picture for drawing purposes. This includes the interpolated States
   * using the tweening method.
   *
   * @return an array list of states
   */
  ArrayList<IState> getStates();

  /**
   * Renders this picture as its SVG format. Will add initial state variables and animate tags as
   * necessary.
   *
   * @param xOffset offset for X variables.
   * @param yOffset offset for Y variables.
   * @return String that contains SVG representation of this picture.
   */
  String svgRender(int xOffset, int yOffset, int speed);

  /**
   * Returns the motion that either starts or ends at the given time. Does not return a motion that
   * ends at the given time. If no motion contains this time, throws Illegal Arg Exception
   *
   * @param time time at which the motion is requested.
   * @return the motion at the time requested
   */
  IMotion motionAtTime(int time);

  /**
   * Returns the last motion of this pictures animation.
   *
   * @return the motion with the greatest start time
   */
  IMotion getLastMotion();

  /**
   * Replaces some motions with the motions in the given list. The original list overall start/end
   * times and the resulting start/end times should be equal. Motions in each list must be
   * consecutive!!
   *
   * @param toRemove motion(s) to be removed from animation.
   * @param toAdd    motion(s) to be added in in toRemove's place.
   */
  void replaceMotion(ArrayList<IMotion> toRemove, ArrayList<IMotion> toAdd);

  /**
   * Gets the alias of this picture so the user can reference it.
   *
   * @return The String alias.
   */
  String getAlias();

  /**
   * Returns a list of all motions containing the key frame. Case 1: key frame is first in
   * animation, so returns 1 motion. Case 2: key frame is in middle of animation, so returns before
   * and after motions. Case 3: key frame is end of the animation, so returns 1 motion. Throws an
   * exception if time is not found in the list of motions.
   *
   * @param time time of the key frame.
   * @return the two motions surrounding the key frame.
   */
  ArrayList<IMotion> motionsOfKeyFrame(int time);

  /**
   * Returns a copy of all the motions in this picture.
   *
   * @return copy of all motions in this picture.
   */
  ArrayList<IMotion> copyMotions();
}
