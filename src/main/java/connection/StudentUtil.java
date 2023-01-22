package connection;

import com.example.preperation_final_2.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentUtil {
    public static void createTable() throws SQLException {
        String createSql = "CREATE TABLE IF NOT EXISTS Students_Tb("+
                "ID INTEGER NOT NULL AUTO_INCREMENT,"+
                "Name VARCHAR(255),"+
                "Lastname VARCHAR(255),"+
                "Age INTEGER NOT NULL, "+
                "Faculty VARCHAR(255),"+
                "Year INTEGER NOT NULL,"+
                "PRIMARY KEY (ID))";
        JDBCUtil.getStatement().executeUpdate(createSql);
        System.out.println("CREATED TABLE");
    }
    public static void insertItem(Student student) throws SQLException {
        String insertSql = "INSERT INTO Students_Tb(Name, Lastname, Age, Faculty, Year) VALUES("
                +"'"+student.getName()+"', "
                +"'"+student.getLastname()+"', "
                +"'"+student.getAge()+"', "
                +"'"+student.getFaculty()+"', "
                +"'"+student.getYear()+"' )";

        JDBCUtil.getStatement().executeUpdate(insertSql);
        System.out.println("inserted item ");
    }

    public static  Map<String, Long> GetMapData() throws SQLException {
        String selectSql = "SELECT * FROM Students_Tb";
        List<Student> students = new ArrayList<>();

        ResultSet resultSet = JDBCUtil.getStatement().executeQuery(selectSql);
        while (resultSet.next()){
            students.add(new Student(resultSet.getString("Name"),
                    resultSet.getString("Lastname"),
                    resultSet.getInt("Age"),
                    resultSet.getString("Faculty"),
                    resultSet.getInt("Year")));
        }

        Map<String, Long> myMap = students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.counting()));
        System.out.println(myMap);

        return myMap;
    }

    public static ObservableList<PieChart.Data> getData() throws SQLException {
        ObservableList<PieChart.Data> myList = FXCollections.observableArrayList();
        Map<String, Long> myMap = GetMapData();
        for(String k : myMap.keySet()){
            myList.add(new PieChart.Data( k, myMap.get(k)));
        }
        return myList;
    }

    public static void getDataForBarChart(){

    }

}
