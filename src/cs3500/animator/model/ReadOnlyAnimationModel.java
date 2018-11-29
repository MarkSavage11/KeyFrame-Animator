package cs3500.animator.model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Map;

/**
 * A read only model which stores shapes and animations to be used in a view. It holds a map of read
 * only shapes and their names, as well as any information relating to the canvas.
 */
public interface ReadOnlyAnimationModel {

  /**
   * Gets the shapes in this model.
   *
   * @return the map that stores names and the shape correlated to that name.
   */
  Map<String, ReadOnlyShape> getShapes();

  ReadOnlyShape getShape(String name);

  /**
   * Gets the canvas position.
   *
   * @return the canvas position.
   */
  Point canvasPosition();

  /**
   * Gets the canvas dimensions.
   *
   * @return the size of the canvas.
   */
  Dimension canvasSize();

  /**
   * Gets the earliest tick that any animation in this model starts.
   *
   * @return the earliest tick among all shapes in this model
   */
  int getFirstTick();

  /**
   * Gets the latest tick that any animation in this model ends.
   *
   * @return the latest tick among all shapes in this model
   */
  int getLastTick();
}
