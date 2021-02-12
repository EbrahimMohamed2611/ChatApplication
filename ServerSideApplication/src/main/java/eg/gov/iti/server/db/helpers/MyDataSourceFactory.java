package eg.gov.iti.server.db.helpers;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyDataSourceFactory {

    private static MysqlDataSource mysqlDS = null;

    private MyDataSourceFactory() {
        System.out.println("Connection is started ");
    }

    public static DataSource getMySQLDataSource() {

            Properties props = new Properties();
            FileInputStream fis = null;

            try {
                //   fis = new FileInputStream(MainEntryPoint.class.getResource("/db.properties").getFile());
             //   fis = new FileInputStream("D:\\ITI\\Java Project Specification\\ChatApplication\\ServerSideApplication\\src\\main\\resources\\databaseConfiguration\\databaseProperties.properties");
                fis = new FileInputStream("databaseProperties.properties");

                //   fis = (FileInputStream) MyDataSourceFactory.class.getClassLoader().getResourceAsStream("db.properties");
                props.load(fis);
                mysqlDS = new MysqlDataSource();
                mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
                mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
                mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
            } catch (IOException e) {
                e.printStackTrace();
            }
           System.out.println("Connection is done");
            return mysqlDS;


    }
}
