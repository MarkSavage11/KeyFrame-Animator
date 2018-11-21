package cs3500.animator.controller;

import javax.swing.*;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.State;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.EditableAnimationView;

public class AnimationControllerImpl implements IAnimationController {
  private AnimationModel model;

  public AnimationControllerImpl(AnimationModel model) {
    this.model = model;
  }

  @Override
  public void deleteShape(String name) {
    runOrError(() -> model.deleteShape(name));
  }

  @Override
  public void addShape(String name, ShapeType type) {
    runOrError(() -> model.addShape(name, type));
  }

  @Override
  public void addKeyframe(String shapeName, int tick, State state) {
    runOrError(() -> model.insertKeyframe(shapeName, tick, state));
  }

  @Override
  public void editKeyFrame(String shapeName, int tick, State state) {
    runOrError(() -> model.insertKeyframe(shapeName, tick, state));
  }

  @Override
  public void deleteKeyFrame(String shapeName, int tick) {
    runOrError(() -> model.deleteKeyframe(shapeName, tick));
  }

  @Override
  public void run(AnimationView view) {
    view.display(model);
  }

  private void runOrError(Runnable r) {
    try {
      r.run();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(new JFrame(), e.getMessage(),
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
