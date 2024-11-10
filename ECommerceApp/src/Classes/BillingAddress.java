package ECommerceApp.src.Classes;

public class BillingAddress {
    private String streetName;
    private String streetNumber;
    private String apartment; // Optional
    private String city;
    private String state;
    private String country;
    private String postalCode;

    public BillingAddress(String streetName, String streetNumber, String city, 
                         String state, String country, String postalCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
    }

    // Constructor with optional apartment
    public BillingAddress(String streetName, String streetNumber, String apartment,
                         String city, String state, String country, String postalCode) {
        this(streetName, streetNumber, city, state, country, postalCode);
        this.apartment = apartment;
    }

    // Getters
    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getApartment() {
        return apartment;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    // Setters
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        StringBuilder address = new StringBuilder();
        address.append(streetNumber).append(" ").append(streetName);
        
        if (apartment != null && !apartment.isEmpty()) {
            address.append(", Apt ").append(apartment);
        }
        
        address.append("\n")
              .append(city).append(", ")
              .append(state).append(" ")
              .append(postalCode).append("\n")
              .append(country);
        
        return address.toString();
    }
}