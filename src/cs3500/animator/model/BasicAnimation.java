package cs3500.animator.model;

import java.awt.*;

public class BasicAnimation implements Animation {

  private int startTick;
  private State startState;
  private int endTick;
  private State endState;

  public BasicAnimation(int startTick, State startState,
                        int endTick, State endState) {
    if (endTick < 0 || startTick < 0 || endTick - startTick <= 0) {
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