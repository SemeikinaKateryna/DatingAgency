import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    static Person temporary = new Person();
    public static void start()
    {
        char ch= '\u2661';
        System.out.println("Dear user, welcome to the \"Dating Agency\"!"+ ch);
        System.out.println((char) 27 + "[33m" + "Below are the main character traits that you possess" +
                " and the main requirements for a future partner.");
        System.out.println((char) 27 + "[34m" + "Proposed feathers:\nkind, smart, friendly, easy-going," +
                " beautiful(for women) and handsome(for men),"+
                " brave, sporty, polite, sociable, ambitious, honest.");
        System.out.println((char) 27 + "[36m" + "Proposed requirements:\nnon-smoker/smoker, " +
                "ready for kids/child-free," +
                "without addictions to alcohol, drugs, computer or gambling,"+
                "has/doesn't have a hobbies"+
                "\npositive/negative towards pets.");
    }
    public static void addPerson() {
        Scanner scanner = new Scanner(System.in);
        System.out.println((char) 27 + "[35m" + "Enter information about you to find your perfect match!" + "\n" +
                (char) 27 + "[38m" + "Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your sex (M/W): ");
        String sex = scanner.nextLine();
        System.out.println("Your marital status: choose the number : UNMARRIED (0), DIVORCED (1), WIDOWED(2) ?");
        String status = scanner.nextLine();
        System.out.println("Your feathers:");
        String[] features = new String[5];
        for (int i = 0; i < features.length; i++) {
            features[i] = scanner.nextLine();
        }
        System.out.println("Enter requirements for your future partner(adjectives):");
        String[] requirements = new String[5];
        for (int i = 0; i < requirements.length; i++) {
            requirements[i] = scanner.nextLine();
        }
        Menu.temporary = new Person(name, surname, age, sex, status, features, requirements);

        //System.out.println("Save information about you to a file?");
        //in future this can be work with files

        People.people.add(temporary);
        //test invitation to new user
        Invitation e = new Invitation(People.people.get(0),temporary,new Date(2023,0,16),
                "Kharkiv");
        temporary.MyInvitations.add(e);
    }
    public static void allOptions() {
        boolean flag = true;
        while(flag) {
            System.out.println("---------------All options:---------------- ");
            System.out.println("1 - Show all users\n" + "2 - Work with me\n"
                    + "3 - Work with my candidates\n" + "4 - Work with my invitations\n" + "0 - Exit");
            Scanner sc = new Scanner(System.in);
            System.out.println("--------Choose your variant!---------");
            int var = sc.nextInt();
            switch (var) {
                case 1:
                    System.out.println(People.people);
                    break;
                case 2:
                    Menu.workWithMe();
                    break;
                case 3:
                    Menu.workWithCandidates();
                    break;
                case 4:
                    Menu.workWithInvitations();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect variant! Try again!"); flag = true;
                    break;
            }
        }
    }
    public static void workWithMe(){
        System.out.println("---------------You work with your profile --------------- ");
        System.out.println("1 - Show my profile\n" + "2 - Change my name\n"
                + "3 - Change my marital status\n" + "4 - Change my age\n" + "0 - Exit");
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            System.out.println("--------Choose your variant!---------");
            int var = sc.nextInt();
            switch (var) {
                case 1:
                    System.out.println(temporary);
                    break;
                case 2:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter new name: ");
                    temporary.setName(scanner.nextLine());
                    System.out.println("Name changed successfully!");
                    break;
                case 3:
                    System.out.println("Enter new marital status: ");
                    int tempStatus = sc.nextInt();
                    switch (tempStatus){
                        case 0: temporary.setMaritalStatus(MaritalStatus.UNMARRIED); break;
                        case 1: temporary.setMaritalStatus(MaritalStatus.DIVORCED); break;
                        case 2: temporary.setMaritalStatus(MaritalStatus.WIDOWED); break;
                        default: System.out.println("Incorrect variant! Try again!");
                    }
                    System.out.println("Marital status changed successfully!");
                    break;
                case 4:
                    System.out.println("Enter new age: ");
                    temporary.setAge(sc.nextInt());
                    System.out.println("Age changed successfully!");
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect variant! Try again!"); flag = true;
                    break;
            }
        }
    }
    public static void workWithCandidates() {
        System.out.println("---------------You work with candidates ----------------");
        temporary.generateCandidates();
        System.out.println("1 - Show my candidates\n" + "2 - Sort candidates by name\n"
                + "3 - Sort candidates by age\n" + "4 - Sort candidates by date of registration\n" + "0 - Exit");
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("---------Choose your variant!--------");
            int var = sc.nextInt();
            switch (var) {
                case 1 : temporary.showCandidates(); break;
                case 2 : Collections.sort(temporary.candidates); break;
                case 3 : Collections.sort(temporary.candidates,new SortByAge()); break;
                case 4 : Collections.sort(temporary.candidates, new SortByDate()); break;
                case 0 : flag = false; break;
                default:
                    System.out.println("Incorrect variant! Try again!"); flag = true;
                    break;
            }
        }
    }
    public static void workWithInvitations() {
        System.out.println("---------------You work with invitations ----------------");
        System.out.println("1 - Show my invitations\n" + "2 - Generate invitation\n" + "0 - Exit");
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("---------Choose your variant!--------");
            int var = sc.nextInt();
            switch (var) {
                case 1 : temporary.showMyInvitations(); break;
                case 2 : temporary.generateInvitation(); break;
                case 0 : flag = false; break;
                default:
                    System.out.println("Incorrect variant! Try again!"); flag = true;
                    break;
            }
        }
    }
}
