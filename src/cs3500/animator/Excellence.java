package cs3500.animator;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import cs3500.animator.controller.AnimationControllerImpl;
import cs3500.animator.controller.IAnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.EditableAnimationView;
import cs3500.animator.view.ProviderAdapterView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextAnimationView;
import cs3500.animator.view.VisualAnimationView;

public final class Excellence {
  /**
   * It's the main method. Uses the specification of command line argument syntax from the
   * assignment.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Map<String, String> flags = new HashMap<>();

    for (int i = 0; i < args.length; i += 2) {
      if (i + 1 < args.length) {
        flags.put(args[i], args[i + 1]);
      } else {
        showError("malformed argument list");
      }
    }

    AnimationModel model = null;

    if (flags.containsKey("-in")) {
      try {
        model = AnimationReader.parseFile(new FileReader(flags.get("-in")),
                new AnimationModelImpl.Builder());
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
    IAnimationController controller = new AnimationControllerImpl(model);

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
        case "edit":
          view = new EditableAnimationView(controller, speed);
          break;
        case "provider":
          view = new ProviderAdapterView(controller, speed);
          break;
        default:
          showError("Unknown view type");
      }
    } else {
      showError("Missing view type");
    }

    try {
      if (view != null && model != null) {
        controller.run(view);
        if (out instanceof FileWriter) {
          try {
            ((FileWriter) out).close();
          } catch (IOException e) {
            showError("cannot close file");
          }
        }
      }
    } catch (IllegalStateException e) {
      showError(e.getMessage());
    }
  }

  private static void showError(String error) {
    JOptionPane.showMessageDialog(new JFrame(), error, "Error", JOptionPane.ERROR_MESSAGE);
    System.exit(-1);
  }
}