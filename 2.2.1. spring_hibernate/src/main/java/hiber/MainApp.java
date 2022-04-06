package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User u1 = new User("Иван", "Иванов", "иванов@mail.ru");
      User u2 = new User("Петя", "Петров", "петров@mail.ru");
      User u3 = new User("Сёма", "Семёнов", "семёнов@mail.ru");
      User u4 = new User("Вася", "Васильев", "вася@mail.ru");

      Car car1 = new Car("Лада", 8);
      Car car2 = new Car("Москвич", 412);
      Car car3 = new Car("Жигуль", 3);
      Car car4 = new Car("Зил", 130);

      userService.add(u1.setCar(car1).setUser(u1));
      userService.add(u2.setCar(car2).setUser(u2));
      userService.add(u3.setCar(car3).setUser(u3));
      userService.add(u4.setCar(car4).setUser(u4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Лада", 8));

      try {
         User notFoundUser = userService.getUserByCar("БМВ", 3);
      } catch (NoResultException e) {
         System.out.println("Нету такого");
      }

      context.close();
   }
}
