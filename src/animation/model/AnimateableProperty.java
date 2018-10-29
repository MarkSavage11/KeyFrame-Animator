package animation.model;

import java.util.List;
import java.util.function.Predicate;

/**
 * An enum representing the possible properties of a shape that can be animated over. The length and
 * test properties describe the attributes of a list that can describe a state of the associated
 * property - details of each are given below. <ul> <li>position: an x, y position. 2 integers of
 * any value.</li> <li>size: a width and height. 2 non-negative integers.</li> <li>color: r,g,b
 * components of a color. 3 integers between 0 and 255, inclusive.</li> </ul>
 */
public enum AnimateableProperty {
  POSITION(2, (list) -> true),
  SIZE(2, (list) -> list.stream().allMatch((i) -> i > 0)),
  COLOR(3, (list) -> list.stream().allMatch((i) -> i >= 0 && i <= 255));

  private Predicate<List<Integer>> test;
  private int length;

  AnimateableProperty(int length, Predicate<List<Integer>> test) {
    this.length = length;
    this.test = test;
  }

  public boolean validState(List<Integer> state) {
    return state.size() == this.length && test.test(state);
  }

  public int getLength() {
    return this.length;
  }
}
