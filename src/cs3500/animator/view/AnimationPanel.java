package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlyShape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.State;

public class AnimationPanel extends JPanel {

  ReadOnlyAnimationModel model;
  int tick = 0;

  /**
   * Constructs an AnimationPanel that displays the models animations.
   *
   * @param model the model to animate
   * @param speed the framerate of the animation
   */
  public AnimationPanel(ReadOnlyAnimationModel model, int speed){
    this.model = model;

    Timer timer = new Timer(1000/speed, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tick++;
        repaint();
      }
    });
    timer.start();
  }

  @Override
  public Dimension getPreferredSize() {
    return model.canvasSize();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();

    for(Map.Entry<String, ReadOnlyShape> shapeEntry : model.getShapes().entrySet()){
      ReadOnlyShape shape = shapeEntry.getValue();
      State state = shape.getStateAt(tick);
      
      g2d.setColor(state.getColor());
      //TODO make this a command pattern so that we can abstract it more. but for right now....
      switch(shape.getType()){
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
