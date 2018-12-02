package cs3500.animator.model;
import java.util.ArrayList;

import cs3500.animator.provider.model.IMotion;
import cs3500.animator.provider.model.IPicture;
import cs3500.animator.provider.model.IState;

public class IPictureAdapter implements IPicture {
  String name;

  IPictureAdapter (String name) {
    this.name = name;
  }


  @Override
  public String asString() {
    return null;
  }

  @Override
  public void addMotion(IMotion m) throws IllegalArgumentException {

  }

  @Override
  public ArrayList<IMotion> getMotionsAtTime(int time) {
    return null;
  }

  @Override
  public boolean isThisPicture(String s) {
    return false;
  }

  @Override
  public String shapeType() {
    return null;
  }

  @Override
  public int getR() {
    return 0;
  }

  @Override
  public int getG() {
    return 0;
  }

  @Override
  public int getB() {
    return 0;
  }

  @Override
  public int getx() {
    return 0;
  }

  @Override
  public int gety() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public void removeLastMotion() {

  }

  @Override
  public ArrayList<IState> getStates() {
    return null;
  }

  @Override
  public String svgRender(int xOffset, int yOffset, int speed) {
    return null;
  }

  @Override
  public IMotion motionAtTime(int time) {
    return null;
  }

  @Override
  public IMotion getLastMotion() {
    return null;
  }

  @Override
  public void replaceMotion(ArrayList<IMotion> toRemove, ArrayList<IMotion> toAdd) {

  }

  @Override
  public String getAlias() {
    return name;
  }

  @Override
  public ArrayList<IMotion> motionsOfKeyFrame(int time) {
    return null;
  }

  @Override
  public ArrayList<IMotion> copyMotions() {
    return null;
  }
}
