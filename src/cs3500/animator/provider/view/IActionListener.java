package cs3500.animator.provider.view;

/**
 * Interface for action listener. This interface is implemented by the cs3500.animator.controller,
 * and is used to delegate actions and events to and from the view.
 */
public interface IActionListener {

  /**
   * Delegates action commands to the correct action to be reflected in the model and view. This
   * method is implemented in the contoller and the description is the action command that is
   * assocaited with each key or button that the user presses.
   *
   * @param description the description of the action to be performed.
   */
  void action(String description);

}
