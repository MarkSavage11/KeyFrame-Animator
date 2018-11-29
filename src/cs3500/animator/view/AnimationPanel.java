package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;

import javax.swing.JPanel;

import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlyShape;
import cs3500.animator.model.State;

/**
 * A JPanel that displays an animation of the given Animation model at the given speed.
 */
public class AnimationPanel extends JPanel {

  ReadOnlyAnimationModel model;
  int tick = 1;
  boolean isLooping = false;

  /**
   * Constructs an AnimationPanel that displays the models animations.
   *
   * @param model the model to animate
   */
  public AnimationPanel(ReadOnlyAnimationModel model) {
    this.model = model;
  }

  @Override
  public Dimension getPreferredSize() {
    return model.canvasSize();
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();

    for (Map.Entry<String, ReadOnlyShape> shapeEntry : model.getShapes().entrySet()) {
      ReadOnlyShape shape = shapeEntry.getValue();
      State state;
      try {
        state = shape.getStateAt(tick);
      } catch (IllegalArgumentException e) {
        state = null;
      }
      //TODO make this a command pattern so that we can abstract it more. but for right now....
      if (state != null) {
        g2d.setColor(state.getColor());
        switch (shape.getType()) {
          case ELLIPSE:
            g2d.fillOval(state.getPosition().x, state.getPosition().y,
                    state.getSize().width, state.getSize().height);
            break;
          case RECTANGLE:
            g2d.fillRect(state.getPosition().x, state.getPosition().y,
                    state.getSize().width, state.getSize().height);
            break;
          default:
            break;
        }
      }
    }

  }

  public void setTick(int tick) {
    this.tick = tick;
  }

  public int getTick() {
    return this.tick;
  }

}
