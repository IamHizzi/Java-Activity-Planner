// Addon class represents additional services that attendees can add to their itinerary
class Addon {
    private String name;
    private int cost; // in pence

    // Constructor
    public Addon(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
