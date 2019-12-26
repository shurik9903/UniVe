package sample;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;

public class AlertBox{

    public static final Object sMonitor = new Object();

    public static void WinLose(String title, String message, String buttext){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(100);
        window.setResizable(false);

        Label label = new Label();
        label.setText(message);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        Button closeButton = new Button(buttext);
        closeButton.setOnAction(e -> {
            window.close();
            synchronized (sMonitor) {
                sMonitor.notify();
            }
            if (Controller.TestLevel){
                try {
                    Controller.ReturnLevelEditor();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Controller.TestLevel = false;
            } else {
                try {
                    Main.window.setScene(new Scene(FXMLLoader.load(AlertBox.class.getResource("GameStart.fxml"))));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        VBox layout = new VBox(3);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (sMonitor) {
                    try {
                        sMonitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void display(String title, String message, String buttext){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(100);
        window.setResizable(false);

        Label label = new Label();
        label.setText(message);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        Button closeButton = new Button(buttext);
        closeButton.setOnAction(e -> {
            window.close();
            synchronized (sMonitor) {
                sMonitor.notify();
            }
        });

        VBox layout = new VBox(3);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (sMonitor) {
                    try {
                        sMonitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void exit(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Выход");
        window.setWidth(300);
        window.setHeight(100);
        window.setResizable(false);

        Label label = new Label();
        label.setText("Вы действительно хотите выйти ?");

        Button closeButtonYes = new Button("Да");
        Button closeButtonNo = new Button("Нет");
        closeButtonYes.setOnAction(e -> System.exit(0));
        closeButtonNo.setOnAction(event -> window.close());

        HBox butbox = new HBox(2);
        butbox.getChildren().addAll(closeButtonYes,closeButtonNo);
        butbox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(3);
        layout.getChildren().addAll(label,butbox);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

}