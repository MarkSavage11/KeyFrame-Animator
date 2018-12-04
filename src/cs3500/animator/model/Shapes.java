package cs3500.animator.model;

/**
 * Represents all the possible shapes of an animation. Can add new shapes, or other objects of
 * animations, later. Does not have to be a literal shape, can be a picture, but using shapes for
 * now.
 */
public enum Shapes {

  rectangle("rectangle"), circle("circle"), square("square"), ellipse("ellipse");

  private final String value;

  /**
   * Constructor for Shape.
   *
   * @param value the string representation of the shape.
   */
  Shapes(String value) {
    this.value = value;
  }

  /**
   * Returns the value of this Shape.
   *
   * @return the value of this Shape.
   */
  public String getValue() {
    return this.value;
  }

}
