# Baccarat Client-Server Game: The Server Application

This application is one component of the overall Baccarat Client-Server game project. The server application serves as a host for connections with one or more client application(s) [baccarat-client](https://github.com/N19htw1n9/Baccarat-Client) (Since the application is multi-threaded, it is able to host numerous clients). The client(s) will then send user input data to the server, which will prompt the server to run the game and return the results to the client(s) for output on the client GUI.

_(Note: This application was designed using Java 8, Apache Maven, Java Sockets, JavaFX 12, SceneBuilder, and JUnit 4)_

## Usage

Run the application and enter a specific port number you want to host clients on. The application will prompt the user that it's waiting for a client connection. Then run [baccarat-client](https://github.com/N19htw1n9/Baccarat-Client) and follow the procedures entailed in its README. Once that is completed, the server application will receive and display a transmission that the client connection was successful. The server application will then display all requests received and sent back to the client application.

## Testing (_with Maven_)

Extensive unit test cases have been written using JUnit 4 to ensure the game elements are working in order.

First, `cd` into the project directory, then:

```
mvn test
```

## Build/Compile (_with Maven_)

First, `cd` into the project directory, then:

```
mvn package
```

_This makes a folder named `target` in the project root, containing the `.jar` and `.class` files._

## Run (_with Maven_)

Ensure that you are running [baccarat-server](https://github.com/N19htw1n9/Baccarat-Server). Once you have built/compiled, execute this command within the same directory:

```
mvn exec:java
```
