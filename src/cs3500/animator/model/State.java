package cs3500.animator.model;

import java.awt.*;

public interface State {

  Point getPosition();

  Dimension getSize();

  Color getColor();
}
