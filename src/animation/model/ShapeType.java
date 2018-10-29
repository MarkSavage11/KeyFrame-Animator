package animation.model;

/**
 * An enum of the types of shapes that can be represented.
 */
public enum ShapeType {
  RECTANGLE("rectangle"), ELLIPSE("ellipse");

  String type;

  ShapeType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
