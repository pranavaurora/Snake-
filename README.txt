=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: pranava
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. JUnit testing:

JUnit testing was used to test the non-GUI elements of the game. Examples of tests include:
1) What is returned when a Snake collides with another Object, the boundary of the game, or itself. 
2) The manner in which the Snake moves when .moveleft(), .moveright() etc is called. 
3) The manner in which the Snake increases its length (by creating a new SnakeUnit object in the 
specific position. 

  2.Collections:

A LinkedList is used to store SnakeUnit objects. Each SnakeUnit object corresponds to a single 
"part" of the snake. The reason why a LinkedList is used is because the ordering of each SnakeUnit 
is important so that they are drawn in the right position when the Snake moves. 
When the Snake moves, the last object in this LinkedList is removed, and a new SnakeUnit object 
is created to the head of the LinkedList. The coordinates of this new SnakeUnit object will be 
determined by the direction in which the Snake moves. This mechanism allows the Snake's movement 
to be as per the original game. 

  3.File I/O:

File I/O is used to store and display the 3 highest scores recorded in the game, 
and the date these highest scores were done. The scores will be stored in a .txt file where 
each line of the file will in the format SCORE: DATE. fileReader will be used to access, 
find and display the 3 highest scores recorded in the game. fileWriter will add the current 
player's score, and date of the game being played once the game ends. 

  4.Inheritance/Subtyping for Dynamic Dispatch:

A GameItem interface is created. The Snake Class and GameObj classes implement this Interface. 
The SnakeUnit, Poison, and Fruit classes are subtypes of the GameObj class and implement the 
abstract methods of .move() and .draw(). Through Dynamic Dispatch, collisions between objects 
will determined. This inheritance/subtyping relationship ensure that universal collisionObject() 
and wallCollision() methods can be used that apply to each component in this Game. 



=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

Game: Implements the runnable class. Determines where the GameCourt, various JButtons (reset and instructions), and the control_panel JPanel - which keeps track of high scores - is displayed on the game. 


GameCourt: Extends the JPanel. This class keeps track and provide the specifics on the way that GameItem objects interact with each other. (Eg: what happens when the snake collides with the Poison, with a fruit, with itself, or with the walls.) This class further calls each GameItem's draw method. 

GameItem: This is an interface that is used to implement the abstract GameObj class, and the Snake class. It specifies specific methods such as wallCollision, draw, collisionObject. Each component of the game (fruit, poison, SnakeUnit, Snake) implements this interface using dynamic dispatch. 

GameObj:
An abstract class that implements GameItem. Has 2 abstract methods: move and draw. It is the superclass for the Fruit, the Poision, and the SnakeUnit subclass. Each of these subclasses have a different implementation for the .move() and the .draw() method. 


SnakeUnit:
A class that is used to represent each SnakeUnit. The starting Snake is made up of 4 SnakesUnit objects that are placed 22 pixels apart. 

Snake:
A class that stores each SnakeUnit object. Keeps track of the positions, and calls the .draw() for each component of the SnakeUnit. Stores each SnakeUnit object in a LinkedList. Provides methods for game functionality (eg: incrementing the length of the Snake, by making a new SnakeUnit object and adding it to the totalSnake LinkedList.)

Poison:
A class that is used to make the Poison object in the game. Moves in diagonals. If the Snake collides with a Poison object, the game will stop running. 

Fruit:
A class that is used to make the Fruit object in the game. If the Snake collides with a Fruit Object, the Snake's length with increase by 1, and the Fruit Object's x and y coordinates will be changed, and the new position of the fruit will be called to the screen. 

Direction Enum: Keeps track of the possible direction that a snake can move in: UP, DOWN, LEFT, RIGHT.



- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

I found it difficult to implement the method that checks whether the Snake Collides with itself. I had to implement a helper method in GameObj called .exactSame(). This method checks if 2 objects have the exact same x and y coordinates. I then implemented the .selfCollision() in Snake using an iterator; this iterator that checks through the LinkedList of SnakeUnit Objects to see if any 2 SnakeUnits objects have exactly the same x and y coordinates. I initially was using the .collisionObject() in GameObj before implementing the .exactSame() method; this kept on returning true causing the game to stop as each SnakeUnit objet was colliding with itself when .move() was called. 


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

I think my game design is well encapsulated and follows the right functionality. If I were to do the project again, I believe I could possibly do without the Fruit and Poison Classes. The .draw() and .move() need not be abstract, and I can instantiate fruit and poison objects and determine the way they are drawn and the way they move in the GameCourt class itself. 

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

Images
http://clipart-library.com/free-apple-clipart.html
https://www.pinterest.com/pin/398920479466388817/?lp=true
http://clipart-library.com/poison-cliparts.html

Tutorials:
How to get date: https://www.javatpoint.com/java-get-current-date

Library:
Sorted Set. https://docs.oracle.com/javase/7/docs/api/java/util/SortedSet.html

