# Number_Puzzle
Number Puzzle implemented in Java for 3x3, 4x4 and 5x5

For Testing, go in Game View and set the testing variable true. This enables a skip for testing button during the game in order to test the functionality multiple times, without having to complete the game every time

MVC structure
The Controller is used to connect, open and close the different Views

The game package contains the game logic. The game is implemented as a running thread that checks for user input using the KeyHandler and swaps the empty tile with the corresponding numbered tile. It is all contained in a panel that is easly added to the view frame.

The model package contains the classes for the Coordinates used to map the tiles, and the Game class that contains the data of the game for easier ranking

The view package contains all the views
