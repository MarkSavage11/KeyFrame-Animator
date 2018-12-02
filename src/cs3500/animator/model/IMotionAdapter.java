package cs3500.animator.model;

import java.util.ArrayList;

import cs3500.animator.provider.model.IMotion;
import cs3500.animator.provider.model.IState;
import cs3500.animator.model.IStateAdapter;

public class IMotionAdapter implements IMotion {
  BasicAnimation adaptee;

  public IMotionAdapter(BasicAnimation animation) {
    adaptee = animation;
  }


  @Override
  public String asString() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getStartTime() {
    return adaptee.startTick();
  }

  @Override
  public int getEndTime() {
    return adaptee.endTick();
  }

  @Override
  public IState getStartState() {
    return new IStateAdapter(adaptee.startState());
  }

  @Override
  public IState getEndState() {
    return new IStateAdapter(adaptee.endState());
  }

  @Override
  public ArrayList<IState> interpolateShapes() {
    ArrayList<IState> states = new ArrayList<>();
    for (int i = this.getStartTime(); i<= this.getEndTime(); i++) {
      states.add(new IStateAdapter(adaptee.getStateAt(i)));
    }

    return states;
  }

  @Override
  public String svgRender(int xOffset, int yOffset, int speed) {
    throw new UnsupportedOperationException();
  }
}
