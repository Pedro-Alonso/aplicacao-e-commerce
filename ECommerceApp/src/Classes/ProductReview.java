package ECommerceApp.src.Classes;
import java.util.UUID;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class ProductReview {
	
	// ATTRIBUTES
	private Product product;
	private UUID id;
	private int ratingCount;
	private float averageRating;
	private ArrayList<ProductRating> ratings;
	
	// CONSTRUCTOR
	public ProductReview() {
		this.id = UUID.randomUUID();
		this.ratingCount = 0;
		this.averageRating = 0;
	}

	// METHODS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	// METHODS RELATED TO THE MANAGEMENT OF INDIVIDUAL RATINGS ------------------------------------------------------------------------------------------------
	
	// Adding a specific rating to the product rating list
	public void addRating(ECommerceUser rater, int stars, String comment) {
		
		// Adding the new rating to the list of ratings
		ProductRating newRating = new ProductRating(comment, rater, stars);
		ratings.add(newRating);
		
		// Increasing the rating count
		this.ratingCount++;
		
		// Recalculating the average of ratings and updating it
		this.averageRating = this.calculateAverageRating();
	}
	
	// Search and return a specific rating, or null
	public ProductRating getRating(UUID id){
		
		// Returning the searched rating
		for(ProductRating rating : this.ratings) {
			if(rating.getId() == id) return rating;
		}
		
		// if it is not found, return null
		return null;
	}
	
	// Remove a rating 
	public String removeRating(UUID id) {
		
		// Searching for the rating
		ProductRating rating = this.getRating(id);
		
		// If it exists, remove it and decrease the number of total ratings
		if(id != null) {
			this.ratings.remove(rating);
			this.ratingCount--;
			return "success";
		}
		
		// Otherwise, return an exception
		return "EXCEPTION: Rating does not exist";
	}
	// =========================================================================================================================================================
	
	
	
	// METHODS THAT CONCATENATE AND RETURN SPECIFIC INFORMATION -----------------------------------------------------------------------------------------------	
	
	// Returns all information regarding both the product rating and the individual ratings
	public String getAllInfo() {
		return this.getGeneralInfo() + "\n\n" + this.getRatingsInfo();
	}
	
	// Concatenates and returns the general information regarding the product ratings
	public String getGeneralInfo() {
		
		return (
				"Product ID: " + this.id.toString() +
				"\nProduct ID: " + this.product.getId() + 
				"\nProduct name: " + this.product.getName() + 
				"\n Average rating: " + Float.toString(this.averageRating) +
				"\n Number of ratings: " + Integer.toString(this.ratingCount)
				);
	}
	
	// Concatenates and returns the information of all ratings
	public String getRatingsInfo(){
		
		if (this.ratings == null) {
			
			StringBuilder information = new StringBuilder("ALL RATINGS: \n\n");
			for (ProductRating rating : this.ratings) {
			information.append(rating.getInfo()).append("\n\n");}
			return information.toString();
		}
		else return "EXCEPTION: No ratings";
	}
	
	// ========================================================================================================================================================
	
	// OTHER METHODS ------------------------------------------------------------------------------------------------------------------------------------------
	
	// Calculating the average rating for the given product
		private float calculateAverageRating() {

			int starCount = 0;
			for(ProductRating rating : ratings) {
				starCount += rating.getStars();
			}
			
			return starCount / this.ratingCount;
			
		}
		
	// ========================================================================================================================================================	
		
	// GETTERS AND SETTERS ------------------------------------------------------------------------------------------------------------------------------------------
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
