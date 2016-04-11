package cn.itcast.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//dao实例工厂
public class DaoFactory {
    private static DaoFactory ourInstance = new DaoFactory();

    public static DaoFactory getInstance() {
        return ourInstance;
    }
    private Properties prop = new Properties();;
    private DaoFactory()  {
        try {
            InputStream in  = DaoFactory.class.getClassLoader().getResourceAsStream("cn/itcast/factory/dao.properties");
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T createDao(Class<T> clazz){

        try {
            String daoname = clazz.getSimpleName();
            String classname = prop.getProperty(daoname);
            return (T) Class.forName(classname).newInstance();
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
}
