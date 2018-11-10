package cs3500.animator;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextAnimationView;
import cs3500.animator.view.VisualAnimationView;

public final class Excellence {
  public static void main(String[] args) {
    Map<String, String> flags = new HashMap<>();

    for (int i = 0; i < args.length; i+= 2) {
      if (i + 1 < args.length) {
        flags.put(args[i], args[i+1]);
      } else {
        showError("malformed argument list");
      }
    }

    AnimationModel model = null;

    if (flags.containsKey("-in")) {
      try {
        model = AnimationReader.parseFile(new FileReader(flags.get("-in")), new AnimationModelImpl.Builder());
      } catch (IOException e) {
        showError("Unable to read from input file");
      }
    } else {
      showError("Missing input file");
    }

    Appendable out = System.out;
    if (flags.containsKey("-out")) {
      try {
        out = new FileWriter(flags.get("-out"));
      } catch (IOException e) {
        showError("Error writing to output file");
      }
    }

    int speed = 1;
    if (flags.containsKey("-speed")) {
      try {
        speed = Integer.parseInt(flags.get("-speed"));
      } catch (NumberFormatException e) {
        showError("Speed given is not a number");
      }
    }

    AnimationView view = null;
    if (flags.containsKey("-view")) {
      switch (flags.get("-view")) {
        case "text":
          view = new TextAnimationView(out);
          break;
        case "visual":
          view = new VisualAnimationView(speed);
          break;
        case "svg":
          view = new SVGAnimationView(out, speed);
          break;
        default:
          showError("Unknown view type");
      }
    } else {
      showError("Missing view type");
    }

    try {
      if (view != null && model != null) {
        view.display(model);
      }
    } catch (IllegalStateException e) {
      showError(e.getMessage());
    }
  }

  private static void showError(String error) {

  }
}