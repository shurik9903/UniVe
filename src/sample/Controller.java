package sample;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.apache.commons.io.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


public class Controller implements Initializable{


    @FXML
    MediaView Video;
    @FXML
    ImageView ImagePPP;
    @FXML
    ImageView ImageAR;
    @FXML
    ImageView ImageAR2;
    @FXML
    ImageView ImageA;
    @FXML
    ImageView ImageR;
    @FXML
    ImageView ImageB;
    @FXML
    ImageView ImageB2;
    @FXML
    ImageView ImageB3;
    @FXML
    ImageView ImageZareg;
    @FXML
    ImageView ImageAutorize;
    @FXML
    AnchorPane AP1;
    @FXML
    AnchorPane AP2;
    @FXML
    AnchorPane AP3;
    @FXML
    AnchorPane AP4;
    @FXML
    AnchorPane AP5;
    @FXML
    AnchorPane APProfile;
    @FXML
    Button BAGH;
    @FXML
    Button BAGV;
    @FXML
    Button BRGH;
    @FXML
    Button BRGV;
    @FXML
    Button BStSt;
    @FXML
    AnchorPane ScrollAnchor;
    @FXML
    AnchorPane AncorlevelEditor;
    @FXML
    HBox HGrid;
    @FXML
    VBox VBoxField;
    @FXML
    AnchorPane AnchorMenuWindow;
    @FXML
    TextField LevelNameText;
    @FXML
    ComboBox LevelComboBox;
    @FXML
    ComboBox LevelUserComboBox;
    @FXML
    TextField ProfileTextField;
    @FXML
    ComboBox ProfileComboBox;
    @FXML
    ComboBox ViewGame;
    @FXML
    ImageView Hero;
    @FXML
    AnchorPane AnchorPaneField;
    @FXML
    ScrollPane ScrollPaneField;
    @FXML
    ComboBox BlockType;
    @FXML
    Button ViewBut;
    @FXML
    ScrollPane ScrollGame;
    @FXML
    TextArea AreaCode;
    @FXML
    TextArea AreaCodeInfo;
    @FXML
    TextField EmailTextReg;
    @FXML
    TextField LoginTextReg;
    @FXML
    TextField PasTextReg;
    @FXML
    TextField PPasTextReg;
    @FXML
    TextField PasTextAut;
    @FXML
    TextField LoginTextAut;

    static boolean StopRunCode = false;
    static boolean ImageDragged = false;
    static boolean LoadLevel;
    static boolean LevelComplete;
    static boolean LevelEditor = true;
    public static boolean TestLevel = false;

    static String[][] Way = new String[100][100];
    static Image[] FieldsImage = new Image[259];
    static Image[][] LevelImage;

    static String LoadLevelNumber;
    static String[] fieldslevel;
    String UMLImage;
    String URLFieldType = "sample/Picture/Fields/Floor/F_";
    public static String LevelName;
    public static String ProfileName;

    public static String EmailReg = null;
    public static String LoginReg = null;
    public static String PasReg = null;
    public static String PPasReg = null;

    public static String LoginAut = null;
    public static String PasAut = null;


    public static final HashSet<String> Action = new HashSet<>(Arrays.asList("Прыжок","ПоворотНаЛево","ПоворотНаПраво","ИдтиВперед","ИдтиНаЛево","ИдтиНаПраво","ИдтиНазад","Активировать"));
    public static final HashSet<String> conditions = new HashSet<>(Arrays.asList("СпередиСтена","СлеваСтена","СправаСтена","СзадиСтена"));

    static int numHorizontal;
    static int numVertical;

    double HeroOldX,HeroOldY,HeroX,HeroY;

    public void VideoStart(){
        MediaPlayer MP = new MediaPlayer(new Media(new File("src/sample/Picture/Base/Fon.mp4").toURI().toString()));
        Video.setMediaPlayer(MP);
        MP.setAutoPlay(true);
        MP.setCycleCount(MediaPlayer.INDEFINITE);

    }

    public static ArrayList<ArrayList<String>> VariablesPlayer = new ArrayList<ArrayList<String>>();

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public void RegistrationB(MouseEvent mouseEvent) {
    Main.Refresh();
    if (Main.DBConnection){
        AP2.setVisible(false);
        AP4.setVisible(true);
        EmailTextReg.clear();
        LoginTextReg.clear();
        PasTextReg.clear();
        PPasTextReg.clear();
    } else {
        AP2.setVisible(false);
        AP3.setVisible(true);
    }
    }

    public void AuthorizationB(MouseEvent mouseEvent) {
        Main.Refresh();
        if (Main.DBConnection){
            AP2.setVisible(false);
            AP5.setVisible(true);
            LoginTextAut.clear();
            PasTextAut.clear();
        } else {
            AP2.setVisible(false);
            AP3.setVisible(true);
        }
    }

    public void Refresh(MouseEvent mouseEvent) {
        Main.Refresh();
    }

    public void BackB(MouseEvent mouseEvent) {
        Main.Refresh();
        if (Main.DBConnection){
            AP2.setVisible(true);
            AP5.setVisible(false);
            AP4.setVisible(false);
        } else {
            AP3.setVisible(true);
            AP5.setVisible(false);
            AP4.setVisible(false);
        }
    }

    public void BackLogin(MouseEvent mouseEvent) throws Exception {
        ConnectToDataBase.ConnectionToDateBase();
        Parent Loading = FXMLLoader.load(getClass().getResource("LoginMenu.fxml"));
        Main.window.setScene(new Scene(Loading));
        Main.window.show();
        Main.AP1 = (AnchorPane)Loading.lookup("#AP1");
        Main.AP2 = (AnchorPane)Loading.lookup("#AP2");
        Main.AP3 = (AnchorPane)Loading.lookup("#AP3");
        Main.AP4 = (AnchorPane)Loading.lookup("#AP4");
        Main.AP5 = (AnchorPane)Loading.lookup("#AP5");
        Main.Refresh();
    }

    public void Exit(ActionEvent actionEvent) {
       AlertBox.exit();
    }

    public void SettingsWindowOpen(ActionEvent actionEvent) throws IOException {
        Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("Settings.fxml"))));
    }

    public void MainWindowOpen(MouseEvent mouseEvent) throws IOException {
        Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml"))));
    }

    public void OpenLevelEditor(ActionEvent actionEvent) throws IOException {

        String text = LevelNameText.getText().replaceAll("\\s","");

        if (text.trim().isEmpty()){
            AlertBox.display("Ошибка","Введите название уровня.","ОК");
        } else if ((new File("src/sample/Level/PlayersLevels/"+text+".txt")).exists()) {
            AlertBox.display("Ошибка","Уровень с таким именем уже существует.","ОК");
        } else if (text.length() > 16) {
            AlertBox.display("Ошибка","Имя уровня не должно быть длиннее 16 символов","ОК");
        }else if(!TextValidation(text)) {
            AlertBox.display("Ошибка","Имя уровня не должно содержать следующих знаков:\\/:*?+\"<>|","ОК");
        }else{
            LoadLevel = false;
            LevelImage = new Image[100][100];
            Way = new String[100][100];
            numHorizontal = 4;
            numVertical = 4;
            LevelName = text;
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("LevelEditor.fxml"))));
        }
    }

    public void OpenLevelEditorSettings(ActionEvent actionEvent) throws IOException {
        Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("LevelEditorSettings.fxml"))));
    }

    public void AddGridHorizontal(ActionEvent actionEvent) {
        if (HGrid.getPrefWidth() < 10000) {
            HGrid.setPrefWidth(HGrid.getPrefWidth()+100);
            HGridAddHorizontal();
            BAGH.setLayoutX(BAGH.getLayoutX() + 100);
            BRGH.setLayoutX(BRGH.getLayoutX() + 100);
            ScrollAnchor.setPrefWidth(ScrollAnchor.getPrefWidth()+100);
        }
    }

    public void AddGridVertical(ActionEvent actionEvent) {
        if (HGrid.getPrefHeight() < 10000) {
            HGrid.setPrefHeight(HGrid.getPrefHeight()+100);
            HGridAddVertical();
            BAGV.setLayoutY(BAGV.getLayoutY() + 100);
            BRGV.setLayoutY(BRGV.getLayoutY() + 100);
            ScrollAnchor.setPrefHeight(ScrollAnchor.getPrefHeight()+100);
        }
    }

    public void RemoveGridHorizontal(ActionEvent actionEvent) {
        if (HGrid.getPrefWidth() > 400) {
            HGrid.setPrefWidth(HGrid.getPrefWidth()-100);
            HGridRemoveHorizontal();
            BAGH.setLayoutX(BAGH.getLayoutX() - 100);
            BRGH.setLayoutX(BRGH.getLayoutX() - 100);
            ScrollAnchor.setPrefWidth(ScrollAnchor.getPrefWidth()-100);
        }
    }

    public void RemoveGridVertical(ActionEvent actionEvent) {
        if (HGrid.getPrefHeight() > 400) {
            HGrid.setPrefHeight(HGrid.getPrefHeight()-100);
            HGridRemoveVertical();
            BAGV.setLayoutY(BAGV.getLayoutY() - 100);
            BRGV.setLayoutY(BRGV.getLayoutY() - 100);
            ScrollAnchor.setPrefHeight(ScrollAnchor.getPrefHeight()-100);
        }
    }

    public void ClickMenu(ActionEvent actionEvent) {
        AnchorMenuWindow.setVisible(true);
    }

    public void HGridAddHorizontal(){
        numHorizontal++;
        RefreshGridImage();
    }

    public void HGridRemoveHorizontal(){
        for (int i = 0; i < numVertical; i++){
            LevelImage[numHorizontal-1][i] = null;
        }
        numHorizontal--;
        RefreshGridImage();
        if (Hero.getTranslateX() > HGrid.getPrefWidth()-100){
            Hero.setTranslateX(Hero.getTranslateX()-100);
        }
    }

    public void HGridAddVertical(){
        numVertical++;
        RefreshGridImage();
    }

    public void HGridRemoveVertical(){
        for (int i = 0; i < numHorizontal; i++){
            LevelImage[i][numVertical-1] = null;
        }
        numVertical--;
        RefreshGridImage();
        if (Hero.getTranslateY() > HGrid.getPrefHeight()-100){
            Hero.setTranslateY(Hero.getTranslateY()-100);
        }
    }

    public void RefreshGridImage(){
        HGrid.getChildren().clear();

        for (int i = 1; i <= numHorizontal; i++){
            VBox vbox = new VBox();
            vbox.setId(String.valueOf(numHorizontal));
            for (int k = 1; k <= numVertical; k++){

                int finalI = i;
                int finalK = k;

                ImageView imageView = new ImageView("sample/Picture/Fields/0.png");
                if (LevelImage[i-1][k-1] != null){
                    imageView.setImage(LevelImage[i-1][k-1]);
                } else {
                    LevelImage[i-1][k-1] = new Image("sample/Picture/Fields/0.png");
                }

                if (LevelEditor) {
                    imageView.setId(String.valueOf(i) + String.valueOf(k));
                    imageView.setOnDragOver(e -> {
                        if (ImageDragged) {
                            e.acceptTransferModes(TransferMode.ANY);
                        }
                    });

                    imageView.setOnDragDropped(e -> {
                        for (int j = 1; j < FieldsImage.length; j++) {
                            if ((FilenameUtils.getBaseName(UMLImage).equals(FilenameUtils.getBaseName(FieldsImage[j].getUrl()))) && (ImageDragged)) {
                                imageView.setImage(e.getDragboard().getImage());
                                LevelImage[finalI - 1][finalK - 1] = new Image(UMLImage);
                                if (FilenameUtils.getBaseName(LevelImage[finalI - 1][finalK - 1].getUrl()).substring(0, 1).equals("F")) {
                                    Way[finalI - 1][finalK - 1] = "Green";
                                } else if (FilenameUtils.getBaseName(LevelImage[finalI - 1][finalK - 1].getUrl()).substring(0, 1).equals("W")) {
                                    Way[finalI - 1][finalK - 1] = "Red";
                                } else if (FilenameUtils.getBaseName(LevelImage[finalI - 1][finalK - 1].getUrl()).substring(0, 1).equals("T")){
                                    Way[finalI - 1][finalK - 1] = "Blue";
                                }
                                break;
                            }
                        }
                    });
                }

                vbox.getChildren().add(imageView);
            }
            HGrid.getChildren().add(vbox);
        }
    }

    public void RefreshGridMark(){
        HGrid.getChildren().clear();

        for (int i = 1; i <= numHorizontal; i++){
            VBox vbox = new VBox();
            vbox.setId(String.valueOf(numHorizontal));
            for (int k = 1; k <= numVertical; k++){

                ImageView imageView = new ImageView("sample/Picture/Fields/0.png");
                if (LevelImage[i-1][k-1] != null){
                    if (Way[i-1][k-1] == null) {
                        Image image = new Image("sample/Picture/Fields/0.png");
                        imageView.setImage(image);
                    } else if (Way[i-1][k-1].equals("Red")){
                        Image image = new Image("sample/Picture/Fields/Red.png");
                        imageView.setImage(image);
                    } else if (Way[i-1][k-1].equals("Blue")){
                        Image image = new Image("sample/Picture/Fields/Blue.png");
                        imageView.setImage(image);
                    } else if (Way[i-1][k-1].equals("Green")){
                        Image image = new Image("sample/Picture/Fields/Green.png");
                        imageView.setImage(image);
                    }
                } else {
                    LevelImage[i-1][k-1] = new Image("sample/Picture/Fields/0.png");
                }

                imageView.setId(String.valueOf(i) + String.valueOf(k));

                vbox.getChildren().add(imageView);
            }
            HGrid.getChildren().add(vbox);
        }
    }

    public void FieldMenu(){
        AnchorPaneField.setPrefHeight(FieldsImage.length*157);
        for (int i = 1; i < FieldsImage.length; i++){
            Label label = new Label("Блок №"+i);
            label.setAlignment(Pos.CENTER);
            FieldsImage[i] = new Image(URLFieldType + i + ".png");
            ImageView imageView = new ImageView(FieldsImage[i]);
            imageView.setId("Field"+i);
            imageView.setOnDragDetected(e -> {
                Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                Image picture = new Image(imageView.getImage().getUrl());
                content.putImage(picture);
                db.setContent(content);
                UMLImage = imageView.getImage().getUrl();
                ImageDragged = true;
            });
            imageView.setOnDragDone(e ->{
                ImageDragged = false;
            });
            VBoxField.getChildren().addAll(label,imageView);
        }

    }

    public void AddSaveFile(){
        File myFolder = new File("src/sample/Level/PlayersLevels");
        File[] files = myFolder.listFiles();
        for (int i = 0; i < files.length; i++){
            if (FilenameUtils.getExtension(files[i].getName()).equals("txt")){
                LevelComboBox.getItems().add(files[i].getName().substring(0,files[i].getName().indexOf('.')));
            }
        }
    }

    public void AddProfile(){
        File myFolder = new File("src/sample/Profile");
        File[] files = myFolder.listFiles();
        for (int i = 0; i < files.length; i++){
            if (FilenameUtils.getExtension(files[i].getName()).equals("txt")){
                ProfileComboBox.getItems().add(files[i].getName().substring(0,files[i].getName().indexOf('.')));
            }
        }
    }

    public void AddUserLevel(){
        File myFolder = new File("src/sample/Level/PlayersLevels");
        File[] files = myFolder.listFiles();
        for (int i = 0; i < files.length; i++){
            if (FilenameUtils.getExtension(files[i].getName()).equals("txt")){
                LevelUserComboBox.getItems().add(files[i].getName().substring(0,files[i].getName().indexOf('.')));
            }
        }
    }

    public void ComboBlockType(){
    BlockType.getItems().addAll("Пол","Стены","Ловушки");
    BlockType.setValue("Пол");
}

    public void ComboGame(){
        ViewGame.getItems().addAll("Вид: стандартный","Вид: метки","Вид: код");
        ViewGame.setValue("Вид: стандартный");
    }

    public void HideMenuLevel(ActionEvent actionEvent) {
        AnchorMenuWindow.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (FilenameUtils.getName(location.getPath()).equals("LevelEditorSettings.fxml")){
            AddSaveFile();
        }
        if ((FilenameUtils.getName(location.getPath()).equals("LevelEditor.fxml")) && !LoadLevel){
            LevelEditor = true;
            RefreshGridImage();
            FieldMenu();
            ComboBlockType();
        } else if ((FilenameUtils.getName(location.getPath()).equals("LevelEditor.fxml")) && LoadLevel){
            LevelEditor = true;
            RefreshGridImage();
            FieldMenu();
            LevelEditorLoadLevelComponents(fieldslevel);
            ComboBlockType();
        } else if ((FilenameUtils.getName(location.getPath()).equals("GameLevel.fxml"))) {
            LevelEditor = false;
            RefreshGridImage();
            GameLevelComponents(fieldslevel);
            ComboGame();
        } else if ((FilenameUtils.getName(location.getPath()).equals("LoginMenu.fxml"))){
            VideoStart();
            AddProfile();
        } else if ((FilenameUtils.getName(location.getPath()).equals("LevelUserLoad.fxml"))){
            AddUserLevel();
        }
    }

    public void SaveLevel() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("src/sample/Level/PlayersLevels/" + LevelName + ".txt", "UTF-8");
        writer.println("Dungeon programming: "+ LevelName);
        writer.println("-");
        writer.println("Horizontal number: ");
        writer.println(numHorizontal);
        writer.println("Vertical number: ");
        writer.println(numVertical);
        writer.println("-");

        LevelComplete = true;
        for (int i = 0; i < numHorizontal; i++){
            for (int j = 0; j < numVertical; j++){
                writer.print(FilenameUtils.getBaseName(LevelImage[i][j].getUrl()));
                writer.print("|");
                if (FilenameUtils.getBaseName(LevelImage[i][j].getUrl()).equals("0")){
                    LevelComplete = false;
                }
            }
        }

        writer.println();
        writer.println("-");
        writer.println("BHorizontal: ");
        writer.println(BAGH.getLayoutX());
        writer.println("BVertical: ");
        writer.println(BAGV.getLayoutY());
        writer.println("Scroll: ");
        writer.println(ScrollAnchor.getPrefWidth());
        writer.println(ScrollAnchor.getPrefHeight());

        writer.println("-");
        writer.println("Hero:");
        writer.println("X");
        writer.println(Hero.getTranslateX());
        writer.println("Y");
        writer.println(Hero.getTranslateY());

        writer.println("-");
        writer.println("LevelComplete: ");
        writer.println(LevelComplete);

        writer.println();
        for (int i = 0; i < numHorizontal; i++){
            for (int j = 0; j < numVertical; j++){
                if (FilenameUtils.getBaseName(LevelImage[i][j].getUrl()).substring(0,1).equals("F")){
                    writer.print("1");
                    writer.print("|");
                } else if (FilenameUtils.getBaseName(LevelImage[i][j].getUrl()).substring(0,1).equals("W")){
                    writer.print("0");
                    writer.print("|");
                } else if (FilenameUtils.getBaseName(LevelImage[i][j].getUrl()).substring(0,1).equals("T")){
                    writer.print("2");
                    writer.print("|");
                }

            }
        }

        writer.close();
        AlertBox.display("Сохранение","Уровень успешно сохранен","ОК");
    }

    public void SaveLevelButton(ActionEvent actionEvent) throws FileNotFoundException, UnsupportedEncodingException {
        SaveLevel();
    }

    public void ExitLevel(ActionEvent actionEvent) throws IOException {
        if (TestLevel) {
            ReturnLevelEditor();
            TestLevel = false;
        } else {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml"))));
        }
    }

    public void DeleteSaveButton(ActionEvent actionEvent) {
        if (LevelComboBox.getValue() == null){
            AlertBox.display("Внимание","Выберите уровень который хотите удалить","ОК");
        } else {
            File file = new File("src/sample/Level/PlayersLevels/" + LevelComboBox.getValue().toString() + ".txt");
            file.delete();
            LevelComboBox.getItems().clear();
            AddSaveFile();
            LevelComboBox.getSelectionModel().selectFirst();
        }


    }

    public void LevelEditorLoadLevel(ActionEvent actionEvent) throws IOException {
        String FieldNumber;
        FieldsImage = new Image[259];
        URLFieldType = "sample/Picture/Fields/Floor/F_";


        if (LevelComboBox.getValue() == null){
            AlertBox.display("Внимание","Выберите уровень который хотите загрузить","ОК");
        } else {
            fieldslevel = new String[12];
            String line;
            int NumberString = 0;
            FileReader file = new FileReader("src/sample/Level/PlayersLevels/" + LevelComboBox.getValue().toString() + ".txt");
            BufferedReader buffread = new BufferedReader(file);

            while ((line = buffread.readLine()) != null) {
                NumberString++;
                switch (NumberString) {
                    case 1:
                        fieldslevel[0] = line;
                        break;
                    case 4:
                        fieldslevel[1] = line;
                        break;
                    case 6:
                        fieldslevel[2] = line;
                        break;
                    case 8:
                        fieldslevel[3] = line;
                        break;
                    case 11:
                        fieldslevel[4] = line;
                        break;
                    case 13:
                        fieldslevel[5] = line;
                        break;
                    case 15:
                        fieldslevel[6] = line;
                        break;
                    case 16:
                        fieldslevel[7] = line;
                        break;
                    case 20:
                        fieldslevel[8] = line;
                        break;
                    case 22:
                        fieldslevel[9] = line;
                        break;
                    case 25:
                        fieldslevel[10] = line;
                        break;
                    case 27:
                        fieldslevel[11] = line;
                        break;
                }
            }

            if ((NumberString == 27) && (fieldslevel[0].equals("Dungeon programming: "+ LevelComboBox.getValue().toString()))){
                numHorizontal = Integer.parseInt(fieldslevel[1]);
                numVertical = Integer.parseInt(fieldslevel[2]);
                LevelImage = new Image[100][100];

                for (int i = 0; i < numHorizontal; i++){
                    for (int j = 0; j < numVertical; j++){
                        if (fieldslevel[3].length() != 0) {
                            FieldNumber = fieldslevel[3].substring(0, fieldslevel[3].indexOf("|"));
                            fieldslevel[3] = fieldslevel[3].substring(fieldslevel[3].indexOf("|") + 1);
                            if (FieldNumber.substring(0,1).equals("F")){
                                LevelImage[i][j] = new Image("sample/Picture/Fields/Floor/" + FieldNumber + ".png");
                            } else if (FieldNumber.substring(0,1).equals("W")){
                                LevelImage[i][j] = new Image("sample/Picture/Fields/Wall/" + FieldNumber + ".png");
                            } else if (FieldNumber.substring(0,1).equals("T")){
                                LevelImage[i][j] = new Image("sample/Picture/Fields/Traps/" + FieldNumber + ".png");
                            }
                        }

                        if (fieldslevel[11].length() != 0) {
                            FieldNumber = fieldslevel[11].substring(0, fieldslevel[11].indexOf("|"));
                            fieldslevel[11] = fieldslevel[11].substring(fieldslevel[11].indexOf("|") + 1);
                            if (FieldNumber.substring(0,1).equals("1")){
                                Way[i][j] = "Green";
                            } else if (FieldNumber.substring(0,1).equals("0")){
                                Way[i][j] = "Red";
                            } else if (FieldNumber.substring(0,1).equals("2")){
                                Way[i][j] = "Blue";
                            }
                        }

                    }
                }



                LevelComplete = Boolean.parseBoolean(fieldslevel[10]);
                LoadLevel = true;
                LevelName = LevelComboBox.getValue().toString();
                Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("LevelEditor.fxml"))));

            }else{
                AlertBox.display("Ошибка","Выбранный уровень поврежден или не является уровнем данной игры","ОК");
            }

            buffread.close();

        }
    }

    public static void ReturnLevelEditor() throws IOException {
        String FieldNumber;
        fieldslevel = new String[12];
        String line;
        int NumberString = 0;

        FileReader file = new FileReader("src/sample/Level/PlayersLevels/" + LevelName + ".txt");
        BufferedReader buffread = new BufferedReader(file);

        while ((line = buffread.readLine()) != null) {
            NumberString++;
            switch (NumberString) {
                case 1:
                    fieldslevel[0] = line;
                    break;
                case 4:
                    fieldslevel[1] = line;
                    break;
                case 6:
                    fieldslevel[2] = line;
                    break;
                case 8:
                    fieldslevel[3] = line;
                    break;
                case 11:
                    fieldslevel[4] = line;
                    break;
                case 13:
                    fieldslevel[5] = line;
                    break;
                case 15:
                    fieldslevel[6] = line;
                    break;
                case 16:
                    fieldslevel[7] = line;
                    break;
                case 20:
                    fieldslevel[8] = line;
                    break;
                case 22:
                    fieldslevel[9] = line;
                    break;
                case 25:
                    fieldslevel[10] = line;
                    break;
                case 27:
                    fieldslevel[11] = line;
                    break;
            }
        }

        numHorizontal = Integer.parseInt(fieldslevel[1]);
        numVertical = Integer.parseInt(fieldslevel[2]);
        LevelImage = new Image[100][100];

        for (int i = 0; i < numHorizontal; i++){
            for (int j = 0; j < numVertical; j++){
                if (fieldslevel[3].length() != 0) {
                    FieldNumber = fieldslevel[3].substring(0, fieldslevel[3].indexOf("|"));
                    fieldslevel[3] = fieldslevel[3].substring(fieldslevel[3].indexOf("|") + 1);
                    if (FieldNumber.substring(0,1).equals("F")){
                        LevelImage[i][j] = new Image("sample/Picture/Fields/Floor/" + FieldNumber + ".png");
                        } else if (FieldNumber.substring(0,1).equals("W")){
                        LevelImage[i][j] = new Image("sample/Picture/Fields/Wall/" + FieldNumber + ".png");
                        } else if (FieldNumber.substring(0,1).equals("T")){
                        LevelImage[i][j] = new Image("sample/Picture/Fields/Traps/" + FieldNumber + ".png");
                        }
                }

                if (fieldslevel[11].length() != 0) {
                    FieldNumber = fieldslevel[11].substring(0, fieldslevel[11].indexOf("|"));
                    fieldslevel[11] = fieldslevel[11].substring(fieldslevel[11].indexOf("|") + 1);
                    if (FieldNumber.substring(0,1).equals("1")){
                        Way[i][j] = "Green";
                        } else if (FieldNumber.substring(0,1).equals("0")){
                        Way[i][j] = "Red";
                        } else if (FieldNumber.substring(0,1).equals("2")){
                        Way[i][j] = "Blue";
                        }
                }


            }

        }

                LoadLevel = true;
                Main.window.setScene(new Scene(FXMLLoader.load(Controller.class.getResource("LevelEditor.fxml"))));

    }

    public void LevelEditorLoadLevelComponents(String[] fieldslevel){
        ScrollAnchor.setPrefWidth(Double.parseDouble(fieldslevel[6]));
        ScrollAnchor.setPrefHeight(Double.parseDouble(fieldslevel[7]));
        BAGH.setLayoutX(Double.parseDouble(fieldslevel[4]));
        BRGH.setLayoutX(Double.parseDouble(fieldslevel[4])+39);
        BAGV.setLayoutY(Double.parseDouble(fieldslevel[5]));
        BRGV.setLayoutY(Double.parseDouble(fieldslevel[5])+39);
        HGrid.setPrefWidth(Double.parseDouble(fieldslevel[4]));
        HGrid.setPrefHeight(Double.parseDouble(fieldslevel[5]));
        Hero.setTranslateX(Double.parseDouble(fieldslevel[8]));
        Hero.setTranslateY(Double.parseDouble(fieldslevel[9]));
    }

    public void GameLevelLoad(String operation) throws IOException {
            String FieldNumber;
            FileReader file = null;
            fieldslevel = new String[12];
            String line;
            int NumberString = 0;

            boolean userCombo = true;

            if (operation.equals("User")){
                if (LevelUserComboBox.getValue() == null){
                    AlertBox.display("Внимание","Выберите уровень который хотите загрузить","ОК");
                    userCombo = false;
                } else {
                    file = new FileReader("src/sample/Level/PlayersLevels/" + LevelUserComboBox.getValue().toString() + ".txt");
                    LoadLevelNumber = "Null";
                }
            } else if (operation.equals("Game")){
                file = new FileReader("src/sample/Level/Gamelevels/Level " + LoadLevelNumber + ".txt");
            } else if (operation.equals("Test")){
                file = new FileReader("src/sample/Level/PlayersLevels/" + LevelName + ".txt");
            }

            if (userCombo) {

                BufferedReader buffread = new BufferedReader(file);

                while ((line = buffread.readLine()) != null) {
                    NumberString++;
                    switch (NumberString) {
                        case 1:
                            fieldslevel[0] = line;
                            break;
                        case 4:
                            fieldslevel[1] = line;
                            break;
                        case 6:
                            fieldslevel[2] = line;
                            break;
                        case 8:
                            fieldslevel[3] = line;
                            break;
                        case 11:
                            fieldslevel[4] = line;
                            break;
                        case 13:
                            fieldslevel[5] = line;
                            break;
                        case 15:
                            fieldslevel[6] = line;
                            break;
                        case 16:
                            fieldslevel[7] = line;
                            break;
                        case 20:
                            fieldslevel[8] = line;
                            break;
                        case 22:
                            fieldslevel[9] = line;
                            break;
                        case 25:
                            fieldslevel[10] = line;
                            break;
                        case 27:
                            fieldslevel[11] = line;
                            break;
                    }
                }

                if (NumberString == 27) {
                    numHorizontal = Integer.parseInt(fieldslevel[1]);
                    numVertical = Integer.parseInt(fieldslevel[2]);
                    LevelImage = new Image[100][100];

                    for (int i = 0; i < numHorizontal; i++) {
                        for (int j = 0; j < numVertical; j++) {
                            if (fieldslevel[3].length() != 0) {
                                FieldNumber = fieldslevel[3].substring(0, fieldslevel[3].indexOf("|"));
                                fieldslevel[3] = fieldslevel[3].substring(fieldslevel[3].indexOf("|") + 1);
                                if (FieldNumber.substring(0, 1).equals("F")) {
                                    LevelImage[i][j] = new Image("sample/Picture/Fields/Floor/" + FieldNumber + ".png");
                                } else if (FieldNumber.substring(0, 1).equals("W")) {
                                    LevelImage[i][j] = new Image("sample/Picture/Fields/Wall/" + FieldNumber + ".png");
                                } else if (FieldNumber.substring(0, 1).equals("T")) {
                                    LevelImage[i][j] = new Image("sample/Picture/Fields/Traps/" + FieldNumber + ".png");
                                }
                            }

                            if (fieldslevel[11].length() != 0) {
                                FieldNumber = fieldslevel[11].substring(0, fieldslevel[11].indexOf("|"));
                                fieldslevel[11] = fieldslevel[11].substring(fieldslevel[11].indexOf("|") + 1);
                                if (FieldNumber.substring(0, 1).equals("1")) {
                                    Way[i][j] = "Green";
                                } else if (FieldNumber.substring(0, 1).equals("0")) {
                                    Way[i][j] = "Red";
                                } else if (FieldNumber.substring(0, 1).equals("2")) {
                                    Way[i][j] = "Blue";
                                }
                            }

                        }
                    }

                    LevelComplete = Boolean.parseBoolean(fieldslevel[10]);
                    LoadLevel = true;
                    Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("GameLevel.fxml"))));

                } else {
                    AlertBox.display("Ошибка", "Выбранный уровень поврежден или не является уровнем данной игры", "ОК");
                }

                buffread.close();
            }
    }

    public void GameLevelComponents(String[] fieldslevel){
        ScrollAnchor.setPrefWidth(Double.parseDouble(fieldslevel[6]));
        ScrollAnchor.setPrefHeight(Double.parseDouble(fieldslevel[7]));
        HGrid.setPrefWidth(Double.parseDouble(fieldslevel[4]));
        HGrid.setPrefHeight(Double.parseDouble(fieldslevel[5]));
        Hero.setTranslateX(Double.parseDouble(fieldslevel[8]));
        Hero.setTranslateY(Double.parseDouble(fieldslevel[9]));
        Hero.setRotate(0);
    }

    public void HeroDragged(MouseEvent event) {
        if (LevelEditor) {
            Hero.setTranslateX(HeroX + event.getSceneX());
            Hero.setTranslateY(HeroY + event.getSceneY());
        }
    }

    public void HeroReleased(MouseEvent event) {
        if (LevelEditor){
            if (Hero.getTranslateX() > HGrid.getPrefWidth() - 75 || Hero.getTranslateX() < -25 || Hero.getTranslateY() > HGrid.getPrefHeight() - 75 || Hero.getTranslateY() < -25) {
                Hero.setTranslateX(HeroOldX);
                Hero.setTranslateY(HeroOldY);
            } else {
                Hero.setTranslateX(((int) Math.round(Hero.getTranslateX() / 100)) * 100);
                Hero.setTranslateY(((int) Math.round(Hero.getTranslateY() / 100)) * 100);

            }
    }
}

    public void HeroPressed(MouseEvent event) {
        if (LevelEditor) {
            HeroOldX = Hero.getTranslateX();
            HeroOldY = Hero.getTranslateY();
            HeroX = Hero.getTranslateX() - event.getSceneX();
            HeroY = Hero.getTranslateY() - event.getSceneY();
        }
    }

    public void TestinglevelButton(ActionEvent actionEvent) throws IOException {
        LevelComplete = true;
        for (int i = 0; i < numHorizontal; i++){
            for (int j = 0; j < numVertical; j++){
                if (FilenameUtils.getBaseName(LevelImage[i][j].getUrl()).equals("0")){
                    LevelComplete = false;
                }
            }
        }

        if (LevelComplete){
            SaveLevel();
            GameLevelLoad("Test");
            TestLevel = true;
        } else {
            AlertBox.display("Внимание!","Для тестирования уровня заполните все пустые ячейки","ОК");
        }
    }

    public void BlockTypeAction(ActionEvent actionEvent) {
        if (BlockType.getValue().equals("Пол")){
            FieldsImage = new Image[259];
            URLFieldType = "sample/Picture/Fields/Floor/F_";
            VBoxField.getChildren().clear();
            FieldMenu();
        }else if (BlockType.getValue().equals("Стены")){
            FieldsImage = new Image[213];
            URLFieldType = "sample/Picture/Fields/Wall/W_";
            VBoxField.getChildren().clear();
            FieldMenu();
        }else if (BlockType.getValue().equals("Ловушки")){
            FieldsImage = new Image[2];
            URLFieldType = "sample/Picture/Fields/Traps/T_";
            VBoxField.getChildren().clear();
            FieldMenu();
        }
    }

    public void ChangeView(ActionEvent actionEvent) {
        if (ViewBut.getText().equals("Вид: стандартный")){
            ViewBut.setText("Вид: метки");
            RefreshGridMark();
        } else {
            ViewBut.setText("Вид: стандартный");
            RefreshGridImage();
        }
    }

    public void Play(ActionEvent actionEvent) throws IOException {
        Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("GameStart.fxml"))));
    }

    public void Level(MouseEvent event) throws IOException {
        LoadLevelNumber = "1";
        GameLevelLoad("Game");
    }

    public void ChangeViewGame(ActionEvent actionEvent) {
        if (ViewGame.getValue().equals("Вид: стандартный")){
            RefreshGridImage();
            ScrollGame.setVisible(true);
            AreaCode.setVisible(false);
            AreaCodeInfo.setVisible(false);
        } else if (ViewGame.getValue().equals("Вид: метки")){
            RefreshGridMark();
            ScrollGame.setVisible(true);
            AreaCode.setVisible(false);
            AreaCodeInfo.setVisible(false);
        } else if (ViewGame.getValue().equals("Вид: код")){
            ScrollGame.setVisible(false);
            AreaCode.setVisible(true);
            AreaCodeInfo.setVisible(true);
        }

    }

    public void UserCompilation(){
        int QuotesNumber = 0;
        int IfNumber = 0;
        int Error = 0;

        VariablesPlayer = new ArrayList<ArrayList<String>>();
        AreaCodeInfo.clear();

        String UserCode[] = AreaCode.getText().split("\\n");
        String Line;

        for(int i = 0; i < UserCode.length; i++){

            Line = UserCode[i];
            Line = Line.replaceAll("\\s","");

            if ( "Повторить".equals(InitializationText(Line)) ){
                QuotesNumber++;

                if (isInteger(Line.substring(Line.indexOf("(")+1,Line.lastIndexOf(")")))){
                }else if (FindArrayVariables(Line.substring(Line.indexOf("(")+1,Line.lastIndexOf(")")))){
                    if (isInteger(VariablesPlayer.get(GetArrayVariables(Line.substring(Line.indexOf("(")+1,Line.lastIndexOf(")")),i)).get(1))){
                    } else {
                        AreaCodeInfo.setText(AreaCodeInfo.getText()+"\n"+"Строка " + i + " ошибка: операция 'Повторить(" + Line.substring(Line.indexOf("(")+1,Line.lastIndexOf(")")) + ")раз{' введено не целочисленное  значение");
                        Error++;
                    }
                } else {
                    AreaCodeInfo.setText(AreaCodeInfo.getText()+"\n"+"Строка " + i + " ошибка: операция 'Повторить(" + Line.substring(Line.indexOf("(")+1,Line.lastIndexOf(")")) + ")раз{' введено не целочисленное значение");
                    Error++;
                }

                int QNE = 1;
                for (int r = i+1; r < UserCode.length; r++){
                    String TE = UserCode[r];
                    TE = TE.replaceAll("\\s","");
                    if ( "Повторить".equals(InitializationText(TE)) ){
                        QNE++;
                    } else if ("Если".equals(InitializationText(TE))){
                        QNE++;
                    } else if ( "}".equals(TE)){
                           QNE--;
                           if (QNE == 0) {
                               break;
                           }
                    }
                }

                if (QNE != 0){
                    Error++;
                }

            } else if ( "Если".equals(InitializationText(Line))){
                QuotesNumber++;
                IfNumber++;

                if (conditions.contains(Line.substring(Line.indexOf("(")+1,Line.lastIndexOf(")")))){
                }else{
                    AreaCodeInfo.setText(AreaCodeInfo.getText()+"\n"+"Строка " + i + " ошибка: операция 'Если(" + Line.substring(Line.indexOf("(")+1,Line.lastIndexOf(")")) + ")тогда{' введено не верное условие");
                    Error++;
                }

                int QNE = 1;
                int QNIF = 1;
                for (int r = i+1; r < UserCode.length; r++){
                    String TE = UserCode[r];
                    TE = TE.replaceAll("\\s","");
                    if ( "Повторить".equals(InitializationText(TE)) ){
                        QNE++;
                    } else if ("Если".equals(InitializationText(TE))){
                        QNE++;
                        QNIF++;
                    } else if ("Иначе".equals(InitializationText(TE))){
                        QNIF--;
                        if ( (QNIF < 0) || ((QNE != 1)&&(QNIF == 0))) {
                            Error++;
                            break;
                        }
                    } else if ( "}".equals(TE)){
                        QNE--;
                        if (QNE == 0) {
                            break;
                        }
                    }
                }

                if (QNE != 0){
                    Error++;
                }

            } else if ( "Иначе".equals(InitializationText(Line)) ){
                IfNumber--;
            } else if ("Действие".equals(InitializationText(Line))){
                if (Action.contains(Line.substring(Line.indexOf("(")+1,Line.lastIndexOf(")")))){
                }else{
                    Error++;
                    AreaCodeInfo.setText(AreaCodeInfo.getText()+"\n"+"Строка " + i + " ошибка: операция 'Действие(" + Line.substring(Line.indexOf("(")+1,Line.lastIndexOf(")")) + ");' введено не верное действие");
                }
            } else if ( "Переменная".equals(InitializationText(Line)) ){

                boolean Rep = false;
                ArrayList<String> Vari = new ArrayList<String>();
                Vari.add(Line.substring(Line.indexOf("я")+1,Line.lastIndexOf(";")));
                Vari.add("Null");

                if (VariablesPlayer.size() == 0){
                    VariablesPlayer.add(Vari);
                }else{
                    loop:
                    for (ArrayList<String> Res : VariablesPlayer ){
                        if (Res.get(0).equals(Vari.get(0))) {
                            Error++;
                            AreaCodeInfo.setText(AreaCodeInfo.getText()+"\n"+"Строка " + i + " ошибка: операция 'Перемення(" + Res.get(0) + ";' переменная с таким именем уже существует");
                            Rep = true;
                            break loop;
                        }
                    }
                    if (!Rep){
                        VariablesPlayer.add(Vari);
                    }
                }
            } else if ( "Присвоить".equals(InitializationText(Line)) ){
                AddArrayVariables(Line.substring(Line.indexOf("ь")+1,Line.indexOf("=")),Line.substring(Line.indexOf("=")+1,Line.indexOf(";")),i);
            } else if (Line.equals("}")){
                QuotesNumber--;
            } else if (!Line.isEmpty()) {
                Error++;
                AreaCodeInfo.setText(AreaCodeInfo.getText()+"\n"+"Строка " + i + " ошибка: операция введена не верно");
            }
        }

        if (QuotesNumber != 0){
            Error++;
            AreaCodeInfo.setText(AreaCodeInfo.getText()+"\n"+"Ошибка: поставлены не все фигурные скобки");
        }

        if (IfNumber < 0){
            Error++;
            AreaCodeInfo.setText(AreaCodeInfo.getText()+"\n"+"Ошибка: не верное количество '}иначе{'");
        }

        if (Error == 0){
            StartMovingCompilation();
        } else {
            BStSt.setText("Запустить");
        }
    }

    public void StartMovingCompilation(){

        VariablesPlayer = new ArrayList<ArrayList<String>>();
        String UserCode[] = AreaCode.getText().split("\\n");
        StopRunCode = false;
        BlockCodePerformance(UserCode);
        BStSt.setText("Запустить");
        if (!StopRunCode){
            Hero.setTranslateX(Double.parseDouble(fieldslevel[8]));
            Hero.setTranslateY(Double.parseDouble(fieldslevel[9]));
            Hero.setRotate(0);
        }

    }

    public int BlockFor(int iteration,String BlockText[]){
        int end = 0;
        int number = 0;

        int QNE = 1;
        for (int r = 1; r < BlockText.length; r++){
            String TE = BlockText[r];
            TE = TE.replaceAll("\\s","");
            if ( "Повторить".equals(InitializationText(TE)) ){
                QNE++;
            } else if ("Если".equals(InitializationText(TE))){
                QNE++;
            } else if ( "}".equals(TE)){
                QNE--;
                if (QNE == 0) {
                    end = r-1;
                    break;
                }
            }
                number++;
        }

        if (number != 0) {
            String BlockCode[] = new String[number];

            for (int s = 0; s < BlockCode.length; s++) {
                BlockCode[s] = BlockText[s+1];
            }
            for (int i = 1; i <= iteration;i++){
               BlockCodePerformance(BlockCode);
            }
        }

        return end+1;

    }

    public int BlockIf(String Condition, String BlockText[]){

        int startelse = 0;
        int end = 0;

        int numberif = 0;
        int numberelse = 0;
        boolean elsecheck = true;

        int QNE = 1;
        for (int r = 1; r < BlockText.length; r++){
            String TE = BlockText[r];
            TE = TE.replaceAll("\\s","");
            if ("Повторить".equals(InitializationText(TE))){
                QNE++;
            } else if ("Если".equals(InitializationText(TE))){
                QNE++;
            } else if ("Иначе".equals(InitializationText(TE))){
                if (QNE == 1){
                    elsecheck = false;
                    startelse = r+1;
                }
            } else if ( "}".equals(TE)){
                QNE--;
                if (QNE == 0) {
                    end = r-1;
                    break;
                }
            }
                if (elsecheck){
                    numberif++;
                }else{
                    numberelse++;
                }
        }

        if (numberelse >= 1){
            numberelse--;
        }

        if (PlayerLocation(Condition)){
            if (numberif != 0){

                String BlockCodeif[] = new String[numberif];
                for (int s = 0; s < BlockCodeif.length; s++) {
                    BlockCodeif[s] = BlockText[s+1];
                }

                BlockCodePerformance(BlockCodeif);
            }
        } else {
            if (numberelse != 0){
                String BlockCodeelse[] = new String[numberelse];
                for (int s = 0; s < BlockCodeelse.length; s++) {
                    BlockCodeelse[s] = BlockText[startelse+s];
                }
                BlockCodePerformance(BlockCodeelse);
            }
        }

        return end+1;

    }

    public void BlockAction(String text){

        //"Прыжок","ПоворотНаЛево","ПоворотНаПраво","ИдтиВперед","ИдтиНаЛево","ИдтиНаПраво","ИдтиНазад","Активировать"

                switch (text) {
                    case "ПоворотНаЛево":
                            Hero.setRotate(Hero.getRotate()-90);
                            if (Hero.getRotate() < 0){
                                Hero.setRotate(270);
                            }
                        break;
                    case "ПоворотНаПраво":
                            Hero.setRotate(Hero.getRotate()+90);
                            if (Hero.getRotate() > 270){
                                Hero.setRotate(0);
                            }
                        break;
                    case "ИдтиВперед":
                        if (!PlayerLocation("СпередиСтена")){
                            switch ((int) Hero.getRotate()){
                                case 0:
                                    Hero.setTranslateY(Hero.getTranslateY()+100);
                                    break;
                                case 90:
                                    Hero.setTranslateX(Hero.getTranslateX()-100);
                                    break;
                                case 180:
                                    Hero.setTranslateY(Hero.getTranslateY()-100);
                                    break;
                                case 270:
                                    Hero.setTranslateX(Hero.getTranslateX()+100);
                                    break;
                            }
                            PlayerTrigerTraps();
                        }
                        break;
                    case "ИдтиНаЛево":
                        if (!PlayerLocation("СлеваСтена")){
                            switch ((int) Hero.getRotate()){
                                case 0:
                                    Hero.setTranslateX(Hero.getTranslateX()+100);
                                    break;
                                case 90:
                                    Hero.setTranslateY(Hero.getTranslateY()+100);
                                    break;
                                case 180:
                                    Hero.setTranslateX(Hero.getTranslateX()-100);
                                    break;
                                case 270:
                                    Hero.setTranslateY(Hero.getTranslateY()-100);
                                    break;
                            }
                            PlayerTrigerTraps();
                        }
                        break;
                    case "ИдтиНаПраво":
                        if (!PlayerLocation("СправаСтена")){
                            switch ((int) Hero.getRotate()){
                                case 0:
                                    Hero.setTranslateX(Hero.getTranslateX()-100);
                                    break;
                                case 90:
                                    Hero.setTranslateY(Hero.getTranslateY()-100);
                                    break;
                                case 180:
                                    Hero.setTranslateX(Hero.getTranslateX()+100);
                                    break;
                                case 270:
                                    Hero.setTranslateY(Hero.getTranslateY()+100);
                                    break;
                            }
                            PlayerTrigerTraps();
                        }
                        break;
                    case "ИдтиНазад":
                        if (!PlayerLocation("СзадиСтена")){
                            switch ((int) Hero.getRotate()){
                                case 0:
                                    Hero.setTranslateY(Hero.getTranslateY()-100);
                                    break;
                                case 90:
                                    Hero.setTranslateX(Hero.getTranslateX()+100);
                                    break;
                                case 180:
                                    Hero.setTranslateY(Hero.getTranslateY()+100);
                                    break;
                                case 270:
                                    Hero.setTranslateX(Hero.getTranslateX()-100);
                                    break;
                            }
                            PlayerTrigerTraps();
                        }
                        break;
                    case "Прыжок":
                        switch ((int) Hero.getRotate()){
                            case 180:
                                if (Hero.getTranslateY() - 200 < 0){
                                    if (!(Hero.getTranslateY() - 100 < 0)) {
                                        if (!FilenameUtils.getName(LevelImage[(int) (Hero.getTranslateX() / 100)][(int) ((Hero.getTranslateY() - 100) / 100)].getUrl()).substring(0, 1).equals("W")){
                                            Hero.setTranslateY(Hero.getTranslateY() - 100);
                                            PlayerTrigerTraps();
                                        }
                                    }
                                }else if (!FilenameUtils.getName(LevelImage[(int) (Hero.getTranslateX() / 100)][(int) ((Hero.getTranslateY() - 200) / 100)].getUrl()).substring(0, 1).equals("W")){
                                    Hero.setTranslateY(Hero.getTranslateY() - 200);
                                    PlayerTrigerTraps();
                                }else if (!FilenameUtils.getName(LevelImage[(int) (Hero.getTranslateX() / 100)][(int) ((Hero.getTranslateY() - 100) / 100)].getUrl()).substring(0, 1).equals("W")){
                                    Hero.setTranslateY(Hero.getTranslateY() - 100);
                                    PlayerTrigerTraps();
                                }
                                break;
                            case 90:
                                if (Hero.getTranslateX() - 200 < 0){
                                    if (!(Hero.getTranslateX() - 100 < 0)) {
                                        if (!FilenameUtils.getName(LevelImage[(int) ((Hero.getTranslateX() - 100) / 100)][(int) (Hero.getTranslateY() / 100)].getUrl()).substring(0, 1).equals("W")){
                                            Hero.setTranslateX(Hero.getTranslateX() - 100);
                                            PlayerTrigerTraps();
                                        }
                                    }
                                }else if (!FilenameUtils.getName(LevelImage[(int) ((Hero.getTranslateX() - 200) / 100)][(int) (Hero.getTranslateY() / 100)].getUrl()).substring(0, 1).equals("W")){
                                    Hero.setTranslateX(Hero.getTranslateX() - 200);
                                    PlayerTrigerTraps();
                                }else if (!FilenameUtils.getName(LevelImage[(int) ((Hero.getTranslateX() - 100) / 100)][(int) (Hero.getTranslateY() / 100)].getUrl()).substring(0, 1).equals("W")){
                                    Hero.setTranslateX(Hero.getTranslateX() - 100);
                                    PlayerTrigerTraps();
                                }
                                break;
                            case 0:
                                if (Hero.getTranslateY() + 200 > HGrid.getPrefHeight()-100){
                                    if (!(Hero.getTranslateY() + 100 > HGrid.getPrefHeight()-100)) {
                                        if (!FilenameUtils.getName(LevelImage[(int) (Hero.getTranslateX() / 100)][(int) ((Hero.getTranslateY() + 100) / 100)].getUrl()).substring(0, 1).equals("W")){
                                            Hero.setTranslateY(Hero.getTranslateY() + 100);
                                            PlayerTrigerTraps();
                                        }
                                    }
                                }else if (!FilenameUtils.getName(LevelImage[(int) (Hero.getTranslateX() / 100)][(int) ((Hero.getTranslateY() + 200) / 100)].getUrl()).substring(0, 1).equals("W")){
                                    Hero.setTranslateY(Hero.getTranslateY() + 200);
                                    PlayerTrigerTraps();
                                }else if (!FilenameUtils.getName(LevelImage[(int) (Hero.getTranslateX() / 100)][(int) ((Hero.getTranslateY() + 100) / 100)].getUrl()).substring(0, 1).equals("W")){
                                    Hero.setTranslateY(Hero.getTranslateY() + 100);
                                    PlayerTrigerTraps();
                                }
                                break;
                            case 270:
                                if (Hero.getTranslateX() + 200 > HGrid.getPrefWidth()-100){
                                    if (!(Hero.getTranslateX() + 100 > HGrid.getPrefWidth()-100)) {
                                        if (!FilenameUtils.getName(LevelImage[(int) ((Hero.getTranslateX() + 100) / 100)][(int) (Hero.getTranslateY() / 100)].getUrl()).substring(0, 1).equals("W")){
                                            Hero.setTranslateX(Hero.getTranslateX() + 100);
                                            PlayerTrigerTraps();
                                        }
                                    }
                                }else if (!FilenameUtils.getName(LevelImage[(int) ((Hero.getTranslateX() + 200) / 100)][(int) (Hero.getTranslateY() / 100)].getUrl()).substring(0, 1).equals("W")){
                                    Hero.setTranslateX(Hero.getTranslateX() + 200);
                                    PlayerTrigerTraps();
                                }else if (!FilenameUtils.getName(LevelImage[(int) ((Hero.getTranslateX() + 100) / 100)][(int) (Hero.getTranslateY() / 100)].getUrl()).substring(0, 1).equals("W")){
                                    Hero.setTranslateX(Hero.getTranslateX() + 100);
                                    PlayerTrigerTraps();
                                }
                                break;
                        }
                        break;
                    case "Активировать":

                        break;
                }


    }

    public void BlockCodePerformance(String BlockCode[]){

        String Line;

        StopCode:
        for (int i = 0;i < BlockCode.length;i++){

            if (StopRunCode){
                break StopCode;
            }

            Line = BlockCode[i];
            Line = Line.replaceAll("\\s", "");
            if ("Повторить".equals(InitializationText(Line))) {
                int iteration = 0;

                if (isInteger(Line.substring(Line.indexOf("(") + 1, Line.lastIndexOf(")")))) {
                    iteration = Integer.parseInt(Line.substring(Line.indexOf("(") + 1, Line.lastIndexOf(")")));
                } else if (FindArrayVariables(Line.substring(Line.indexOf("(") + 1, Line.lastIndexOf(")")))) {
                    iteration = Integer.parseInt(VariablesPlayer.get(GetArrayVariables(Line.substring(Line.indexOf("(") + 1, Line.lastIndexOf(")")), i)).get(1));
                }

                String BlockText[] = new String[BlockCode.length-i];
                for (int s = 0;s < BlockText.length;s++){
                    BlockText[s] = BlockCode[s+i];
                }

                i = i + BlockFor(iteration,BlockText);

            } else if ("Если".equals(InitializationText(Line))) {

                String BlockText[] = new String[BlockCode.length-i];
                for (int s = 0;s < BlockText.length;s++){
                    BlockText[s] = BlockCode[s+i];
                }
                i = i + BlockIf(Line.substring(Line.indexOf("(")+1,Line.lastIndexOf(")")),BlockText);

            } else if ("Действие".equals(InitializationText(Line))) {
                new Thread(new Runnable(){
                    public void run(){
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                BlockAction(Line.substring(Line.indexOf("(")+1,Line.indexOf(")")));
            } else if ("Переменная".equals(InitializationText(Line))) {
                ArrayList<String> Vari = new ArrayList<String>();
                Vari.add(Line.substring(Line.indexOf("я") + 1, Line.lastIndexOf(";")));
                Vari.add("Null");
                VariablesPlayer.add(Vari);
            } else if ("Присвоить".equals(InitializationText(Line))) {
                AddArrayVariables(Line.substring(Line.indexOf("ь") + 1, Line.indexOf("=")), Line.substring(Line.indexOf("=") + 1, Line.indexOf(";")), i);
            }

        }
    }

    public boolean PlayerLocation(String conditions){

        switch (conditions){
            case "СлеваСтена":
                switch ((int) Hero.getRotate()){
                    case 0:
                        return ULDR("Right");
                    case 90:
                        return ULDR("Down");
                    case 180:
                        return ULDR("Left");
                    case 270:
                        return ULDR("Up");
                }
            case "СправаСтена":
                switch ((int) Hero.getRotate()){
                    case 0:
                        return ULDR("Left");
                    case 90:
                        return ULDR("Up");
                    case 180:
                        return ULDR("Right");
                    case 270:
                        return ULDR("Down");
                }
            case "СпередиСтена":
                switch ((int) Hero.getRotate()){
                    case 0:
                        return ULDR("Down");
                    case 90:
                        return ULDR("Left");
                    case 180:
                        return ULDR("Up");
                    case 270:
                        return ULDR("Right");
                }
            case "СзадиСтена":
                switch ((int) Hero.getRotate()){
                    case 0:
                        return ULDR("Up");
                    case 90:
                        return ULDR("Right");
                    case 180:
                        return ULDR("Down");
                    case 270:
                        return ULDR("Left");
                }
        }
        return false;
    }

    public void PlayerTrigerTraps(){

        switch (FilenameUtils.getBaseName(LevelImage[(int) (Hero.getTranslateX() / 100)][(int) (Hero.getTranslateY() / 100)].getUrl())){
            case "T_1":
                StopRunCode = true;
                AlertBox.WinLose("Победа","Уровень пройден !","ОК");

                break;
        }

    }

    public boolean ULDR(String text){

        switch (text){
            case "Up":
                if (Hero.getTranslateY() - 100 < 0){
                    return true;
                }else
                    return FilenameUtils.getName(LevelImage[(int) (Hero.getTranslateX() / 100)][(int) ((Hero.getTranslateY() - 100) / 100)].getUrl()).substring(0, 1).equals("W");
            case "Left":
                if (Hero.getTranslateX() - 100 < 0){
                    return true;
                }else
                    return FilenameUtils.getName(LevelImage[(int) ((Hero.getTranslateX() - 100) / 100)][(int) (Hero.getTranslateY() / 100)].getUrl()).substring(0, 1).equals("W");
            case "Down":
                if (Hero.getTranslateY() + 100 > HGrid.getPrefHeight()-100){
                    return true;
                }else
                    return FilenameUtils.getName(LevelImage[(int) (Hero.getTranslateX() / 100)][(int) ((Hero.getTranslateY() + 100) / 100)].getUrl()).substring(0, 1).equals("W");
            case "Right":
                if (Hero.getTranslateX() + 100 > HGrid.getPrefWidth()-100){
                    return true;
                }else
                    return FilenameUtils.getName(LevelImage[(int) ((Hero.getTranslateX() + 100) / 100)][(int) (Hero.getTranslateY() / 100)].getUrl()).substring(0, 1).equals("W");
        }

        return true;

    }

    public boolean FindArrayVariables(String text){
        boolean FindVar = false;
        loop:
        for (ArrayList<String> Res : VariablesPlayer ){
            if (Res.get(0).equals(text)) {
                FindVar = true;
                break loop;
            }
        }
        return FindVar;
    }

    public void AddArrayVariables(String text,String addvar, int linenum){
        boolean FindVar = false;
        int number = 0;

        loop:
        for (ArrayList<String> Res : VariablesPlayer ){
            if (Res.get(0).equals(text)) {
                FindVar = true;
                VariablesPlayer.get(number).set(1,addvar);
                break loop;
            }
            number ++;
        }

        if (!FindVar){
            AreaCodeInfo.setText(AreaCodeInfo.getText()+"\n"+"Строка " + linenum + " ошибка: операция 'Перемення(" + text + ";' переменной с таким именем не существует");
        }
    }

    public int GetArrayVariables(String text, int linenum){

        boolean FindVar = false;
        int number = 0;

        loop:
        for (ArrayList<String> Res : VariablesPlayer ){

            if (Res.get(0).equals(text)) {
                FindVar = true;
                break loop;
            }
            number ++;
        }

        if (!FindVar){
            AreaCodeInfo.setText(AreaCodeInfo.getText()+"\n"+"Строка " + linenum + " ошибка: операция 'Перемення(" + text + ";' переменной с таким именем не существует");
        }

        return number;
    }

    public String InitializationText(String Line){

            if ( (Line.startsWith("Повторить(")) && (Line.endsWith(")раз{")) ){
                if ((Line.substring(0,Line.indexOf("(")+1).equals("Повторить(")) && (Line.substring(Line.indexOf(")"),Line.lastIndexOf("{")+1).equals(")раз{")) && (Line.length() == Line.lastIndexOf("{")+1) ) {
                    return "Повторить";
                }else return "";
            } else if ( (Line.startsWith("Если(")) && (Line.endsWith(")тогда{")) ){
                if ( (Line.length() >= 12) && (Line.substring(0,Line.indexOf("(")+1).equals("Если(")) && (Line.substring(Line.indexOf(")"),Line.lastIndexOf("{")+1).equals(")тогда{")) && (Line.length() == Line.lastIndexOf("{")+1) ) {
                    return "Если";
                }else return "";
            } else if (Line.equals("}иначе{")){
                if ( (Line.substring(0,Line.lastIndexOf("{")+1).equals("}иначе{")) && (Line.length() == Line.lastIndexOf("{")+1) ) {
                    return "Иначе";
                }else return "";
            } else if ( (Line.startsWith("Действие(")) && (Line.endsWith(");")) ){
                if ( (Line.substring(0,Line.indexOf("(")+1).equals("Действие(")) && (Line.substring(Line.indexOf(")"),Line.lastIndexOf(";")+1).equals(");")) && (Line.length() == Line.lastIndexOf(";")+1) ) {
                    return "Действие";
                }else return "";
            } else if ( (Line.startsWith("Переменная")) && (Line.endsWith(";")) ){
                if ( (Line.length() > 11) && (Line.substring(0,Line.indexOf("я")+1).equals("Переменная")) && (Line.length() == Line.lastIndexOf(";")+1) ) {
                    return "Переменная";
                }else return "";
            } else if ( (Line.startsWith("Присвоить")) && (Line.endsWith(";")) ){
                if ( (Line.length() >= 12) && (Line.substring(0,Line.indexOf("ь")+1).equals("Присвоить")) && (Line.contains("=")) && (Line.length() == Line.lastIndexOf(";")+1) ) {
                    return "Присвоить";
                }else return "";
            } else {
                return "";
            }

    }

    public void ImageButton(MouseEvent mouseEvent) {
        String eve = mouseEvent.getTarget().toString();
        eve = eve.substring(eve.indexOf("=")+1,eve.indexOf(","));

        switch (eve){
            case "ImagePPP":
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                    ImagePPP.setImage(new Image("sample/Picture/Base/Повторить попытку(on).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                    ImagePPP.setImage(new Image("sample/Picture/Base/Повторить попытку(off).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    ImagePPP.setImage(new Image("sample/Picture/Base/Повторить попытку(click).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    ImagePPP.setImage(new Image("sample/Picture/Base/Повторить попытку(on).png"));
                }
                break;
            case "ImageAR":
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                    ImageAR.setImage(new Image("sample/Picture/Base/Автономный режим(on).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                    ImageAR.setImage(new Image("sample/Picture/Base/Автономный режим(off).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    ImageAR.setImage(new Image("sample/Picture/Base/Автономный режим(click).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    ImageAR.setImage(new Image("sample/Picture/Base/Автономный режим(on).png"));
                }
                break;
            case "ImageAR2":
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                    ImageAR2.setImage(new Image("sample/Picture/Base/Автономный режим(on).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                    ImageAR2.setImage(new Image("sample/Picture/Base/Автономный режим(off).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    ImageAR2.setImage(new Image("sample/Picture/Base/Автономный режим(click).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    ImageAR2.setImage(new Image("sample/Picture/Base/Автономный режим(on).png"));
                }
                break;
            case "ImageA":
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                    ImageA.setImage(new Image("sample/Picture/Base/Авторизация(on).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                    ImageA.setImage(new Image("sample/Picture/Base/Авторизация(off).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    ImageA.setImage(new Image("sample/Picture/Base/Авторизация(click).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    ImageA.setImage(new Image("sample/Picture/Base/Авторизация(on).png"));
                }
                break;
            case "ImageR":
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                    ImageR.setImage(new Image("sample/Picture/Base/Регистрация(on).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                    ImageR.setImage(new Image("sample/Picture/Base/Регистрация(off).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    ImageR.setImage(new Image("sample/Picture/Base/Регистрация(click).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    ImageR.setImage(new Image("sample/Picture/Base/Регистрация(on).png"));
                }
                break;
            case "ImageB":
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                    ImageB.setImage(new Image("sample/Picture/Base/Назад(on).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                    ImageB.setImage(new Image("sample/Picture/Base/Назад(off).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    ImageB.setImage(new Image("sample/Picture/Base/Назад(click).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    ImageB.setImage(new Image("sample/Picture/Base/Назад(on).png"));
                }
                break;
            case "ImageB2":
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                    ImageB2.setImage(new Image("sample/Picture/Base/Назад(on).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                    ImageB2.setImage(new Image("sample/Picture/Base/Назад(off).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    ImageB2.setImage(new Image("sample/Picture/Base/Назад(click).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    ImageB2.setImage(new Image("sample/Picture/Base/Назад(on).png"));
                }
                break;
            case "ImageB3":
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                    ImageB3.setImage(new Image("sample/Picture/Base/Назад(on).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                    ImageB3.setImage(new Image("sample/Picture/Base/Назад(off).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    ImageB3.setImage(new Image("sample/Picture/Base/Назад(click).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    ImageB3.setImage(new Image("sample/Picture/Base/Назад(on).png"));
                }
                break;
            case "ImageZareg":
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                    ImageZareg.setImage(new Image("sample/Picture/Base/Зарегистрироваться(on).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                    ImageZareg.setImage(new Image("sample/Picture/Base/Зарегистрироваться(off).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    ImageZareg.setImage(new Image("sample/Picture/Base/Зарегистрироваться(click).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    ImageZareg.setImage(new Image("sample/Picture/Base/Зарегистрироваться(on).png"));
                }
                break;
            case "ImageAutorize":
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                    ImageAutorize.setImage(new Image("sample/Picture/Base/Авторизоваться(on).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                    ImageAutorize.setImage(new Image("sample/Picture/Base/Авторизоваться(off).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    ImageAutorize.setImage(new Image("sample/Picture/Base/Авторизоваться(click).png"));
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    ImageAutorize.setImage(new Image("sample/Picture/Base/Авторизоваться(on).png"));
                }
                break;
        }
    }

    public void ButtonInformation(ActionEvent actionEvent) {

    }

    public void ButtonStartStop(ActionEvent actionEvent) {
        BStSt.setText("Остановить");
        UserCompilation();
    }

    public void ProfileBack(ActionEvent actionEvent) {
        APProfile.setVisible(false);
        Main.Refresh();
    }

    public void CreateProfile(ActionEvent actionEvent) throws IOException {

        String text = ProfileTextField.getText().replaceAll("\\s","");

        if (text.trim().isEmpty()){
            AlertBox.display("Ошибка","Введите имя профиля.","ОК");
        } else if ((new File("src/sample/Profile/"+text+".txt")).exists()) {
            AlertBox.display("Ошибка","Профиль с таким именем уже существует.","ОК");
        } else if (text.length() > 16) {
            AlertBox.display("Ошибка","Имя профиля не должно быть длиннее 16 символов","ОК");
        }else if(!TextValidation(text)) {
            AlertBox.display("Ошибка","Имя профиля не должно содержать следующих знаков:\\/:*?+\"<>|","ОК");
        }else{
            ProfileName = text;
            PrintWriter writer = new PrintWriter("src/sample/Profile/" + text + ".txt", "UTF-8");
            writer.println("Profile: "+ text);
            writer.println("Level complete:");
            writer.println("0");
            writer.close();
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml"))));
        }

    }

    public void LoadProfile(ActionEvent actionEvent) throws IOException {
        if (ProfileComboBox.getValue() == null){
            AlertBox.display("Внимание","Выберите профиль который хотите загрузить","ОК");
        } else {

            String line;
            String NameLevel = "";
            int NumberString = 0;
            FileReader file = new FileReader("src/sample/Profile/" + ProfileComboBox.getValue().toString() + ".txt");
            BufferedReader buffread = new BufferedReader(file);

            while ((line = buffread.readLine()) != null) {
                NumberString++;
                if (NumberString == 1){
                    NameLevel = line;
                }
            }

            if ((NumberString == 3) || (NameLevel.equals("Profile: "+ ProfileComboBox.getValue().toString()))){
                ProfileName = ProfileComboBox.getValue().toString();
                Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml"))));
            }else{
                AlertBox.display("Ошибка","Выбранный профиль поврежден.","ОК");
            }
            buffread.close();

        }
    }

    public void DeleteProfile(ActionEvent actionEvent) {
        if (ProfileComboBox.getValue() == null){
            AlertBox.display("Внимание","Выберите профиль который хотите удалить","ОК");
        } else {
            File file = new File("src/sample/Profile/" + ProfileComboBox.getValue().toString() + ".txt");
            file.delete();
            ProfileComboBox.getItems().clear();
            AddProfile();
            ProfileComboBox.getSelectionModel().selectFirst();
        }
    }

    public void ProfileWindowOpen(MouseEvent mouseEvent) {
        AP2.setVisible(false);
        AP3.setVisible(false);
        APProfile.setVisible(true);
    }

    public static boolean TextValidation(String text) {
        return text.matches("^[^.\\\\/:.*?+\"<>|]?[^\\\\/:.*?+\"<>|]*")
                && getValidFileName(text).length()>0;
    }

    public static String getValidFileName(String fileName) {
        String newFileName = fileName.replaceAll("^[.\\\\/:.*?+\"<>|]?[\\\\/:.*?+\"<>|]*", "");
        return newFileName;
    }

    public void LoadUserLevel(MouseEvent mouseEvent) throws IOException {
        Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("LevelUserLoad.fxml"))));
    }

    public void LoadLevel(ActionEvent actionEvent) throws IOException {
        GameLevelLoad("User");
    }

    public void PlayImage(MouseEvent mouseEvent) throws IOException {
        Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("GameStart.fxml"))));
    }

    public void FinAut(MouseEvent mouseEvent) {

        LoginAut = LoginTextAut.getText();
        PasAut = PasTextAut.getText();
        ConnectToDataBase.AuthorizChecking();
    }

    public void FinReg(MouseEvent mouseEvent) {
        EmailReg = EmailTextReg.getText();
        LoginReg = LoginTextReg.getText();
        PasReg = PasTextReg.getText();
        PPasReg = PPasTextReg.getText();
        ConnectToDataBase.RegistrChecking();
    }
}