# Animation

Simple animator program that reads in user input and animates moving shapes. Input would describe different movements or changes that pictures undergo, and at what time. 

The overall design involved an Animation interface, which contains a list of elements in the animation, or Pictures. These pictures all contain each of their motions throughout the animation. The Animation can print out a table of all of the motions for each Picture in the animation. 

The end goal of this project will be for a cs3500.animator.controller to take this Animation table and use it to send each picture and motions to the View, where the user can see the resulting animated shapes, render a text version or an SVG representation. 

## Project Organization

We divided our project into 3 components employing a version of the MVC pattern. 

### Model

Our model interface is IAnimation. This interface provides functionality such as adding/removing pictures, adding motions, and adding/removing key frames. The model stores the information about each picture in the animation. The data in the model is broken up into the following classes:

- Animation - The model of the program. Animation contains a list of Pictures to represent the elements in the animation. 

- Picture - object to be animated. Contains a list of motions, an alias, and a Shapes type. The list of motions must be continuous, with exactly one motion covering each time t. One motion's end time will be equal to the next motions start time.

- Shapes - An enum class that describes the possible types of shape objects the user may want to animate.

- Motion - describes a change in a picture over time. Consists of two states, start and end. The end time must be greater than the start time.

- State - the state of a given picture at a time. Contains the time, the coordinates, the dimensions, and the color of the picture. None of the values contained in State can be negative. Uses a Builder class in order to easily construct with custom values.

We also have an ImmutableModel Interface that is passed to View. We did this so that the user cannot directly mutuate the model, without going through this wrapper Interface. The immmutale model methods return copies of the data stored in the model, so that no return values can be modified to mutate the internal model. 

### View

We have two view interfaces, both with the purpose of sending/receiving information to/from the user. 

The first view interface was IView. This interface provides the functionality to display the animation, which takes in an Immutable Model to pull data from. The following two classes implement this interface:

- Text View - A simple text view that outputs the model in the form of a table with motions listed in order of ascending time. 

- SVG View - An SVG rendering of the model. This can be opened in a web browser to view the animation visually. 

The second view interface is the IAnimateView. This interface is designed for the views that have a GUI user interface. This interface also has the same display animation method as the previous view interface, but it has many additional methods as well, such as set speed, increase speed, decrease speed, play, pause, loop, and other methods that allow a user to control the playback of the animation. The following classes implement the IAnimateView interface:

- VisualAnimation View - A visual representation of the model, that is opened when the user runs the program. 

- Editor View - A visual representation of the model where the user can play, pause, restart, change speed, loop and edit keyframes, and save their animation. Users can also load another animation while the current is running - the new animation will open in another window. This view has a VisualAnimationView as a field, so as to avoid duplication in the similarities between these two views. 
  * NOTE: Key Frames may be added at any time, but may only be removed at the endpoints of a motion. The UI throws appropriate error messages for these conditions. 

## Directions
To run the program, the user enters command arguments to specify, view type, input file, optional output and optional speed and runs the program. Depending on the type of view chosen, the program will create a file or open a window with the animation. 

 

## Built in

* IntelliJ IDEA


## Contributing

Contributions by Grainne Casey and Mahema Singh.

See https://github.com/grainnecasey/Animation2 for the repository. As of right now this is a private repository
and can be viewed with permission from authors.


