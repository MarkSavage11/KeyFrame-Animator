package cs3500.animator.view;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import cs3500.animator.controller.IAnimationController;
import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.ReadOnlyShape;
import cs3500.animator.model.State;
import cs3500.animator.model.StateImpl;
import cs3500.animator.provider.model.IPicture;
import cs3500.animator.provider.model.ImmutableModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IActionListener;
import cs3500.animator.provider.view.IAnimateView;

public class AdaptedAnimationListener implements cs3500.animator.view.IActionListener {
  private IAnimationController controller;
  private IAnimateView editor;
  private ImmutableModel model;


  public AdaptedAnimationListener(IAnimationController controller,
                                  IAnimateView editor, ImmutableModel model){
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
        break;
      case "Add":
        fields = editor.getAddFields("Add");
        String name = fields.get(8);
        int addTick;
        try{
          addTick = Integer.parseInt(fields.get(0));
        }catch(NumberFormatException e){
          editor.showErrorMessage("Please enter a number for Tick");
          break;
        }
        State state = null;
        try {
          state = new StateImpl(
                  new Point(Integer.parseInt(fields.get(1)), Integer.parseInt(fields.get(2))),
                  new Dimension(Integer.parseInt(fields.get(3)), Integer.parseInt(fields.get(4))),
                  new Color(Integer.parseInt(fields.get(5)), Integer.parseInt(fields.get(6)),
                          Integer.parseInt(fields.get(7))));
        }catch(IllegalArgumentException e){
          editor.showErrorMessage("Invalid argument(s)");
          break;
        }
        this.controller.addKeyframe(name, addTick, state);
        break;
      case "Remove":
        fields = editor.getAddFields("Remove");
        if(fields.get(0).equals("")){
          editor.showErrorMessage("Select a shape to remove from");
          break;
        }
        int removeTick;
        try{
          removeTick = Integer.parseInt(fields.get(1));
        }catch(IllegalArgumentException e){
          editor.showErrorMessage("Please enter a number for Tick");
          break;
        }
        this.controller.deleteKeyFrame(fields.get(0), removeTick);
        break;
      case "Save":
        fields = editor.getAddFields("Save");
        if (fields.get(0).equals("")){
          this.editor.showErrorMessage("Cannot save to a file with no name");
          break;
        }
        if (fields.get(0).equals("Text") || fields.get(0).equals("text")) {
          try {
            BufferedWriter file = new BufferedWriter(new FileWriter(fields.get(1)));
            new SVGAnimationView(file, 30);
            file.close();

          }catch(IOException e){
            editor.showErrorMessage(e.getMessage());
          }
        } else if (fields.get(0).equals("SVG") || fields.get(0).equals("svg")) {
          try {
            BufferedWriter file = new BufferedWriter(new FileWriter(fields.get(1)));
            new TextAnimationView(file);
            file.close();
          }catch(IOException e){
            editor.showErrorMessage(e.getMessage());
          }
        } else {
          editor.showErrorMessage("Improper Save type");
        }
        break;
      case "Load":
        AnimationModel tempModel;
        try {
          tempModel = AnimationReader.parseFile(new FileReader(inputFile), //TODO: replace with the value from the load text box
                  new AnimationModelImpl.Builder());
        } catch (IOException e) {
          editor.showErrorMessage("Unable to read from input file");
          break;
        }

        for (IPicture p: model.getPictures()) {
          controller.deleteShape(p.getAlias());
        }

        for (Map.Entry<String, ReadOnlyShape> shape: tempModel.getShapes().entrySet()) {
          controller.addShape(shape.getKey(), shape.getValue().getType());
          for (Map.Entry<Integer,State> keyframe: shape.getValue().getKeyframes().entrySet()) {
            controller.addKeyframe(shape.getKey(), keyframe.getKey(), keyframe.getValue());
          }
        }
        break;
      default:
        break;
    }

  }
}
