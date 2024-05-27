package busreservation;

public class Bus {
	private int busno;
    private String busName;
    private Boolean ac;
    private int capacity;
    private int availableSeats;
    private String route;
    private String type;
    private String startTime;
    private String endTime;

    Bus(int busno, String busName, Boolean ac, int capacity, String route, String type, String startTime, String endTime) {
        this.busno = busno;
        this.busName = busName;
        this.ac = ac;
        this.capacity = capacity;
        this.availableSeats = capacity;
        this.route = route;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getbusno() {
        return busno;
    }

    public String getBusName() {
        return busName;
    }

    public Boolean isac() {
        return ac;
    }

    public int getcapacity() {
        return capacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getRoute() {
        return route;
    }

    public String getType() {
        return type;
    }

    public void setbusno(int busno) {
        this.busno = busno;
    }

    public void setcapacity(int capacity) {
        this.capacity = capacity;
    }

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        } else {
            System.out.println("No available seats to book.");
        }
    }

    public static void routeInfo() {
        System.out.println("1. Karur to Chennai");
        System.out.println("2. Namakkal to Chennai");
    }

    public void getbusinfo() {
        System.out.println("Bus No: " + busno + "\nBus Name: " + busName + "\nAC/Non-AC: " + ac + "\nCapacity: " + capacity + "\nAvailable Seats: " + availableSeats + "\nType: " + type + "\n");
    }

    public double calculateCost() {
        double cost = 0;
        if (route.equals("Karur to Chennai")) {
            if (type.equals("sitting")) {
                cost = ac ? 550 : 450;
            } else if (type.equals("sleeper")) {
                cost = ac ? 750 : 650;
            }
        } else if (route.equals("Namakkal to Chennai")) {
            if (type.equals("sitting")) {
                cost = ac ? 500 : 400;
            } else if (type.equals("sleeper")) {
                cost = ac ? 700 : 600;
            }
        }
        return cost;
    }
}
