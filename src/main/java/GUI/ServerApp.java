package GUI;

import java.util.HashMap;

import Server.GameServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ServerApp extends Application {
    Button serverChoice;
    HashMap<String, Scene> sceneMap;
    HBox buttonBox;
    Scene startScene;
    BorderPane startPane;
    GameServer serverConnection;

    ListView<String> listItems;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Start Baccarat Game Server");

        this.serverChoice = new Button("Server");
        this.serverChoice.setStyle("-fx-pref-width: 300px");
        this.serverChoice.setStyle("-fx-pref-height: 300px");

        this.serverChoice.setOnAction(e -> {
            primaryStage.setScene(sceneMap.get("server"));
            primaryStage.setTitle("Baccarat Gamer Server");

            serverConnection = new GameServer(data -> {
                Platform.runLater(() -> {
                    listItems.getItems().add(data.toString());
                });
            }, 5555);
        });

        this.buttonBox = new HBox(400, serverChoice);
        startPane = new BorderPane();
        startPane.setPadding(new Insets(70));
        startPane.setCenter(buttonBox);

        startScene = new Scene(startPane, 800, 800);

        listItems = new ListView<String>();

        sceneMap = new HashMap<String, Scene>();

        sceneMap.put("server", createServerGui());

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
        pane.setPadding(new Insets(70));
        pane.setCenter(listItems);
        return new Scene(pane, 500, 400);
    }
}
