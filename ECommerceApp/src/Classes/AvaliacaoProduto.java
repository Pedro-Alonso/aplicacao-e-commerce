package eCommerce;
import java.util.UUID;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class AvaliacaoProduto {
	
	// ATTRIBUTES
	private Produto product;
	private UUID productRatingId;
	private int ratingCount;
	private float averageRating;
	private ArrayList<AvaliacaoPontual> ratings;
	
	// CONSTRUCTOR
	public AvaliacaoProduto() {
		this.productRatingId = UUID.randomUUID();
		this.ratingCount = 0;
		this.averageRating = 0;
	}
	
	
	
	// METHODS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	// METHODS RELATED TO THE MANAGEMENT OF INDIVIDUAL RATINGS ------------------------------------------------------------------------------------------------
	
	// Adding a specific rating to the product rating list
	public void addRating(eCommerceUser rater, int stars, String comment) {
		
		// Adding the new rating to the list of ratings
		AvaliacaoPontual newRating = new AvaliacaoPontual(comment, rater, stars);
		ratings.add(newRating);
		
		// Increasing the rating count
		this.ratingCount++;
		
		// Recalculating the average of ratings and updating it
		this.setAverageRating(this.calculateAverageRating());
	}
	
	// Search and return a specific rating, or null
	public AvaliacaoPontual getRating(UUID ratingId){
		
		// Returning the searched rating
		for(AvaliacaoPontual rating : this.ratings) {
			if(rating.getId() == ratingId) return rating;
		}
		
		// if it is not found, return null
		return null;
	}
	
	// Remove a rating 
	public String removeRating(UUID ratingId) {
		
		// Searching for the rating
		AvaliacaoPontual rating = this.getRating(ratingId);
		
		// If it exists, remove it and decrease the number of total ratings
		if(ratingId != null) {
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
	String getAllInfo() {
		return this.getGeneralInfo() + "\n\n" + this.getRatingsInfo();
	}
	
	// Concatenates and returns the general information regarding the product ratings
	String getGeneralInfo() {
		
		return (
				"Product ID: " + this.productRatingId.toString() +
				"\nProduct ID: " + this.product.getId() + 
				"\nProduct name: " + this.product.getName() + 
				"\n Average rating: " + Float.toString(this.averageRating) +
				"\n Number of ratings: " + Integer.toString(this.ratingCount)
				);
	}
	
	// Concatenates and returns the information of all ratings
	String getRatingsInfo(){
		
		if (this.ratings == null) {
			
			StringBuilder information = new StringBuilder("ALL RATINGS: \n\n");
			for (AvaliacaoPontual rating : this.ratings) {
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
			for(AvaliacaoPontual rating : ratings) {
				starCount += rating.getStars();
			}
			
			return starCount / this.ratingCount;
			
		}
		
	// ========================================================================================================================================================	
		
		
		
	// GETTERS AND SETTERS ------------------------------------------------------------------------------------------------------------------------------------------
	public Produto getProduct() {
        return product;
    }

    public void setProduct(Produto product) {
        this.product = product;
    }

    public UUID getProductRatingId() {
        return productRatingId;
    }

    public void setProductRatingId(UUID productRatingId) {
        this.productRatingId = productRatingId;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public ArrayList<AvaliacaoPontual> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<AvaliacaoPontual> ratings) {
        this.ratings = ratings;
    }
}
