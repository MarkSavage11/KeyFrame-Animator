package cs3500.animator.provider.model;

import cs3500.animator.provider.model.Shapes;

/**
 * Interface that represents a State. A State knows its time, dimensions, location, and shape type,
 * and color. A state is the base unit of an animation and represents a shape's "state" at one point
 * in time.
 */
public interface IState {

  /**
   * Gets the width of this state.
   *
   * @return the width
   */
  int getWidth();

  /**
   * Gets the height of this state.
   *
   * @return height
   */
  int getHeight();

  /**
   * Gets the x location of the state.
   *
   * @return x loc
   */
  int getLocX();

  /**
   * Gets the y location of the state.
   *
   * @return the y loc
   */
  int getLocY();

  /**
   * Gets the r color componenet.
   */
  int getR();

  /**
   * Getss the b color component.
   */
  int getB();

  /**
   * Gets the g.
   */
  int getG();

  /**
   * Returns the states shape type value.
   *
   * @return string representing the type
   */
  String getShapeType();

  /**
   * Sets this states shape type if not default.
   *
   * @param s the shape type.
   */
  void setShapeType(Shapes s);
}
