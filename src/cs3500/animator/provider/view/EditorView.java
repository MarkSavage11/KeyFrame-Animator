package cs3500.animator.provider.view;

import cs3500.animator.model.State;

import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


/**
 * Class for the Editor View. An editor view will CONTAIN a visual view inside of it, there for
 * being able to perform the same functions as an Animated view but will have the extra
 * functionality of being able to loop, pause, resume, start, and change the speed of the animation
 * while it is running. The user specifies that they want this kind of view with the input word
 * "edit".
 */
public class EditorView extends JFrame implements IAnimateView {

  private IAnimateView animateView;
  private boolean looping; //indicates if animation should start over again when it ends.
  private boolean playing;
  private JButton playButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;
  private JButton loopingToggleButton;
  private JButton addKeyFrameButton;
  private JButton removeKeyFrameButton;
  private JButton saveAnimationButton;
  private JButton loadAnimationButton;
  //Textfields
  private TextField addTtext;
  private TextField addxtext;
  private TextField addytext;
  private TextField addwtext;
  private TextField addhtext;
  private TextField addrtext;
  private TextField addgtext;
  private TextField addbtext;
  private TextField aliastext;
  //Panels - one wrapper panel, one animation panel, one menu panel, add keyframe panel,
  //delete key frame panel, and alias list panel
  private JPanel mainPanel;
  private JPanel menuPanel;
  private JPanel addKeyFramePanel;
  private JPanel removeKeyFramePanel;
  private JPanel savePanel;
  //Labels
  private JLabel addT;
  private JLabel addx;
  private JLabel addy;
  private JLabel addw;
  private JLabel addh;
  private JLabel addr;
  private JLabel addg;
  private JLabel addb;
  private JLabel alias;
  private JLabel timetoRemove;
  private JLabel aliasToRemove;
  private JLabel textOrSVG;
  private JLabel fileName;
  private JLabel loadAnimation;
  //TextFields
  private TextField removeText;
  private TextField aliasRemoveText;
  private TextField textOrSVGtext;
  private TextField fileNametext;
  private TextField loanAnimationtext;

  //layouts
  private GridLayout addKeyFrameLayout;

  /**
   * Public constructor for an Editor View. For all public methods required by the IAnimate View
   * class the Editor View can rely on the animate view it takes in.
   *
   * @param animateView the visual view that this view take in.
   */
  public EditorView(IAnimateView animateView) {
    this.animateView = animateView;
    this.playing = false;

    this.initializeButtons();
    this.initializePanelsAndLayout();
    this.initializeTextBoxes();
    this.addComponents();

    animateView.addDrawingComp(mainPanel);
    animateView.addDrawingComp(addKeyFramePanel);
    animateView.addDrawingComp(removeKeyFramePanel);
    animateView.addDrawingComp(savePanel);

    this.pack();

  }

  @Override
  public ArrayList<String> getAddFields(String s) {
    ArrayList<String> fields = new ArrayList<>();

    switch (s) {

      case "Add":
        fields.add(addTtext.getText());
        fields.add(addxtext.getText());
        fields.add(addytext.getText());
        fields.add(addwtext.getText());
        fields.add(addhtext.getText());
        fields.add(addrtext.getText());
        fields.add(addgtext.getText());
        fields.add(addbtext.getText());
        fields.add(aliastext.getText());

        break;

      case "Remove":
        fields.add(aliasRemoveText.getText());
        fields.add(removeText.getText());

        break;

      case "Save":
        fields.add(textOrSVGtext.getText());
        fields.add(fileNametext.getText());
        break;
      case "Load":
        fields.add(loanAnimationtext.getText());
        break;
      default:
        return fields;
    }
    return fields;


  }


  /**
   * Sets the action listener of the view. This listener will listen for all button clicks.
   *
   * @param listener the IActionListener set to the buttons in this view.
   */
  @Override
  public void setListener(IActionListener listener, KeyListener keys) {
    ActionListener listen = e -> listener.action(e.getActionCommand());
    playButton.addActionListener(listen);
    pauseButton.addActionListener(listen);
    restartButton.addActionListener(listen);
    increaseSpeedButton.addActionListener(listen);
    decreaseSpeedButton.addActionListener(listen);
    loopingToggleButton.addActionListener(listen);
    addKeyFrameButton.addActionListener(listen);
    removeKeyFrameButton.addActionListener(listen);
    saveAnimationButton.addActionListener(listen);
    loadAnimationButton.addActionListener(listen);
    addKeyListener(keys);


  }


  @Override
  public void makeVisible() {
    animateView.makeVisible();
    this.setVisible(true);


  }

  @Override
  public void editSpeed(int speed) {
    animateView.editSpeed(speed);
  }

  @Override
  public void setTime(int time) {
    this.animateView.setTime(time);
  }


  @Override
  public void refresh() {

    animateView.refresh();
  }

  @Override
  public void update(ArrayList<State> statesupdate) {
    animateView.update(statesupdate);
  }

  @Override
  public void displayAnimationVisual() {

    if (this.playing) {

      animateView.displayAnimationVisual();
    }
    //end this method with make visible call
    this.repaint();
    this.makeVisible();

  }

  @Override
  public void addDrawingComp(JPanel p) {
    this.add(p);
  }


  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setPlaying(boolean playing) {
    this.animateView.setPlaying(playing);
  }

  @Override
  public void increaseSpeed() {
    this.animateView.increaseSpeed();
  }

  @Override
  public void decreaseSpeed() {
    this.animateView.decreaseSpeed();
  }

  @Override
  public int getTime() {
    return this.animateView.getTime();
  }

  @Override
  public void pause() {

    this.animateView.pause();

  }

  @Override
  public void play() {
    this.playing = true;
    this.animateView.play();
  }


  @Override
  public void toggleLooping() {
    if (this.looping) {
      this.looping = false;
    } else {
      this.looping = true;
    }
    this.animateView.toggleLooping();
  }

  /**
   * Initializes the buttons for the constructor.
   */
  private void initializeButtons() {

    //Button Events
    playButton = new JButton("Play P");
    playButton.setActionCommand("Play");
    pauseButton = new JButton("Pause U");
    pauseButton.setActionCommand("Pause");
    restartButton = new JButton("Restart R");
    restartButton.setActionCommand("Restart");
    increaseSpeedButton = new JButton("Increase Speed I");
    increaseSpeedButton.setActionCommand("Increase Speed");
    decreaseSpeedButton = new JButton("Decrease Speed D");
    decreaseSpeedButton.setActionCommand("Decrease Speed");
    loopingToggleButton = new JButton("Looping Toggle L");
    loopingToggleButton.setActionCommand("Looping Toggle");
    addKeyFrameButton = new JButton("Add");
    addKeyFrameButton.setPreferredSize(new Dimension(20, 40));
    addKeyFrameButton.setActionCommand("Add");
    removeKeyFrameButton = new JButton("Remove");
    removeKeyFrameButton.setActionCommand("Remove");

    saveAnimationButton = new JButton("Save");
    saveAnimationButton.setActionCommand("Save");

    loadAnimationButton = new JButton("Load");
    loadAnimationButton = new JButton("Load");

  }

  /**
   * Initializes the panels and layouts for the constructor.
   */
  private void initializePanelsAndLayout() {
    mainPanel = new JPanel();
    mainPanel.add(new JTextArea("Hit these buttons!"));

    menuPanel = new JPanel();
    addKeyFramePanel = new JPanel();
    removeKeyFramePanel = new JPanel();
    savePanel = new JPanel();

    //1 row two columns
    addKeyFrameLayout = new GridLayout(1, 2);
  }

  /**
   * Initializes the text components of the constructor.
   */
  private void initializeTextBoxes() {

    //initlaizing addKeyTextFields
    addTtext = new TextField();
    addxtext = new TextField();
    addytext = new TextField();
    addwtext = new TextField();
    addhtext = new TextField();
    addrtext = new TextField();
    addgtext = new TextField();
    addbtext = new TextField();
    aliastext = new TextField();

    //extra cred
    textOrSVGtext = new TextField();
    textOrSVGtext.setPreferredSize(new Dimension(50, 20));
    fileNametext = new TextField();
    fileNametext.setPreferredSize(new Dimension(50, 20));
    loanAnimationtext = new TextField();
    loanAnimationtext.setPreferredSize(new Dimension(50, 20));

    //initializing text labels
    addT = new JLabel("Time");
    addx = new JLabel("X");
    addy = new JLabel("Y");
    addw = new JLabel("Width");
    addh = new JLabel("Height");
    addr = new JLabel("R");
    addg = new JLabel("G");
    addb = new JLabel("B");
    alias = new JLabel("Alias");
    timetoRemove = new JLabel("Time");
    textOrSVG = new JLabel("Text/SVG");
    loadAnimation = new JLabel("Animation file");
    fileName = new JLabel("File Name");

    removeText = new TextField();
    removeText.setPreferredSize(new Dimension(50, 20));

    aliasToRemove = new JLabel("Alias");
    aliasRemoveText = new TextField();
    aliasRemoveText.setPreferredSize(new Dimension(50, 20));

  }

  /**
   * Adds the components to the buttons in the constructor.
   */
  private void addComponents() {
    //Adding buttons to the menu panel
    menuPanel.add(playButton);
    menuPanel.add(pauseButton);
    menuPanel.add(restartButton);
    menuPanel.add(increaseSpeedButton);
    menuPanel.add(decreaseSpeedButton);
    menuPanel.add(loopingToggleButton);

    //adding the text fields to the Key Frame Panel

    addKeyFramePanel.setLayout(addKeyFrameLayout);
    addKeyFramePanel.add(addT);
    addKeyFramePanel.add(addTtext);
    addKeyFramePanel.add(addx);
    addKeyFramePanel.add(addxtext);
    addKeyFramePanel.add(addy);
    addKeyFramePanel.add(addytext);
    addKeyFramePanel.add(addw);
    addKeyFramePanel.add(addwtext);
    addKeyFramePanel.add(addh);
    addKeyFramePanel.add(addhtext);
    addKeyFramePanel.add(addr);
    addKeyFramePanel.add(addrtext);
    addKeyFramePanel.add(addg);
    addKeyFramePanel.add(addgtext);
    addKeyFramePanel.add(addb);
    addKeyFramePanel.add(addbtext);
    addKeyFramePanel.add(alias);
    addKeyFramePanel.add(aliastext);

    //add AddKeyFrameButton

    addKeyFramePanel.add(addKeyFrameButton);

    //add remove KeyFrame Button
    removeKeyFramePanel.add(timetoRemove);
    removeKeyFramePanel.add(removeText);
    removeKeyFramePanel.add(aliasToRemove);
    removeKeyFramePanel.add(aliasRemoveText);
    removeKeyFramePanel.add(removeKeyFrameButton);

    //save panel
    savePanel.add(textOrSVG);
    savePanel.add(textOrSVGtext);
    savePanel.add(fileName);
    savePanel.add(fileNametext);

    savePanel.add(saveAnimationButton);
    savePanel.add(loadAnimation);
    savePanel.add(loanAnimationtext);
    savePanel.add(loadAnimationButton);

    //add the main panel to the animative view's panel
    mainPanel.add(menuPanel);
  }

}
