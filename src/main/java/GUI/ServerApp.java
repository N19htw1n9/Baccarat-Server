package GUI;

import Server.GameServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ServerApp extends Application {
    TextField portField;
    Scene startScene;
    BorderPane startPane;
    GameServer serverConnection;

    ListView<String> listItems = new ListView<String>();

    private int port = 5555;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Start Baccarat Game Server");

        this.portField = new TextField("5555");
        this.portField.setPromptText("Port number (e.g. 5555)");

        Button startServerButton = new Button("Start Server");
        startServerButton.setOnAction(e -> {
            this.port = Integer.parseInt(this.portField.getText());
            primaryStage.setScene(this.createServerGui());
            primaryStage.setTitle("Baccarat Gamer Server");

            serverConnection = new GameServer(data -> {
                Platform.runLater(() -> {
                    listItems.getItems().add(data.toString());
                });
            }, this.port);
        });

        HBox startContent = new HBox(20, this.portField, startServerButton);

        startContent.setPadding(new Insets(10));
        startScene = new Scene(startContent, 500, 400);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public Scene createServerGui() {
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));
        pane.setCenter(listItems);
        return new Scene(pane, 500, 700);
    }
}
