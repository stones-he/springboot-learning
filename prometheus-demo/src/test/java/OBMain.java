import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OBMain {
    public static void main(String[] args) {
        try {
            String url = "jdbc:oceanbase://10.110.7.36:2883/SYS";
            String user = "SYS@hxy_test#obtest";
            String password = "bbBB22__";
            Class.forName("com.oceanbase.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.createStatement().execute("select * from dual");
            ResultSet resultSet = connection.createStatement().executeQuery("select * from dual");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo() {
        List<Student> students = Stream.of(
                  Student.builder().name("name1").age(10).className("1").build()
                , Student.builder().name("name2").age(20).className("2").build()
                , Student.builder().name("name3").age(30).className("2").build()
                , Student.builder().name("name4").age(40).className("4").build()
                , Student.builder().name("name5").age(50).className("4").build()
                , Student.builder().name("name6").age(60).className("4").build()
                , Student.builder().name("name7").age(70).className("8").build()
                , Student.builder().name("name8").age(80).className("8").build()
                , Student.builder().name("name9").age(90).className("8").build()
                , Student.builder().name("name10").age(100).className("8").build()
                , Student.builder().name("name11").age(110).className("1").build()
                , Student.builder().name("name12").age(120).className("1").build()
                , Student.builder().name("name13").age(130).className("1").build()
        ).toList();

        //

        Map<String, List<Student>> map = students.stream().collect(Collectors.groupingBy(Student::getClassName));
        System.out.println(map);

    }
}