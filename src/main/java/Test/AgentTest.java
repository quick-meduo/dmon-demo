package Test;
import java.sql.*;

public class AgentTest {

    public static void main(String[] args) throws Exception {

        Class.forName("org.h2.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");

//        try (Connection conn = DriverManager.getConnection("jdbc:h2:./test", "sa", "");
//            PreparedStatement preparedStatement = conn.prepareStatement("create table A (a varchar(32),b varchar(32))")) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "sinux123");
             PreparedStatement preparedStatement = conn.prepareStatement("create table A (a varchar(32),b varchar(32))")) {
            Statement statement = conn.createStatement();
            boolean ret = preparedStatement.execute();
            ret = statement.execute("insert into A values ('aa','a')");
            ret = statement.execute("insert into A values ('bb','b')");
            ret = statement.execute("insert into A values ('cc','c')");
            ret = statement.execute("insert into A values ('ccs','cs')");
            ret = statement.execute("insert into A values ('ccsx','csx')");
            ResultSet rs = statement.executeQuery("select * from A");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnName =rsmd.getColumnLabel(i);
                    Object value = rs.getObject(columnName);
                    System.out.println(columnName + ":" + value.toString());
                }
            }
            rs = statement.executeQuery("select * from A where b like '%c%'");
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnName =rsmd.getColumnLabel(i);
                    Object value = rs.getObject(columnName);
                    System.out.println(columnName + ":" + value.toString());
                }
            }
            ret =  statement.execute("drop table A");
        }
    }

}
