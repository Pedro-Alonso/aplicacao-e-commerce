package ECommerceApp.src.Classes;
import java.util.UUID;

import ECommerceApp.src.Classes.Order.PaymentStatus;

import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * Represents a review for a specific product in an e-commerce application.
 * The `ProductReview` class manages the ratings and comments for a product,
 * ensuring that only valid users (those who have purchased the product) can
 * submit ratings.
 *
 * @author Racciatti
 * @version 1.0
 * @see ProductRating
 */
public class ProductReview {

    /**
     * The product that this review is for.
     */
    private Product product;

    /**
     * Unique identifier for the product review.
     */
    private UUID id;

    /**
     * The number of ratings for this product.
     */
    private int ratingCount;

    /**
     * The average rating for this product.
     */
    private float averageRating;

    /**
     * List of individual product ratings.
     */
    private ArrayList<ProductRating> ratings;

    /**
     * Constructs a `ProductReview` object for the specified product.
     *
     * @param product The product that this review is for.
     */
    public ProductReview(Product product) {
        this.id = UUID.randomUUID();
        this.ratingCount = 0;
        this.averageRating = 0;
        this.product = product;
    }

    /**
     * Checks whether the specified user is valid to rate the product.
     * A user is considered valid if they have purchased the product and the
     * payment for the order has been completed.
     *
     * @param rater The user who is attempting to rate the product.
     * @return `true` if the user is valid, `false` otherwise.
     */
    private boolean isRaterValid(ECommerceUser rater) {
        // Check each order for the user
        for (Order order : rater.getOrders()) {
            // Check each product in the order
            for (Product product : order.getCart().getProducts()) {
                // If the product being rated matches the current product
                if (product.getId() == this.product.getId()) {
                    // And the payment for the order has been completed
                    if (order.getPaymentStatus() == PaymentStatus.COMPLETED) {
                        // The user is valid to rate the product
                        return true;
                    }
                }
            }
        }
        // The user is not valid to rate the product
        return false;
    }

    /**
     * Adds a new rating for the product.
     *
     * @param rater    The user who is rating the product.
     * @param stars    The number of stars (rating) for the product.
     * @param comment  The comment provided by the user.
     * @throws Exception If the user is not valid to rate the product.
     */
    public void addRating(ECommerceUser rater, int stars, String comment) throws Exception {
        // Check if the rater is valid
        if (!isRaterValid(rater)) {
            throw new Exception("The user can't rate a product he has not purchased!");
        }

        // Create a new product rating and add it to the list
        ProductRating newRating = new ProductRating(comment, rater, stars);
        ratings.add(newRating);

        // Increment the rating count and update the average rating
        this.ratingCount++;
        this.updateAverageRating();
    }

    /**
     * Retrieves a specific product rating by its unique identifier.
     *
     * @param id The unique identifier of the product rating.
     * @return The `ProductRating` object, or `null` if not found.
     */
    public ProductRating getRating(UUID id) {
        // Search for the rating in the list
        for (ProductRating rating : this.ratings) {
            if (rating.getId() == id) {
                return rating;
            }
        }
        // If the rating is not found, return null
        return null;
    }

    /**
     * Removes a specific product rating by its unique identifier.
     *
     * @param id The unique identifier of the product rating.
     * @throws Exception If the rating does not exist.
     */
    public void removeRating(UUID id) throws Exception {
        // Search for the rating in the list
        ProductRating rating = this.getRating(id);

        // If the rating is not found, throw an exception
        if (rating == null) {
            throw new Exception("The rating does not exist");
        }

        // Remove the rating from the list, decrement the rating count, and update the average rating
        this.ratings.remove(rating);
        this.ratingCount--;
        this.updateAverageRating();
    }

    /**
     * Retrieves all the information related to the product review, including
     * the general information and the individual ratings.
     *
     * @return A string containing all the product review information.
     */
    public String getAllInformation() {
        return this.getGeneralInformation() + "\n\n" + this.getRatingsInformation();
    }

    /**
     * Retrieves the general information about the product review, such as the
     * product ID, name, average rating, and number of ratings.
     *
     * @return A string containing the general product review information.
     */
    public String getGeneralInformation() {
        return "Product ID: " + this.id.toString() +
               "\nProduct ID: " + this.product.getId() +
               "\nProduct name: " + this.product.getName() +
               "\nAverage rating: " + Float.toString(this.averageRating) +
               "\nNumber of ratings: " + Integer.toString(this.ratingCount);
    }

    /**
     * Retrieves the information about all the individual ratings for the product.
     *
     * @return A string containing the information for all product ratings, or
     *         an error message if there are no ratings.
     */
    public String getRatingsInformation() {
        if (this.ratings == null) {
            StringBuilder information = new StringBuilder("ALL RATINGS: \n\n");
            for (ProductRating rating : this.ratings) {
                information.append(rating.getInformation()).append("\n\n");
            }
            return information.toString();
        } else {
            return "EXCEPTION: No ratings";
        }
    }

    /**
     * Updates the average rating for the product based on the current list of
     * individual ratings.
     */
    private void updateAverageRating() {
        if (this.ratingCount == 0) {
            this.averageRating = 0;
        } else {
            int starCount = 0;
            for (ProductRating rating : this.ratings) {
                starCount += rating.getStars();
            }
            this.averageRating = starCount / this.ratingCount;
        }
    }

    // Getters and setters for the class properties
    public Product getProduct() {
        return product;
    }

    public UUID getId() {
        return this.id;
    }

    public int getRatingCount() {
        return this.ratingCount;
    }

    public float getAverageRating() {
        return this.averageRating;
    }

    public ArrayList<ProductRating> getRatings() {
        return ratings;
    }
}