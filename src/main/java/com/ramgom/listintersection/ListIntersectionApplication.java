package com.ramgom.listintersection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListIntersectionApplication extends Application {

    private static final int WIDTH = 410;
    private static final int HEIGHT = 275;
    private static final String FXML_RESOURCE_NAME = "/listIntersection.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(FXML_RESOURCE_NAME));
        primaryStage.setTitle("List Intersection");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
