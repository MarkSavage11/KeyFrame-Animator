package cs3500.animator.provider.model;

import cs3500.animator.provider.model.Shapes;

/**
 * Class for determining what kind of shape to construct a picture with. This is a workaround asking
 * directly what kind of shape that a picture represents.
 */
public class ShapesEnumIf {

  /**
   * Method that returns an enum based on a string passed in by the user to build and animation.
   * NOTE: Must be updated if additional values are added to Shapes enum.
   *
   * @param type String passed in.
   * @return Shape enum.
   */
  public Shapes enumIf(String type) {
    switch (type) {
      case "rectangle":
        return Shapes.rectangle;

      case "square":
        return Shapes.square;

      case "ellipse":
        return Shapes.ellipse;

      case "circle":
        return Shapes.circle;

      default:
        throw new IllegalArgumentException("Specified type of Shape not found.");

    }


  }

}
