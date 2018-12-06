package cs3500.animator.provider.view;

import cs3500.animator.model.State;
import cs3500.animator.provider.model.IState;
import cs3500.animator.view.IActionListener;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Interface for views that can animate. This view extends the regular IView to inherit the
 * displayAnimation methods.
 */
public interface IAnimateView {

  /**
   * Make the view visible.
   */
  void makeVisible();

  /**
   * Refresh the view.
   */
  void refresh();

  /**
   * Updates the pictures that need to be drawn by the panel.
   *
   * @param statesupdate the list of updated pictures
   */
  void update(ArrayList<IState> statesupdate);

  /**
   * Diplays a View for an animated version. There is no need for a string input or appendable.
   */
  void displayAnimationVisual();

  /**
   * Adds a panel to the Animated view.
   * @param p The panel to be added.
   */
  void addDrawingComp(JPanel p);

  /**
   * Throws an error if we are not able to render the image.
   *
   * @param error The error message.
   */
  void showErrorMessage(String error);

  /**
   * Edits the speed of this animation, if the user wants the animation to play faster or slower.
   *
   * @param speed The speed the user want to change To.
   */
  void editSpeed(int speed);

  /**
   * Allows for the time in the view to be changed. The current time of the view will be set to the
   * inputted time. This can be used to jump around the animation or restart the animation as called
   * for.
   *
   * @param time the new time for the view to jump to in the animation.
   */
  void setTime(int time);


  /**
   * Sets editor view to play/pause according to the inputted value. A value of true means the
   * animation is playing and time is passing. A value of false means the animation is paused and
   * time is fixed until playing=true. New for Assignment 7 in order to control play/pause
   * functionality.
   *
   * @param newValue new value for playing
   */
  void setPlaying(boolean newValue);

  /**
   * Increases the speed of the view by value of 5 ticks per second. Implemented in Assignment 7 for
   * the increaseSpeed button functionality.
   */
  void increaseSpeed();

  /**
   * Decreases the speed of the view by value of 5 ticks per second. Implemented in Assignment 7 for
   * the decreaseSpeed button functionality.
   */
  void decreaseSpeed();


  /**
   * Sets the listener for the view. The listener will be able to take the
   * action command and perform the nessecary action.
   * @param listener the listener for the commands of the view
   * @param keys the key listener for the keystroke commands of the view
   */
  void setListener(IActionListener listener, KeyListener keys);

  /**
   * Gets the current time of the view.
   *
   * @return the current time of the animation
   */


  int getTime();

  /**
   * Pauses the view animation. Freezes the timer until play is called.
   */
  void pause();

  /**
   * Plays the animation, so the timer will increment as normal.
   */
  void play();


  /**
   * Switches the animation from looping to not looping. Will be set to the opposite of what it was
   * before.
   */
  void toggleLooping();

  /**
   * Gets the fields to add a KeyFrame. The list of fields are used in the add
   * Key Frame method.
   *
   * @param s the string of action
   * @return an array list of the arguments the user passes in.
   */
  ArrayList<String> getAddFields(String s);
}
