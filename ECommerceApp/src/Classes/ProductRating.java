package ECommerceApp.src.Classes;
import java.util.UUID;
import java.time.LocalDateTime;

public class ProductRating {
	
	// ATTRIBUTES
	private String comment;
	private ECommerceUser rater;
	private UUID id;
	private LocalDateTime creationDate;
	private int stars;
	
	// CONSTRUCTOR
	public ProductRating(String comment, ECommerceUser rater, int stars) 
	{
		this.id = UUID.randomUUID();
		this.stars = stars;
		this.comment = comment;
		this.creationDate = LocalDateTime.now();
		this.rater = rater;
	}
	
	
	// METHODS -------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	// Returns the rating's information
	public String getInfo() {
		return  ("Rating ID: " + this.id.toString() +
				"\nRater ID: " + this.rater.getId() + 
				"\nDate: " + this.creationDate.toString() +
				"\nStars: " + Integer.toString(this.stars) + 
				"\nComment: " + this.comment);
	}
	
	
	// =========================================================================================================================================================
	
	// GETTERS AND SETTERS --------------------------------------------------------------------------------------------------------------------------------------

	    public UUID getId() {
	        return id;
	    }

	    public ECommerceUser getRater() {
	        return rater;
	    }

	    public int getStars() {
	        return stars;
	    }

	    public void setStars(int stars) {
	        this.stars = stars;
	    }

	    public LocalDateTime getCreationDate() {
	        return creationDate;
	    }

	    public String getComment() {
	        return comment;
	    }

	    public void setComment(String comment) {
	        this.comment = comment;
	    }
	
}
