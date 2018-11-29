package cs3500.animator.provider.model;

import java.util.ArrayList;


/**
 * Interface for a visual animation. An animation represents a collection of shapes that have their
 * respective motions. An animation is comprised of a series of motions and shapes. An IAnimation is
 * a model of a visual animation and holds the data to evenually animate. Provides methods for a
 * user to add and delete motions, and observe the data in the Animation but not to mutate any of
 * it.
 */
public interface IAnimation {

  /**
   * Adds a picture to the animation's list of pictures. Takes in the picture to be added.
   *
   * @param shapeName type of the picture shape to be added
   * @param alias     alias of the picture to be added
   * @throws IllegalArgumentException if the picture added is a null.
   */
  void addPicture(String shapeName, String alias) throws IllegalArgumentException;


  /**
   * Returns a list of all motions from the pictures that an animation has that occur at the
   * specified time.
   *
   * @param time specified time.
   * @return Array list of Motions
   */
  ArrayList<Motion> getMotionsAtTime(int time);

  /**
   * Returns a list of allthe pictures in the animation.
   *
   * @return list of pictures in the animation.
   */

  ArrayList<IPicture> getPictures();

  /**
   * Adds a motion to the given animation. Specifies the name of the picture to add the motion to.
   * If any of the inputs are invalid, will throw an exception.
   *
   * @param name The name of the picture you want to add motions to
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   * @throws IllegalArgumentException - when fields are not valid for motion.
   */
  void addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1,
                 int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);

  /**
   * For now, removes the last motion from the specified picture.
   *
   * @param alias the alias of the picture to be removed
   */
  void removeMotionFrom(String alias);

  /**
   * Removes the specified picture from the animation and its motions.
   *
   * @param alias The alias of the shape to be removed.
   */
  void removePicture(String alias);

  /**
   * Returns the bounds of the canvas of the animation. The return list is in order x,y,height,
   * width
   *
   * @return list of integers with the information about the bounds of the canvas.
   */
  ArrayList<Integer> getBounds();

  /**
   * Adds an individual keyframe to the animation.
   *
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @param x    The x-position of the shape
   * @param y    The y-position of the shape
   * @param w    The width of the shape
   * @param h    The height of the shape
   * @param r    The red color-value of the shape
   * @param g    The green color-value of the shape
   * @param b    The blue color-value of the shape
   */
  void addKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Removes an individual key frame from the animation. Specifically the key frame at time t of the
   * specified shape.
   *
   * @param name name of the shape who's key frame is to be removed
   * @param t    time of the key frame to be removed.
   */
  void removeKeyFrame(String name, int t);


}
