public class Main {

    public static void main(String[] args) throws InterruptedException {
        //default array of features and requirements
        String[] defFeatures = {"kind" , "smart", "friendly" , "sporty" , "handsome"};
        String[] defRequirements = {"non-smoker" , "ready for kids", "without any addictions" ,
                "positive towards pets" , "doesn't have a hobbies"};
        //default people
        Person one = new Person("Gosha","Taranov", 33, "M", "0",
                defFeatures,defRequirements);
        Thread.sleep(2000);
        Person two = new Person("Danil","Pushkar", 29, "M", "0",
                defFeatures,defRequirements);
        Thread.sleep(2000);
        Person three = new Person("Danil","Azarov", 32, "M", "2",
                defFeatures,defRequirements);
        Thread.sleep(2000);
        Person four = new Person("Anna","Pushkar", 23, "W", "1",
                defFeatures,defRequirements);
        Thread.sleep(2000);
        Person five = new Person("Maria","Krivinos", 17, "W", "0",
                defFeatures,defRequirements);
        Thread.sleep(2000);

        People.people.add(one);
        People.people.add(two);
        People.people.add(three);
        People.people.add(four);
        People.people.add(five);

        Menu.start();           //start program
        Menu.addPerson();       //add your own profile
        Menu.allOptions();      //all menu options

    }
}
