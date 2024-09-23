import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Itinerary class represents a sequence of activities for a group of attendees
class Itinerary {
    private String referenceNumber;
    private List<Activity> activities;
    private List<Attendee> attendees;
    private List<Addon> addons;

    // Constructor
    public Itinerary(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        this.activities = new ArrayList<>();
        this.attendees = new ArrayList<>();
        this.addons = new ArrayList<>();
    }

    // Add activity to the itinerary
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    // Add attendee to the itinerary
    public void addAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    // Add addon to the itinerary
    public void addAddon(Addon addon) {
        addons.add(addon);
    }

    // Getters and setters
    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public List<Addon> getAddons() {
        return addons;
    }

    public void setAddons(List<Addon> addons) {
        this.addons = addons;
    }

    public double calculateTotalCost() {
        int activityCost = activities.stream().mapToInt(Activity::getBaseCost).sum();
        int addonCost = addons.stream().mapToInt(Addon::getCost).sum();
        return activityCost + addonCost; 
    }
}

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Get itinerary reference number
            System.out.print("Enter itinerary reference number: ");
            String referenceNumber = scanner.nextLine();

            // Create itinerary
            Itinerary itinerary = new Itinerary(referenceNumber);

            // Get number of activities and attendees
            System.out.print("Enter number of activities: ");
            int numActivities = scanner.nextInt();
            System.out.print("Enter number of attendees: ");
            int numAttendees = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Get activity details
            for (int i = 0; i < numActivities; i++) {
                System.out.println("Enter details for activity " + (i + 1) + ":");
                System.out.print("Title: ");
                String title = scanner.nextLine();
                System.out.print("Description: ");
                String description = scanner.nextLine();
                System.out.print("Location: ");
                String location = scanner.nextLine();
                System.out.print("Date and Time (YYYY-MM-DD HH:MM): ");
                String dateTime = scanner.nextLine();
                System.out.print("Duration (minutes): ");
                int duration = scanner.nextInt();
                System.out.print("Base Cost (in pence): ");
                int baseCost = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                Activity activity = new Activity(title, description, location, dateTime, duration, baseCost);
                itinerary.addActivity(activity);
            }

            // Get attendee details
            for (int i = 0; i < numAttendees; i++) {
                System.out.print("Enter name of attendee " + (i + 1) + ": ");
                String attendeeName = scanner.nextLine();
                Attendee attendee = new Attendee(attendeeName);
                itinerary.addAttendee(attendee);
            }

            // Get addon details
            System.out.print("Do you want to add addons? (yes/no): ");
            String addAddonChoice = scanner.nextLine();
            while (addAddonChoice.equalsIgnoreCase("yes")) {
                System.out.print("Enter addon name: ");
                String addonName = scanner.nextLine();
                System.out.print("Enter addon cost (in pence): ");
                int addonCost = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                Addon addon = new Addon(addonName, addonCost);
                itinerary.addAddon(addon);

                System.out.print("Do you want to add another addon? (yes/no): ");
                addAddonChoice = scanner.nextLine();
            }

            // Calculate total cost of itinerary
            double totalCost = itinerary.calculateTotalCost();

            // Display itinerary summary
            System.out.println("\nItinerary Summary:");
            System.out.println("+====================================================+");
            System.out.println("| Client: " + itinerary.getReferenceNumber() + "            ");
            System.out.println("| Activities: " + numToWord(numActivities) + "             Attendees: " + numToWord(numAttendees));
            System.out.println("|");
            System.out.println("| Cost: £ " + String.format("%.2f", totalCost));
            System.out.println("|");
            System.out.println("|                     Cost breakdown                 ");
            System.out.println("|");

            // Display activity subtotals
            double activitySubtotal = 0;
            for (int i = 0; i < numActivities; i++) {
                Activity activity = itinerary.getActivities().get(i);
                int activityCost = activity.getBaseCost();
                activitySubtotal += activityCost;

                System.out.println("| " + (i + 1) + ". " + activity.getTitle() + " @ £" + (activityCost) + " x " + numAttendees);
                if (!itinerary.getAddons().isEmpty()) {
                    System.out.print("|    Add-ons:");
                    for (Addon addon : itinerary.getAddons()) {
                        System.out.print(" " + addon.getName() + " @ £" + (addon.getCost()) + " x " + numAttendees);
                        activitySubtotal += (addon.getCost() * numAttendees);
                    }
                    System.out.println();
                }
                System.out.println("|                                Sub-Total: £ " + String.format("%.2f",activitySubtotal));
            }

            // Display addon subtotals
            double addonSubtotal = 0;
            System.out.println("| Itinerary Add-ons");
            for (Addon addon : itinerary.getAddons()) {
                addonSubtotal += (addon.getCost() * numAttendees);
                System.out.println("| - " + addon.getName() + " @ £" + (addon.getCost()) + " x " + numAttendees);
            }
            if (!itinerary.getAddons().isEmpty()) {
                System.out.println("|                                Sub-Total: £ " + String.format("%.2f", addonSubtotal));
            }

            // Calculate and display discount
            double discount = (activitySubtotal + addonSubtotal) * getDiscountRate(numAttendees, numActivities);
            System.out.println("|");
            System.out.println("| 5% Discount                    Sub-Total: £ " + String.format("%.2f", discount));
        }

        System.out.println("+====================================================+");
    }

    // Method to convert number to word
    private static String numToWord(int num) {
        if (num == 1) return "One";
        if (num == 2) return "Two";
        if (num == 3) return "Three";
        if (num == 4) return "Four";
        if (num == 5) return "Five";
        return String.valueOf(num);
    }

    // Method to get discount rate based on number of attendees and activities
    private static double getDiscountRate(int numAttendees, int numActivities) {
        if (numAttendees >= 6 || numActivities >= 3) return 0.05;
        return 0;
    }
}