package taterith.aggregate;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

/**
 * from https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */
public class Person {
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public Person(String name, LocalDate birthday, Sex gender, String emailAddress) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public void printPerson() {
        System.out.println(name);
    }

    public static List<Person> somePeople() {
        return Arrays.asList(new Person[] {
            new Person("Vladmir", LocalDate.of(1977,1,1), Sex.MALE, "vlad@mail.com"),
            new Person("Stacy", LocalDate.of(1983,1,1), Sex.FEMALE, "stacy@mail.com"),
            new Person("Jonas", LocalDate.of(1990, 1, 1), Sex.MALE, "jonas@mail.com")
        });
    }
}
