package taterith;

import java.util.Arrays;
import java.util.Comparator;

/**
 * http://www.oracle.com/technetwork/articles/java/architect-lambdas-part1-2080972.html
 *
 * A lambda in Java essentially consists of three parts: a parenthesized set of parameters, an arrow, and then a body,
 * which can either be a single expression or a block of Java code.
 */
public class Lambda {
    public static void main(String[] args) {
        new LambdaExamples().demo();
        new HelloLexicalScope().r.run();
        new HelloVariableCapture().demo();
        new HelloMethodReference().demo();
        new HelloHigerOrderFunction().demo();
    }
}

class LambdaExamples {
    public void demo() {
        // Creates a Runnable-implementing object
        Runnable r = () -> System.out.println("Hello World");
        // Runnable is a Java 8 functional interface meaning it has exactly one method
        r.run();

        // Comparator takes two strings and returns an integer
        Comparator<String> c = (lhs, rhs) -> {
            System.out.println("I am comparing " + lhs + " to " + rhs);
            // If the body of the lambda contains more than one expression, the value can be handed back with return
            return lhs.compareTo(rhs);
        };
        int result = c.compare("Hello", "World");

        // Inferring a lambda to a custom interface
        Something s = (i) -> i.toString();
        System.out.println("Result from Something is " + s.doit(13));
    }
}

/** Something is an example of a functional interface which will be the target type for a lambda */
interface Something {
    public String doit(Integer i);
}

class HelloLexicalScope {
    public Runnable r = () -> {
        // In a regular Java class, 'this' refers to the current instance
        // But in a lambda, 'this' refers to the immediate environment around the lambda's definition
        System.out.println(this);
        System.out.println(toString());
    };

    public String toString() {
        return "HelloLexicalScope's custom toString()";
    }
}

class HelloVariableCapture {
    public void demo() {
        // Lambdas can 'close over' variables from the enclosing scope. In keeping with the rest of Java,
        // if the variables are objects then nothing is stopping those objects from being modified.
        StringBuilder message = new StringBuilder();
        Runnable r = () -> System.out.println(message);
        message.append("Howdy, ");
        message.append("world!");
        r.run();
    }
}

class Person {
    public String firstName;
    public String lastName;
    public int age;

    public static int compareFirstNames(Person lhs, Person rhs) {
        return lhs.firstName.compareTo(rhs.firstName);
    }

    public Person(String f, String l, int a) {
        firstName = f; lastName = l; age = a;
    }

    public String getFirstName() {
        return firstName;
    }

    public String toString() {
        return "[Person: firstName:" + firstName + " " +
                "lastName:" + lastName + " " +
                "age:" + age + "]";
    }
}

class HelloMethodReference {
    public void demo() {
        Person[] people = new Person[] {
                new Person("Ted", "Neward", 41),
                new Person("Charlotte", "Neward", 41),
                new Person("Michael", "Neward", 19),
                new Person("Matthew", "Neward", 13)
        };
        // The double-colon can be used to make a method reference, instead of a method-literal
        Arrays.sort(people, Person::compareFirstNames);
        for (Person p : people)
            System.out.println(p);
    }
}

class HelloHigerOrderFunction {
    public void demo() {
        Person[] people = new Person[] {
                new Person("Ted", "Neward", 41),
                new Person("Charlotte", "Neward", 41),
                new Person("Michael", "Neward", 19),
                new Person("Matthew", "Neward", 13)
        };
        // comparing is an example of a higher-order function
        Arrays.sort(people, Comparator.comparing(Person::getFirstName));
        for (Person p : people)
            System.out.println(p);
    }
}