package cs3500.animator.model;

import cs3500.animator.provider.model.IState;

public class IStateAdapter implements IState {

  State adaptee;
  //String shapeType;

  public IStateAdapter(State adaptee){
    this.adaptee = adaptee;
  }


  @Override
  public int getWidth() {
    return adaptee.getSize().width;
  }

  @Override
  public int getHeight() {
    return adaptee.getSize().height;
  }

  @Override
  public int getLocX() {
    return adaptee.getPosition().x;
  }

  @Override
  public int getLocY() {
    return adaptee.getPosition().y;
  }

  @Override
  public int getR() {
    return adaptee.getColor().getRed();
  }

  @Override
  public int getB() {
    return adaptee.getColor().getBlue();
  }

  @Override
  public int getG() {
    return adaptee.getColor().getGreen();
  }

  @Override
  public String getShapeType() {
    return null;
  }

  @Override
  public void setShapeType(Shapes s) {
    
  }
}
