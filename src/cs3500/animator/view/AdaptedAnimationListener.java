package cs3500.animator.view;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.controller.IAnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.State;
import cs3500.animator.model.StateImpl;
import cs3500.animator.provider.view.IActionListener;
import cs3500.animator.provider.view.IAnimateView;

public class AdaptedAnimationListener implements IActionListener {
  private IAnimationController controller;
  private IAnimateView editor;
  private AnimationModel model;


  public AdaptedAnimationListener(IAnimationController controller,
                                  IAnimateView editor, AnimationModel model){
    this.controller = controller;
    this.editor = editor;
    this.model = model;
  }

  @Override
  public void action(String description) {
    ArrayList<String> fields;

    switch(description) {
      case "Play":
        this.editor.play();
        break;
      case "Pause":
        this.editor.pause();
        break;
      case "Restart":
        this.editor.setTime(0);
        break;
      case "Increase Speed":
        this.editor.increaseSpeed();
        break;
      case "Decrease Speed":
        this.editor.decreaseSpeed();
        break;
      case "Looping Toggle":
        this.editor.toggleLooping();
      case "Add":
        fields = editor.getAddFields("Add");
        String name = fields.get(8);

        int tick = Integer.parseInt(fields.get(0)); //TODO: Check our inputs
        State state = new StateImpl(
                new Point(Integer.parseInt(fields.get(1)), Integer.parseInt(fields.get(2))),
                new Dimension(Integer.parseInt(fields.get(3)), Integer.parseInt(fields.get(4))),
                new Color(Integer.parseInt(fields.get(5)), Integer.parseInt(fields.get(6)),
                        Integer.parseInt(fields.get(7))));
        //if() TODO: Check how to add a shape and figure that shit out.
        this.controller.addKeyframe(name, tick, state);
        break;
      case "Remove":
        fields = editor.getAddFields("Remove");
        //TODO: Check input
        this.controller.deleteKeyFrame(fields.get(0), Integer.parseInt(fields.get(1)));
        break;
      case "Save":
        fields = editor.getAddFields("Save");
        if (fields.get(0).equals("Text") || fields.get(0).equals("text")) {
          try {
            new SVGAnimationView(new BufferedWriter(new FileWriter(fields.get(1))), 30); //Can't get access to the speed in the editor
          }catch(IOException e){
            editor.showErrorMessage(e.getMessage());
          }
        } else if (fields.get(0).equals("SVG") || fields.get(0).equals("svg")) {
          try {
            new TextAnimationView(new BufferedWriter(new FileWriter(fields.get(1))));
          }catch(IOException e){
            editor.showErrorMessage(e.getMessage());
          }
        } else {
          editor.showErrorMessage("Improper Save type");
        }
        break;
      default:
        break;
    }

  }
}
