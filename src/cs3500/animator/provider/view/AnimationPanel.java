package cs3500.animator.provider.view;

import cs3500.animator.provider.model.State;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Class for an Animated panel. This will be the space that the animation shapes are drawn.
 */
public class AnimationPanel extends JPanel {

  private ArrayList<State> states;


  /**
   * Public constructor for a Panel. A panel takes in a list of states to be drawn and gets updated
   * every tick. The states to be drawn will be updated and passed from the view.
   *
   * @param states THe list of pictures that need to be drawn in this animation
   */
  public AnimationPanel(ArrayList<State> states) {
    super();
    this.states = states;
    this.setBackground(Color.WHITE);

  }

  /**
   * Paints the panel component with the shapes for the animation. The panel will be drawn
   * differently based on the kind of shape and will be redrawn every tick with updated state list.
   *
   * @param g A graphic to create the specified shape
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    //paint shapes with background color showing as well
    for (State state : states) {
      //set the color for all of them

      g2d.setColor(new Color(state.getR(), state.getG(), state.getB()));


      //set the actual shape
      switch (state.getShapeType()) {
        //rectangle can serve as a square if user wants
        case "rectangle":

          g2d.fillRect(state.getLocX(), state.getLocY(), state.getWidth(), state.getHeight());
          break;

        case "ellipse":
          //NOTE: oval is the closest thing we could get to ellipse
          g2d.fillOval(state.getLocX(), state.getLocY(), state.getWidth(), state.getHeight());
          break;

        default:
          throw new IllegalArgumentException("this is not a valid shape.");

      }
    }


  }


  /**
   * Updates the list of pictures that need to be drawn each tick.
   *
   * @param updated List of updated pictures to be drawn
   */
  public void updateStates(ArrayList<State> updated) {
    this.states = updated;

  }
}
