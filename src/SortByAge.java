import java.util.Comparator;

public class SortByAge implements Comparator<Person> {
    @Override
    public int compare(Person one, Person two) {
        return one.age - two.age;
    }
}
