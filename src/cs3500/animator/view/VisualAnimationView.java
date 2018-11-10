package cs3500.animator.view;

import javax.swing.*;

import cs3500.animator.model.ReadOnlyAnimationModel;

/**
 * An animation view that displays a full visual animation of the given model.
 */
public class VisualAnimationView extends JFrame implements AnimationView{

  private ReadOnlyAnimationModel model;
  private JPanel panel;
  private JScrollPane scrollPane;

  private final int speed; //Speed in ticks per second

  /**
   * Constructs a visual animation view that shows an animation of the given model.
   */
  public VisualAnimationView(int speed){
    super();
    this.speed = speed;
  }

  /**
   * Displays the given model's animation in a fully animated view.
   *
   * @param model the animation model to display.
   * @throws IllegalStateException this method does not throw this exception.
   */
  public void display(ReadOnlyAnimationModel model) throws IllegalStateException{
    this.setTitle("Excellent");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new AnimationPanel(model, speed);
    scrollPane = new JScrollPane(panel);
    this.add(scrollPane);
    this.setVisible(true);

  }
}
