import java.util.*;

public class Person implements Comparable<Person> {
    String name;
    String surname;
    int age;
    String sex;
    int id;
    static int count;
    Date date;
    MaritalStatus maritalStatus;
    String[] features = new String[5];      //adjectives
    String[] requirements = new String[5];
    ArrayList<Person> candidates = new ArrayList<>();
    ArrayList<Invitation> invitations = new ArrayList<>(); //invitations, that user sent
    ArrayList<Invitation> MyInvitations = new ArrayList<>(); //incoming invitations
    Person() {
        this.name = " ";
        this.surname = " ";
        this.age = 0;
        this.sex = " ";
        this.id = 0;
        Arrays.fill(features," ");
        Arrays.fill(requirements," ");
    }
    Person(String name, String surname, int age, String sex, String status,
           String[] features, String[] requirements) {
        count++;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        switch (status) {
            case "0" -> maritalStatus = MaritalStatus.UNMARRIED;
            case "1" -> maritalStatus = MaritalStatus.DIVORCED;
            case "2" -> maritalStatus = MaritalStatus.WIDOWED;
            default -> {
            }
        }
        this.id = count;
        date = new Date();
        System.arraycopy(features, 0, this.features, 0, features.length);
        System.arraycopy(requirements, 0, this.requirements, 0, requirements.length);
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String toString() {
        return("\nUser #" + id + "\nName :\t" + name + "\nSurname :\t" + surname + "\nAge :\t" + age
        + "\nSex :\t" + sex + "\nMarital status : " + maritalStatus
        + "\nYour features:" + Arrays.toString(features) + "\nRequirements for future partner :"
        + Arrays.toString(requirements)+ "\nDate of registration :\t" + date);
    }
    public void generateCandidates() {
        int fCount = 0;        //for compare by feathers
        int rCount = 0;        //for compare by requirements
        //since the requirements and information about myself have same size are 5 positions
        //I can use one cycle for compare features as well as requirements
        for (Person temp : People.people) {
            for (int i = 0; i < features.length; i++) {
                for (int j = 0; j < features.length; j++) {
                    if (this.features[i].equals(temp.features[j])) {
                        fCount++;
                    }
                    if (this.requirements[i].equals(temp.requirements[j])) {
                        rCount++;
                    }
                }
            }
            //condition of match
            if (!this.sex.equals(temp.sex) && fCount >= 3 && rCount >= 3) {
                candidates.add(temp);
            }
        }
    }
    public void showCandidates() {
        System.out.println("---------------Your candidates: ----------------");
        if(candidates.isEmpty()){
            System.out.println("You have no candidates(");
        }
        else {
            for (Person tempCan : candidates) {
                System.out.println(tempCan);
            }
        }
    }
    //default sorting algorithm : by surname
    //if surnames equals then sort by name
    @Override
    public int compareTo(Person other){
        if (this.surname.equals(other.surname)) {
            return this.name.compareTo(other.name);
        }
        else{
            return this.surname.compareTo(other.surname);
        }
    }
    public void generateInvitation() {
        //in this method class Date can be used, but it is unwanted, so I use GregorianCalendar
        GregorianCalendar calendar = new GregorianCalendar();
        boolean counter = false;
        System.out.println("To whom do you want to send a date invitation? (Choose person id)");
        Scanner sc = new Scanner(System.in);
        int selectedId = sc.nextInt();
        for (Person temp : People.people) {
            if (selectedId == temp.id) {
                counter = true;
                System.out.println("Enter meeting date!");
                //Date selectedDate = new Date(sYear, sMonth,sDay);
                System.out.println("Enter year:");
                int sYear = sc.nextInt();
                calendar.set(Calendar.YEAR, sYear);
                System.out.println("Enter month:");
                int sMonth = sc.nextInt();
                calendar.set(Calendar.MONTH, --sMonth);
                System.out.println("Enter day:");
                int sDay = sc.nextInt();
                calendar.set(Calendar.DAY_OF_MONTH, sDay);
                System.out.println("Enter hour:");
                int sHour = sc.nextInt();
                calendar.set(Calendar.HOUR_OF_DAY, sHour);
                System.out.println("Enter minute:");
                int sMinute = sc.nextInt();
                calendar.set(Calendar.MINUTE, sMinute);
                System.out.println("Enter place:");
                String sPlace = sc.next();
                Invitation invitation = new Invitation(this, temp, calendar.getTime(), sPlace);
                temp.MyInvitations.add(invitation);
                this.invitations.add(invitation);
                System.out.println("--------------Generated invitation: ---------------\n" + invitation);
            }
        }
        if (!counter) {
            System.out.println("Incorrect id! There is no such person!");
        }
    }
    public void showMyInvitations(){
        System.out.println("---------------Your invitations: ----------------");
        if(this.MyInvitations.isEmpty()){
            System.out.println("You have no invitations");
        } else {
            for (Invitation tempI : this.MyInvitations) {
                System.out.println(tempI);
                if(tempI.response == null) {
                    System.out.println("Do you want to accept(0) or don't accept(1) this invitation? " +
                            "To skip press any other button");
                    Scanner scanner = new Scanner(System.in);
                    String answer = scanner.next();
                    switch (answer) {
                        case "0" -> tempI.response = Response.ACCEPT;
                        case "1" -> tempI.response = Response.DO_NOT_ACCEPT;
                        default -> System.out.println("Incorrect!");
                    }
                }
            }
        }
    }

}
