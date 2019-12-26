package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Main extends Application {


    static MediaView Video;
    public static boolean DBConnection;
    public static AnchorPane AP1;
    public static AnchorPane AP2;
    public static AnchorPane AP3;
    public static AnchorPane AP4;
    public static AnchorPane AP5;

    public static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ConnectToDataBase.ConnectionToDateBase();
        window = primaryStage;
        Parent Loading = FXMLLoader.load(getClass().getResource("LoginMenu.fxml"));
        window.setTitle("Dungeon programming");
        window.setScene(new Scene(Loading));
        window.setResizable(false);

        Video = (MediaView)Loading.lookup("#Video");
        MediaPlayer MP = new MediaPlayer(new Media(new File("src/sample/Picture/Base/Fon.mp4").toURI().toString()));
        Video.setMediaPlayer(MP);
        MP.setAutoPlay(true);
        MP.setCycleCount(MediaPlayer.INDEFINITE);

        window.show();

        AP1 = (AnchorPane)Loading.lookup("#AP1");
        AP2 = (AnchorPane)Loading.lookup("#AP2");
        AP3 = (AnchorPane)Loading.lookup("#AP3");
        AP4 = (AnchorPane)Loading.lookup("#AP4");
        AP5 = (AnchorPane)Loading.lookup("#AP5");
        new Thread(new Runnable(){
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                AP1.setVisible(false);
                Refresh();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void Refresh (){
        if (DBConnection) {
            AP2.setVisible(true);
            AP1.setVisible(false);
            AP3.setVisible(false);
            AP4.setVisible(false);
            AP5.setVisible(false);
        } else {
            AP3.setVisible(true);
            AP1.setVisible(false);
            AP2.setVisible(false);
            AP4.setVisible(false);
            AP5.setVisible(false);
        }
    }
}