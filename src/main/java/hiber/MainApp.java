package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Tom", "Hanks", "tom@mail.ru");
      User user2 = new User("Oliver", "Stone", "oliver@mail.ru");
      User user3 = new User("Denis", "Wood", "denis@mail.ru");
      User user4 = new User("Sara", "Parker", "sara@mail.ru");

      Car car1 = new Car("BMV", 7777);
      Car car2 = new Car("Toyota", 5677);
      Car car3 = new Car("Mazda", 2364);
      Car car4 = new Car("Mercedes", 2367);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      System.out.println(userService.getUserByCar("Mazda", 2364));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}

