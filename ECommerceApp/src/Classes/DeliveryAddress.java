package ECommerceApp.src.Classes;

import java.util.UUID;

public class DeliveryAddress {

  private UUID id;
  private String street;
  private String neighborhood;
  private String number;
  private String complement;
  private String city;
  private String state;
  private String zipCode;

  /**
   * Constructor for the DeliveryAddress class
   * @param street The street of the address -> {@link String}
   * @param neighborhood The neighborhood of the address -> {@link String}
   * @param number The number of the address -> {@link String}
   * @param complement The complement of the address -> {@link String}
   * @param city The city of the address -> {@link String}
   * @param state The state of the address -> {@link String}
   * @param zipCode The ZIP code of the address -> {@link String}
   */
  public DeliveryAddress(
    String street,
    String neighborhood,
    String number,
    String complement,
    String city,
    String state,
    String zipCode
  ) {
    this.id = UUID.randomUUID();
    this.street = street;
    this.neighborhood = neighborhood;
    this.number = number;
    this.complement = complement;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }

  // Getters and setters for the attributes

  /**
   * {@return the id of the DeliveryAddress object as a {@link UUID}}
   */
  public UUID getId() {
    return id;
  }

  /**
   * {@return the street of the DeliveryAddress object as a {@link String}}
   */
  public String getStreet() {
    return street;
  }

  /**
   * Method to set the street of the DeliveryAddress object
   * @param street The street of the address -> {@link String}
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * {@return the neighborhood of the DeliveryAddress object as a {@link String}}
   */
  public String getNeighborhood() {
    return neighborhood;
  }

  /**
   * Method to set the neighborhood of the DeliveryAddress object
   * @param neighborhood The neighborhood of the address -> {@link String}
   */
  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  /**
   * {@return the number of the DeliveryAddress object as a {@link String}}
   */
  public String getNumber() {
    return number;
  }

  /**
   * Method to set the number of the DeliveryAddress object
   * @param number The number of the address -> {@link String}
   */
  public void setNumber(String number) {
    this.number = number;
  }

  /**
   * {@return the complement of the DeliveryAddress object as a {@link String}}
   */
  public String getComplement() {
    return complement;
  }

  /**
   * Method to set the complement of the DeliveryAddress object
   * @param complement The complement of the address -> {@link String}
   */
  public void setComplement(String complement) {
    this.complement = complement;
  }

  /**
   * {@return the city of the DeliveryAddress object as a {@link String}}
   */
  public String getCity() {
    return city;
  }

  /**
   * Method to set the city of the DeliveryAddress object
   * @param city The city of the address -> {@link String}
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * {@return the state of the DeliveryAddress object as a {@link String}}
   */
  public String getState() {
    return state;
  }

  /**
   * Method to set the state of the DeliveryAddress object
   * @param state The state of the address -> {@link String}
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * {@return the ZIP code of the DeliveryAddress object as a {@link String}}
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Method to set the ZIP code of the DeliveryAddress object
   * @param zipCode The ZIP code of the address -> {@link String}
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
}
