import java.util.Comparator;

public class SortByDate implements Comparator<Person> {
    public int compare(Person one, Person two){
        return one.date.compareTo(two.date);
    }
}
