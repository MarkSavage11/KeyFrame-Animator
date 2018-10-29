package animation.model;

import java.util.List;

/**
 * An animation, which has a start and end time, and may define a change (or lack thereof) to one or
 * more of position, size, and color.
 */
public interface Animation {
  /**
   * Gets the tick on which this animation begins.
   *
   * @return the tick this animation begins on
   */
  int startTick();

  /**
   * Gets the tick on which this animation ends.
   *
   * @return the tick this animation ends on
   */
  int endTick();

  /**
   * Adds the given animation (defined as its start and end states) over the given property to the
   * set stored by this animation.
   *
   * @param prop  the property the animation acts over
   * @param start the starting state of the property
   * @param end   the ending state of the property
   * @throws IllegalArgumentException if either start or end are invalid states of the property, or
   *                                  if a definition for the given property already exists.
   */
  void addAnimation(AnimateableProperty prop, List<Integer> start, List<Integer> end)
          throws IllegalArgumentException;

  /**
   * Returns whether the animation acts over the given property.
   *
   * @param prop the property to test
   * @return true if the animation has the given property, false otherwise
   */
  boolean hasProperty(AnimateableProperty prop);

  /**
   * Gets the state of the property at the start of the animation.
   *
   * @param prop the property to return the state of
   * @return the state of the property
   */
  List<Integer> getPropertyAtStart(AnimateableProperty prop) throws IllegalArgumentException;

  /**
   * Gets the state of the property at the end of the animation.
   *
   * @param prop the property to return the state of
   * @return the state of the property
   */
  List<Integer> getPropertyAtEnd(AnimateableProperty prop) throws IllegalArgumentException;

  /**
   * Copies the animation so it can be given to the view safely
   *
   * @return a copy of the animation
   */
  Animation copy();

  /**
   * Determines whether the animation conflicts with another animation. Will return false if the
   * animation tries to modify position, color, or size at the same time as this, or if such an
   * animation overlaps at the edge (by exactly one tick) but defines a different property for that
   * tick.
   *
   * @param other the other Animation to compare to
   * @return true if the animations conflict, false otherwise
   */
  boolean conflictsWith(Animation other);
}
