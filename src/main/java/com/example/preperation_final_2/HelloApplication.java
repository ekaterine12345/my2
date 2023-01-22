package com.example.preperation_final_2;

import connection.StudentUtil;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        StudentUtil.createTable();
        StudentUtil.GetMapData();

        PieChart pieChart = new PieChart();
        pieChart.setData(StudentUtil.getData());
        pieChart.setTranslateX(100);
        pieChart.setTranslateY(100);

        Label label1 = new Label("name");
        Label label2 = new Label("lastname");
        Label label3 = new Label("age");
        Label label4 = new Label("faculty");
        Label label5 = new Label("year");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();

        Button button = new Button("Add Item");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = textField1.getText();
                String lastname = textField2.getText();
                int age = Integer.parseInt(textField3.getText());
                String faculty = textField4.getText();
                int year = Integer.parseInt(textField5.getText());
                Student myStudent = new Student(name, lastname, age, faculty, year);
                try {
                    StudentUtil.insertItem(myStudent);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        GridPane root = new GridPane();
        root.addRow(0, label1, textField1);
        root.addRow(1, label2, textField2);
        root.addRow(2, label3, textField3);
        root.addRow(3, label4, textField4);
        root.addRow(4, label5, textField5);
        root.addRow(5, button);
        root.addRow(10, pieChart);

        Scene scene = new Scene(root, 500, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}