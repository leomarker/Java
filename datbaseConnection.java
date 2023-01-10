import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "Mysql@root90");


        Connection con = null;
        Statement statement = null;
        ResultSet records = null;
        String sql;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "Mysql@root90");
            sql = "select * from students";
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            records = statement.executeQuery(sql);
        }catch(SQLException xx) {
            System.out.print("Error:" + " + xx.getMessage()");
        } catch(Exception xx) {
            System.out.print("Error: " + " + xx.getMessage()");
        }
        while(records.next()) {
            System.out.print(records.getString("ID"));
            System.out.print(records.getString("name"));
            System.out.print(records.getString("grade"));
            System.out.println(" ");

    }

        con.close();
    }
}