package taterith.aggregate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Aggregate operations process elements from a stream.
 * A stream carries values from a source, such as collection, through a pipeline.
 * A pipeline is a sequence of aggregate operations.
 *
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
 * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#approach9
 * https://docs.oracle.com/javase/tutorial/collections/streams/
 */
public class AggregateOperations {
    public static void main(String[] args) {
        // Print the email addresses of those eligible for Selective Service
        List<String> emails = Person.somePeople()
                .stream()
                .filter(p ->
                            p.gender == Person.Sex.MALE
                            && p.getAge() > 18
                            && p.getAge() < 25)
                .map(p -> p.emailAddress)
                .collect(Collectors.toList());

        emails.forEach(email -> System.out.println(email));

        // Calculate the average age
        double averageAge = Person.somePeople()
                .stream()
                .filter(p -> p.gender == Person.Sex.MALE)
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();
    }
}
