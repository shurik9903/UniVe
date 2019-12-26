package sample;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.io.IOException;
import java.sql.*;

public class ConnectToDataBase {

    public static Connection ConnectionToDateBase() throws Exception, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver").getResource("sample/mysql-connector-java-5.1.46-bin.jar");
        String UserName = "root";
        String Password = "root";
        String URL = "jdbc:mysql://localhost:3306/GameDB";
        try{
            Connection connection = DriverManager.getConnection(URL, UserName, Password);
            Main.DBConnection = true;
            return connection;
        } catch (Exception e) {
            Main.DBConnection = false;
        }
        return null;
    }

    public static void RegistrChecking (){
        if ((Controller.EmailReg.trim().length() <= 0) || (Controller.LoginReg.trim().length() <= 0) || (Controller.PasReg.trim().length() <= 0) || (Controller.PPasReg.trim().length() <= 0)){
            AlertBox.display("Ошибка !","Вы не заполнили все поля.","ОК");
        } else if ((!Controller.PasReg.equals(Controller.PPasReg))){
            AlertBox.display("Ошибка !","Пароли не совпадают.","OK");
        } else {
            try {
                RegistrInsertionDB();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void AuthorizChecking (){
        if ((Controller.LoginAut.trim().length() <= 0) || (Controller.PasAut.trim().length() <= 0)){
            AlertBox.display("Ошибка !","Вы не заполнили все поля.","ОК");
        } else {
            try {
                AuthorizInsertionDB();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void RegistrInsertionDB() throws SQLException, ClassNotFoundException{
        try {
            Connection con = ConnectionToDateBase();
            Statement create = con.createStatement();
            create.executeUpdate("CREATE TABLE IF NOT EXISTS players (login CHAR(15) NOT NULL, pass CHAR(30) NOT NULL, email CHAR(30) NOT NULL, PRIMARY KEY (login))");
            ResultSet result = create.executeQuery("select * from players where login = '" + Controller.LoginReg + "'");
            if (result.next()){
                AlertBox.display("Сообщение","Введенный логин уже используется, введите другой логин.","ОК");
            }else {
                result = create.executeQuery("select * from players where email = '" + Controller.EmailReg + "'");
                if (result.next()){
                    AlertBox.display("Сообщение","Введенная почта уже используется, введите другую почту.","ОК");
                } else {
                    create.executeUpdate("INSERT INTO players (login,email,pass) VALUES ('" + Controller.LoginReg + "','" + Controller.EmailReg + "','" + Controller.PasReg + "')");
                    AlertBox.display("Сообщение","Вы успешно зарегистрировались","ОК");
                    Main.Refresh();
                }
            }
        } catch (Exception e) {
            Main.DBConnection = false;
            Main.Refresh();
        }
    }

    public static void AuthorizInsertionDB() throws SQLException, ClassNotFoundException {
        try {
            Connection con = ConnectionToDateBase();
            Statement create = con.createStatement();
            ResultSet result = create.executeQuery("select login,pass from players where login = '" + Controller.LoginAut + "' and pass = '" + Controller.PasAut + "'");
            if (!result.next()){
                AlertBox.display("Сообщение","Введен неверный логин или пароль.","ОК");
            } else {
                Main.DBConnection = false;
                Controller.ProfileName = Controller.LoginAut;
                Main.window.setScene(new Scene(FXMLLoader.load(ConnectToDataBase.class.getResource("MainMenu.fxml"))));
            }
        } catch (Exception e) {
            Main.DBConnection = false;
            Main.Refresh();
        }
    }


}