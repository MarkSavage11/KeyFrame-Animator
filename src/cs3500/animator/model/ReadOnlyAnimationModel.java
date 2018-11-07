package cs3500.animator.model;

import java.awt.*;
import java.util.Map;

public interface ReadOnlyAnimationModel {

  Map<String, ReadOnlyShape> getShapes();

  Point canvasPosition();

  Dimension canvasSize();
}
