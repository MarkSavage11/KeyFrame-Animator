package cs3500.animator.provider.view;

import cs3500.animator.provider.model.IPicture;
import cs3500.animator.provider.model.IState;
import cs3500.animator.provider.model.ImmutableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.Timer;

/**
 * Class for an animated view. The view takes in an immutable model to process data on what shapes
 * and motions should be drawn.
 */
public class VisualAnimationView extends JFrame implements IAnimateView, ActionListener {


  private AnimationPanel panel;
  private final int width = 3000;
  private final int height = 3000;
  private ImmutableModel model;
  private Timer timer;
  private int currentTime;
  private int speed;
  private boolean looping;
  private int lastTime;


  /**
   * Constructor for a animated view. The view takes in the immutable model, and a speed. All other
   * fields are initialized in the constructor with defaults.
   */
  public VisualAnimationView(ImmutableModel model, int speed) {
    this.setTitle("Animation!");
    this.setSize(width, height); //change to canvas size

    this.currentTime = 1;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //the immutable model
    this.model = model;

    this.looping = false;

    //border layout with a panel
    this.setLayout(new BorderLayout());
    this.panel = new AnimationPanel(new ArrayList<IState>());
    this.panel.setPreferredSize(new Dimension(750, 750));
    this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    this.add(panel);

    //alias list

    JPanel aliaslist = new JPanel();
    aliaslist.setLayout(new OverlayLayout(aliaslist));
    aliaslist.setLocation(0, 500);
    aliaslist.setPreferredSize(new Dimension(25, 75));
    JTextArea aliases = new JTextArea(this.listOfAlias());
    JScrollPane scrollPane = new JScrollPane(aliases);
    scrollPane.setBackground(Color.PINK);
    aliaslist.add(scrollPane);
    this.add(aliaslist);

    //scrollbar
    JScrollPane pane = new JScrollPane(panel);
    this.add(pane);

    //time and the ticks
    this.speed = speed;
    this.timer = new Timer(1000 / this.speed, this);

    this.lastTime = this.model.lastTime();

    this.pack();

  }

  /**
   * Returns a string with all the possible alias names the user can choose from. This will be
   * displayed along the top of the screen.
   *
   * @return String of aliases to choose from.
   */
  private String listOfAlias() {

    String s = "Available Pictures: \n";

    for (IPicture p : model.getPictures()) {
      s = s + p.getAlias() + "\n";
    }

    return s;

  }


  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void update(ArrayList<IState> statesupdate) {

    this.panel.updateStates(statesupdate);
  }

  @Override
  public void displayAnimationVisual() {

    this.timer.start();
    //end this method with make visible call
    this.repaint();
    this.makeVisible();
  }


  @Override
  public void addDrawingComp(JPanel p) {

    this.add(p);
  }

  //this should get fired on every tick. The list of shapes to be drawn updates.
  @Override
  public void actionPerformed(ActionEvent e) {

    if (looping) {
      if (this.currentTime > this.lastTime) {
        this.setTime(1);
        currentTime = 1;
      }
    }

    this.update(model.getStatesAtTime(currentTime)); // we only want the ones at the specific time

    currentTime++;

    this.refresh(); //reset the the list of pictures to be painted and then refresh the view

  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);

  }

  @Override
  public void editSpeed(int newSpeed) {

    this.speed = newSpeed;
    this.timer.setDelay(1000 / this.speed);
  }

  @Override
  public void setTime(int time) {
    this.currentTime = time;
  }

  @Override
  public void setPlaying(boolean newValue) {
    if (newValue && !this.timer.isRunning()) {
      this.timer.start();
    } else if (!newValue) {
      this.timer.stop();
    }
  }

  @Override
  public void increaseSpeed() {
    this.speed += 5;
    this.timer.setDelay(1000 / this.speed);
  }

  @Override
  public void decreaseSpeed() {
    this.speed -= 5;
    this.timer.setDelay(1000 / this.speed);
  }

  @Override
  public void setListener(IActionListener listener, KeyListener keys) {
    //This is blank because there are no buttons to receive listeners in this view.
  }

  @Override
  public int getTime() {
    return this.currentTime;
  }

  @Override
  public void pause() {
    timer.stop();
  }

  @Override
  public void play() {
    timer.setDelay(currentTime);
    timer.start();

  }

  @Override
  public void toggleLooping() {
    if (this.looping) {
      this.looping = false;
    } else {
      this.looping = true;
    }
  }

  @Override
  public ArrayList<String> getAddFields(String s) {
    return new ArrayList<>();
  }

}