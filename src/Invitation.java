import java.util.Date;
public class Invitation {
    Person sender;
    Person recipient;
    Date meetingDate;
    String place;
    Response response;
    public Invitation(Person sender, Person recipient, Date meetingDate, String place) {
        this.sender = sender;
        this.recipient = recipient;
        this.meetingDate = meetingDate;
        this.place = place;
    }
    @Override
    public String toString(){
        return "Sender: " + sender.toString() + "\nRecipient: " + recipient.toString() +
                "\nMeeting date:\t" + meetingDate + "\nMeeting place:\t" + place +
                "\nResponse status:\t" + response;
    }
}
