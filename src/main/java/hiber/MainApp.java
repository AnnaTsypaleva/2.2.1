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

      userService.add(new User("Tom", "Hanks", "tom@mail.ru",
              new Car("BMV", 7777)));
      userService.add(new User("Oliver", "Stone", "oliver@mail.ru",
              new Car("Toyota", 5677)));
      userService.add(new User("Denis", "Wood", "denis@mail.ru",
              new Car("Mazda", 2364)));
      userService.add(new User("Sara", "Parker", "sara@mail.ru",
              new Car("Mercedes", 2367)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car Model = " + user.getCar().getModel());
         System.out.println("Car Series = " + user.getCar().getSeries());
         System.out.println();
      }

      User user = userService.getUserByCar("Mazda", 2364);
      System.out.println("Машина принадлежит user с именем " + user.getFirstName() + " и фамилией " + user.getLastName());

      context.close();
   }
}





