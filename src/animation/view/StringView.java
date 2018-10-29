package animation.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import animation.model.AnimateableProperty;
import animation.model.Animation;
import animation.model.Shape;

/**
 * A view which can take a Model state and return a string representation.
 */
public class StringView {

  /**
   * Takes a model's state and returns a string representation of it.
   *
   * @param animationState a Model state to display
   * @return a string describing the model
   */
  public String display(Map<String, Shape> animationState) {

    List<String> shapeStrs = new ArrayList<>();

    for (String s : animationState.keySet()) {

      List<String> animationStrs = new ArrayList<>();

      for (Animation anim : animationState.get(s).getAnimations()) {

        List<String> animStart = new ArrayList<>();
        animStart.add("" + anim.startTick());
        for (AnimateableProperty prop : AnimateableProperty.values()) {
          animStart.addAll(getAsStrings(anim, prop, anim::getPropertyAtStart));
        }

        List<String> animEnd = new ArrayList<>();
        animEnd.add("" + anim.endTick());
        for (AnimateableProperty prop : AnimateableProperty.values()) {
          animEnd.addAll(getAsStrings(anim, prop, anim::getPropertyAtEnd));
        }

        animationStrs.add(String.join(" ", animStart) + "    " +
                String.join(" ", animEnd));
      }

      animationStrs.replaceAll((str) -> "motion " + s + " " + str);
      shapeStrs.add("shape " + s + animationState.get(s).getType().toString() + "\n" +
              String.join("\n", animationStrs));
    }
    return String.join("\n\n", shapeStrs);
  }

  private List<String> getAsStrings(Animation anim, AnimateableProperty prop,
                                    Function<AnimateableProperty, List<Integer>> getProp) {
    if (anim.hasProperty(prop)) {
      return getProp.apply(prop).stream().map((i) -> "" + i).collect(Collectors.toList());
    } else {
      return new ArrayList<Integer>(prop.getLength()).stream()
              .map((i) -> "-").collect(Collectors.toList());
    }
  }
}
