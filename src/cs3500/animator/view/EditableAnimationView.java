package cs3500.animator.view;

import java.awt.*;

import javax.swing.*;

import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.ReadOnlyAnimationModel;


public class EditableAnimationView extends JFrame implements AnimationView {



  public static void main(String[] args){
    new EditableAnimationView(20).display((ReadOnlyAnimationModel) new AnimationModelImpl(new Point(300,300), new Dimension(300,300)));
  }

  private JPanel editorPanel;
  private JPanel shapeDisplayPanel;
  private JPanel shapeList;
  private JButton deleteShapeButton;
  private JPanel addShapePanel;
  private JTextField addShapeNameBox;
  private JComboBox<String> addShapeTypeBox;
  private JButton addShapeButton;

  private JPanel keyFrameDisplayPanel;
  private JPanel keyFrameList;
  private JButton deleteKeyFrameButton;
  private JPanel keyFrameModifyPanel;
  private JPanel editKeyFramePanel;
  private JTextField editX = new JTextField(2);
  private JTextField editY = new JTextField(2);
  private JTextField editW = new JTextField(2);
  private JTextField editH = new JTextField(2);
  private JTextField editR = new JTextField(2);
  private JTextField editG= new JTextField(2);
  private JTextField editB = new JTextField(2);
  private JButton editKeyFrameButton;
  private JPanel addKeyFramePanel;
  private JTextField addT = new JTextField(2);
  private JTextField addX = new JTextField(2);
  private JTextField addY = new JTextField(2);
  private JTextField addW = new JTextField(2);
  private JTextField addH = new JTextField(2);
  private JTextField addR = new JTextField(2);
  private JTextField addG = new JTextField(2);
  private JTextField addB = new JTextField(2);
  private JButton addKeyFrameButton;

  private JPanel viewerPanel;
  private JPanel displayPanel;
  private JScrollPane displayScrollPane;
  private JPanel playerPanel;
  private JButton restartButton;
  private JButton playButton;
  private JButton pauseButton;
  private JTextField speedTextField;
  private JButton changeSpeedButton;
  private JCheckBox isLoopingBox;

  private final int speed; //Speed in ticks per second

  /**
   * Constructs a visual animation view that shows an animation of the given model.
   */
  public EditableAnimationView(IAnimationController controller, int speed){
    super();
    this.speed = speed;
    this.setTitle("Excellent");
    this.setPreferredSize(new Dimension(1000, 600));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  /**
   * Displays the given model's animation in a fully animated view.
   *
   * @param model the animation model to display.
   * @throws IllegalStateException this method does not throw this exception.
   */
  public void display(ReadOnlyAnimationModel model) throws IllegalStateException{
    displayPanel = new AnimationPanel(model, this.speed);
    displayPanel.setPreferredSize(new Dimension(500,500));
    displayScrollPane = new JScrollPane(displayPanel);

    editorPanel = new JPanel(new GridLayout(2, 2, 10, 10));
    editorPanel.setPreferredSize(new Dimension(500,600));

    shapeDisplayPanel = new JPanel(new BorderLayout());
    shapeDisplayPanel.add(new JLabel("Shapes"), BorderLayout.PAGE_START);
    shapeList = new JPanel();
    shapeDisplayPanel.add(shapeList, BorderLayout.CENTER);
    deleteShapeButton = new JButton("Delete");
    deleteShapeButton.setEnabled(false);
    shapeDisplayPanel.add(deleteShapeButton, BorderLayout.PAGE_END);

    keyFrameDisplayPanel = new JPanel(new BorderLayout());
    keyFrameDisplayPanel.add(new JLabel("Key Frames"), BorderLayout.PAGE_START);
    keyFrameList = new JPanel();
    keyFrameDisplayPanel.add(keyFrameList, BorderLayout.CENTER);
    deleteKeyFrameButton = new JButton("Delete");
    deleteKeyFrameButton.setEnabled(false);
    keyFrameDisplayPanel.add(deleteKeyFrameButton, BorderLayout.PAGE_END);

    addShapePanel = new JPanel();
    JLabel nameLabel = new JLabel("Name");
    addShapeNameBox = new JTextField(15);

    JLabel shapeLabel = new JLabel("Shape");
    addShapeTypeBox = new JComboBox<String>(new String[]{"Rectangle", "Ellipse"});

    addShapeButton = new JButton("Add Shape");
    addShapeButton.setPreferredSize(new Dimension(200, 30));

    addShapePanel.add(nameLabel);
    addShapePanel.add(addShapeNameBox);
    addShapePanel.add(shapeLabel);
    addShapePanel.add(addShapeTypeBox);
    addShapePanel.add(addShapeButton);

    keyFrameModifyPanel = new JPanel();
    editKeyFramePanel = new JPanel();
    editKeyFramePanel.setPreferredSize(new Dimension(250,100));
    addKeyFramePanel = new JPanel();
    addKeyFramePanel.setPreferredSize(new Dimension(250,100));
    editKeyFramePanel.add(new JLabel("x"));
    editKeyFramePanel.add(editX);
    editKeyFramePanel.add(new JLabel("y"));
    editKeyFramePanel.add(editY);
    editKeyFramePanel.add(new JLabel("w"));
    editKeyFramePanel.add(editW);
    editKeyFramePanel.add(new JLabel("h"));
    editKeyFramePanel.add(editH);
    editKeyFramePanel.add(new JLabel("r"));
    editKeyFramePanel.add(editR);
    editKeyFramePanel.add(new JLabel("g"));
    editKeyFramePanel.add(editG);
    editKeyFramePanel.add(new JLabel("b"));
    editKeyFramePanel.add(editB);
    editKeyFrameButton = new JButton("Edit");
    editKeyFrameButton.setPreferredSize(new Dimension(200, 30));
    editKeyFramePanel.add(editKeyFrameButton);

    addKeyFramePanel.add(new JLabel("t"));
    addKeyFramePanel.add(addT);
    addKeyFramePanel.add(new JLabel("x"));
    addKeyFramePanel.add(addX);
    addKeyFramePanel.add(new JLabel("y"));
    addKeyFramePanel.add(addY);
    addKeyFramePanel.add(new JLabel("w"));
    addKeyFramePanel.add(addW);
    addKeyFramePanel.add(new JLabel("h"));
    addKeyFramePanel.add(addH);
    addKeyFramePanel.add(new JLabel("r"));
    addKeyFramePanel.add(addR);
    addKeyFramePanel.add(new JLabel("g"));
    addKeyFramePanel.add(addG);
    addKeyFramePanel.add(new JLabel("b"));
    addKeyFramePanel.add(addB);
    addKeyFrameButton = new JButton("Add");
    addKeyFrameButton.setPreferredSize(new Dimension(200, 30));
    addKeyFramePanel.add(addKeyFrameButton);


    keyFrameModifyPanel.add(editKeyFramePanel);
    keyFrameModifyPanel.add(addKeyFramePanel);

    editorPanel.add(shapeDisplayPanel);
    editorPanel.add(keyFrameDisplayPanel);
    editorPanel.add(addShapePanel);
    editorPanel.add(keyFrameModifyPanel);

    viewerPanel = new JPanel(new BorderLayout());
    viewerPanel.setPreferredSize(new Dimension(475, 600));

    playerPanel = new JPanel();
    playerPanel.setPreferredSize(new Dimension(500,100));
    this.restartButton = new JButton("Restart");
    this.playButton = new JButton("Play");
    this.pauseButton = new JButton("Pause");
    this.speedTextField = new JTextField("" + this.speed, 2);
    this.changeSpeedButton = new JButton("Change");
    this.isLoopingBox = new JCheckBox("Looping", false);

    playerPanel.add(restartButton);
    playerPanel.add(playButton);
    playerPanel.add(pauseButton);
    playerPanel.add(new JLabel("Speed: "));
    playerPanel.add(speedTextField);
    playerPanel.add(changeSpeedButton);
    playerPanel.add(isLoopingBox);


    viewerPanel.add(displayScrollPane, BorderLayout.CENTER);
    viewerPanel.add(playerPanel, BorderLayout.PAGE_END);


    this.add(editorPanel, BorderLayout.WEST);
    this.add(viewerPanel, BorderLayout.EAST);


    this.pack();
    this.setVisible(true);
  }

  private void setFunctionality() {

  }

}
