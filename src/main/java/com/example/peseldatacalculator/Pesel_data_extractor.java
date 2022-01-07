package com.example.peseldatacalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Pesel_data_extractor extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Pesel_data_extractor.class.getResource("Pesel extractor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PESEL Data Extractor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}