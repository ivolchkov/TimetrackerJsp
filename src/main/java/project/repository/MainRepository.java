package project.repository;

import project.domain.user.Role;
import project.domain.user.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainRepository {
    public static void main(String[] args)  {
//        try ( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "32506632")) {
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        UserDAO repository = new UserDAOImpl();

        User user = User.builder().withId(1L)
                .withName("Ihor")
                .withSurname("Volchkov")
                .withEmail("igorik@gmail.com")
                .withPassword("password")
                .withRole(Role.ADMIN).build();

//        System.out.println(user.getRole().name());
        System.out.println(repository.save(user));
    }
}
