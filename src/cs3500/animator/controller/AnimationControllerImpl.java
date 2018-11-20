package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.State;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.EditableAnimationView;

public class AnimationControllerImpl implements IAnimationController {
  private AnimationModel model;
  private AnimationView view;


  public AnimationControllerImpl(AnimationModel model){
    this.model = model;
  }

  @Override
  public void deleteShape(String name) {
    model.deleteShape(name);
  }

  @Override
  public void addShape(String name, ShapeType type) {
    model.addShape(name, type);
  }

  @Override
  public void addKeyframe(String shapeName, int tick, State state) {
   //Needs public facing ability to add Keyframe
  }

  @Override
  public void editKeyFrame(String shapeName, int tick, State state) {
    //Needs public facing ability to add Keyframe
  }

  @Override
  public void deleteKeyFrame(String shapeName, int tick) {

  }
}
