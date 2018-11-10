package cs3500.animator.view;

import java.io.IOException;
import java.util.Map;

import cs3500.animator.model.Animation;
import cs3500.animator.model.ReadOnlyAnimationModel;
import cs3500.animator.model.ReadOnlyShape;
import cs3500.animator.model.State;

/**
 * A view that appends the given model information into an SVG format.
 */
public class SVGAnimationView implements AnimationView{

  private Appendable ap;
  private final int speed;

  /**
   * Constructs an SVGAnimationView with a given appendable to write to, and a given framerate.
   *
   * @param ap an appendable to write the SVG formatting to
   * @param speed the framerate of the animation
   */
  public SVGAnimationView(Appendable ap, int speed){
    this.ap = ap;
    this.speed = speed;
  }


  /**
   * Writes the given model information into the view's appendable.
   *
   * @param model the animation model to display.
   * @throws IllegalStateException if the appendable cannot be written to for any reason.
   */
  public void display(ReadOnlyAnimationModel model) throws IllegalStateException{
    try {
      ap.append("<svg width=\"" + model.canvasSize().width + "\" height=\""
              + model.canvasSize().height + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");

      for (Map.Entry<String, ReadOnlyShape> shape : model.getShapes().entrySet()) {
        String shapeTag;
        String xAttName;
        String yAttName;
        String wAttName;
        String hAttName;
        State firstState = shape.getValue().getAnimations().get(0).startState();

        //TODO Command Pattern for abstraction for different shape types
        switch(shape.getValue().getType()){
          case RECTANGLE:
            shapeTag = "rect";
            xAttName = "x";
            yAttName = "y";
            wAttName = "width";
            hAttName = "height";
            break;
          case ELLIPSE:
            shapeTag = "ellipse";
            xAttName = "cx";
            yAttName = "cy";
            wAttName = "rx";
            hAttName = "ry";
            break;
          default:
            throw new IllegalArgumentException("Incompatible shape type");
        }

        ap.append(String.format("<%s id=\"%s\" %s=\"%d\" %s=\"%d\" %s=\"%d\" %s=\"%d\" fill=\"rgb(%d,%d,%d)\">\n", shapeTag,
                shape.getKey(), xAttName, firstState.getPosition().x, yAttName, firstState.getPosition().y,
                wAttName, firstState.getSize().width, hAttName, firstState.getSize().height,
                firstState.getColor().getRed(), firstState.getColor().getBlue(), firstState.getColor().getGreen()));

        for (Animation anim : shape.getValue().getAnimations()) {
          ap.append(writeAnimation(anim, xAttName, yAttName, wAttName, hAttName));
        }

        ap.append("</" + shapeTag + ">\n");

      }

      ap.append("</svg>");

    } catch (IOException e) {
      throw new IllegalStateException("Cannot write to output.");
    }
  }

  private String writeAnimation(Animation anim, String xAttName, String yAttName, String wAttName,
                               String hAttName){
    int delay = 1000/this.speed;
    StringBuilder sb = new StringBuilder();
    if(anim.startState().getPosition()!= anim.endState().getPosition()){
      if(anim.startState().getPosition().x != anim.endState().getPosition().x){
        sb.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\" " +
                "dur=\"%dms\" attributeName=\"%s\" from=\"%d\" to=\"%d\"/>\n",
                delay*anim.startTick(), delay * (anim.endTick()-anim.startTick()),
                xAttName, anim.startState().getPosition().x, anim.endState().getPosition().x));
      }
      if(anim.startState().getPosition().y != anim.endState().getPosition().y){
        sb.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\" " +
                        "dur=\"%dms\" attributeName=\"%s\" from=\"%d\" to=\"%d\"/>\n",
                delay*anim.startTick(), delay * (anim.endTick()-anim.startTick()),
                yAttName, anim.startState().getPosition().y, anim.endState().getPosition().y));

      }
    }
    if(anim.startState().getSize()!= anim.endState().getSize()){
      if(anim.startState().getSize().width != anim.endState().getSize().width){
        sb.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\" " +
                        "dur=\"%dms\" attributeName=\"%s\" from=\"%d\" to=\"%d\"/>\n",
                delay*anim.startTick(), delay * (anim.endTick()-anim.startTick()),
                wAttName, anim.startState().getSize().width, anim.endState().getSize().width));
      }
      if(anim.startState().getSize().height != anim.endState().getSize().height){
        sb.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\" " +
                        "dur=\"%dms\" attributeName=\"%s\" from=\"%d\" to=\"%d\"/>\n",
                delay*anim.startTick(), delay * (anim.endTick()-anim.startTick()),
                hAttName, anim.startState().getSize().height, anim.endState().getSize().height));
      }
    }
    if(anim.startState().getColor().getRGB()!= anim.endState().getColor().getRGB()){
      sb.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\" " +
                      "dur=\"%dms\" attributeName=\"fill\" " +
                      "from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\"/>\n",
              delay*anim.startTick(), delay * (anim.endTick()-anim.startTick()),
              anim.startState().getColor().getRed(), anim.startState().getColor().getGreen(),
              anim.startState().getColor().getBlue(), anim.endState().getColor().getRed(),
              anim.endState().getColor().getGreen(), anim.endState().getColor().getBlue()));
    }
    return sb.toString();
  }


}
