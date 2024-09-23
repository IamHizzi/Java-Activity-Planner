
// Activity class represents an individual activity
public class Activity {
    private String title;
    private String description;
    private String location;
    private String dateTime;
    private int duration; // in minutes
    private int baseCost; // in pence

    // Constructor
    public Activity(String title, String description, String location, String dateTime, int duration, int baseCost) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.duration = duration;
        this.baseCost = baseCost;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }
}
