package lk.ijse.BookWorm.config;

import lk.ijse.BookWorm.entity.Branch;
import lk.ijse.BookWorm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig sessionFactoryConfig;

    private final SessionFactory sessionFactory;

   private SessionFactoryConfig(){
       Properties properties = new Properties();

       try{
           InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties");
           if(inputStream == null){
               System.out.println("Resource not found!");
           } else {
               properties.load(inputStream);
               System.out.println("Resource loaded seccessfully!");
           }
       }catch (IOException e){
           e.printStackTrace();
       }

   /*    sessionFactory = new Configuration()
               .setProperties(properties).
               addAnnotatedClass(Student.class).
               addAnnotatedClass(Room.class).
               addAnnotatedClass(Reservation.class).
               addAnnotatedClass(User.class).
               buildSessionFactory();*/
       sessionFactory = new Configuration()
               .setProperties(properties)
               .addAnnotatedClass(User.class)
               .addAnnotatedClass(Branch.class)
               .buildSessionFactory();


   }

    public static SessionFactoryConfig getSessionFactoryConfig() {
        return (sessionFactoryConfig == null) ? sessionFactoryConfig = new SessionFactoryConfig() : sessionFactoryConfig;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
