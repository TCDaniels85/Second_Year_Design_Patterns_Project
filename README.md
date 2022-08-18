# Second_Year_Design_Patterns_Project
Assignment objective was to create a game in Java employing a number of design patterns

The aim of this assignment is to create an interactive game that teaches primary school children about some aspect of science. The application presented is a space themed game named ‘Planet Collector’, which aims to teach children the names and order of all the planets in the solar system as well as the dangers of asteroids and other space debris, in a fun and engaging manner. The player is required to navigate a ship to avoid or shoot enemy objects such as asteroids and satellites and collect the planets as they appear. The player scores points for shooting enemies and collecting planets and loses points for shooting planets, they also lose a life if they collide with an enemy, the game ends when either the all 8 planets are collected or the player loses three lives. 

This game has been implemented using several design patterns, namely the abstract factory pattern, the command pattern, the delegation pattern, and the object pool pattern. These design patterns offer a structured approach to software design which aid in making code more flexible and maintainable.

The object pool design pattern has been implemented within the game to handle the three types of enemy object and the background star objects. The idea of this design pattern is to reduce the cost of release and re-acquisition of resources for objects that are expensive to create, only a set amount are created and these are reused when required.

The factory pattern has been implemented within the planet collector game to handle the creation of foreground and background game objects. This separates the responsibility of game object creation from the main method to the factory which promotes loose coupling between objects improving encapsulation and eliminating the need to bind application specific classes into the code.

The delegation class has been used in the Planet Collector application to handle the delegation between the player class and the player explode class. This pattern is useful when an object is required to be of a different subclass at different points in the application.

The strategy pattern has been used in the Planet Collector application to handle the movement of the enemy and planet objects within the game. The idea of the strategy pattern is to encapsulate the movement behaviour of an enemy object into a separate class rather than having the algorithm in the main class, this enables different movement strategies to be implemented for an enemy at different points in the games run cycle. This encapsulation of object behaviour also enables the easy addition of new strategies if required.

Below is a short demonstration video of the application running:

https://user-images.githubusercontent.com/71389146/185395006-2a1ddbb2-d0cd-4f1f-9c44-a4d56397ce1e.mp4

