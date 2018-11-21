package cs3500.animator.model;

import java.awt.*;

/**
 * An animation that would be stored in a shape in an animation model. This type of animation holds
 * the starting and ending times and states, holding all information relating to position size and
 * color.
 */
public class BasicAnimation implements Animation {

  private int startTick;
  private State startState;
  private int endTick;
  private State endState;

  /**
   * Constructs a basic animation with a given start tick, start state, end tick, and end state.
   *
   * @param startTick  the tick this animation starts on
   * @param startState the state this animation begins with
   * @param endTick    the tick this animation ends on
   * @param endState   the state this animation ends with
   * @throws IllegalArgumentException if the given tick values are negative or where the end tick is
   *                                  before the start tick
   */
  public BasicAnimation(int startTick, State startState,
                        int endTick, State endState) throws IllegalArgumentException {
    if (endTick - startTick < 0) {
      throw new IllegalArgumentException("Illegal start or end ticks");
    }
    this.startTick = startTick;
    this.startState = startState;
    this.endTick = endTick;
    this.endState = endState;
  }

  @Override
  public int startTick() {
    return startTick;
  }

  @Override
  public int endTick() {
    return endTick;
  }

  @Override
  public State startState() {
    return this.startState;
  }

  @Override
  public State endState() {
    return this.endState;
  }

  @Override
  public State getStateAt(int tick) throws IllegalArgumentException {
    if (tick > startTick && tick < endTick) {
      return new StateImpl(
              new Point(
                      interpolate(startState.getPosition().x, endState.getPosition().x, tick),
                      interpolate(startState.getPosition().y, endState.getPosition().y, tick)),
              new Dimension(
                      interpolate(startState.getSize().width, endState.getSize().width, tick),
                      interpolate(startState.getSize().height, endState.getSize().height, tick)),
              new Color(
                      interpolate(startState.getColor().getRed(),
                              endState.getColor().getRed(), tick),
                      interpolate(startState.getColor().getGreen(),
                              endState.getColor().getGreen(), tick),
                      interpolate(startState.getColor().getBlue(),
                              endState.getColor().getBlue(), tick))
      );
    } else {
      throw new IllegalArgumentException("Tick out of range.");
    }
  }

  private int interpolate(int start, int end, int tick) {
    return start + (end - start) / (endTick - startTick) * (tick - startTick);
  }
}