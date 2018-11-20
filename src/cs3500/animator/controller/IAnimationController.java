package cs3500.animator.controller;

import cs3500.animator.model.ShapeType;
import cs3500.animator.model.State;

public interface IAnimationController {

  void deleteShape(String name);

  void addShape(String name, ShapeType type);

  void addKeyframe(int tick, State state);

  void editKeyFrame(int tick, State state);





}
