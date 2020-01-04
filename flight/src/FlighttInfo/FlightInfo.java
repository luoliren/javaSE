package FlighttInfo;

public class FlightInfo {



        private String FlightNumber;
        private String InitialPoint;
        private String destination;
        private int price;
        private String FlightType;
        public void setFlightNumber(String flightNumber) {
            this.FlightNumber = flightNumber;
        }
        public String getFlightNumber() {
            return FlightNumber;
        }
        public void setInitialPoint(String initialPoint) {
            this.InitialPoint = initialPoint;
        }
        public String getInitialPoint() {
            return InitialPoint;
        }
        public void setDestination(String destination) {
            this.destination = destination;
        }
        public String getDestination() {
            return destination;
        }
        public void setPrice(int price) {
            this.price = price;
        }
        public int getPrice() {
            return price;
        }
        public void setFlightType(String flightType) {
            this.FlightType = flightType;
        }
        public String getFlightType() {
            return FlightType;
        }

        public FlightInfo(String FlightNumber, String InitialPoint, String destination, int price, String flightType)
        {
            this.FlightNumber = FlightNumber;
            this.InitialPoint = InitialPoint;
            this.destination = destination;
            this.price = price;
            this.FlightType = flightType;
        }
    }


