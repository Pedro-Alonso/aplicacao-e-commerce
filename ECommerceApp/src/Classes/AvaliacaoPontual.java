package eCommerce;
import java.util.UUID;
import java.time.LocalDateTime;

// NOTA: Ja está com o básico para funcionar, mas vou adicionar as funcionalidades de reports e upvotes/downvotes nas avaliações

public class AvaliacaoPontual {
	
	// ATTRIBUTES
	private String comment;
	private eCommerceUser rater;
	private UUID ratingId;
	private LocalDateTime ratingDate;
	private int stars;
	
	// CONSTRUCTOR
	public AvaliacaoPontual(String comment, eCommerceUser rater, int stars) 
	{
		this.ratingId = UUID.randomUUID();
		this.stars = stars;
		this.comment = comment;
		this.ratingDate = LocalDateTime.now();
		this.rater = rater;
	}
	
	
	// METHODS -------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	// Returns the rating's information
	String getInfo() {
		return  ("Rating ID: " + this.ratingId.toString() +
				"\nRater ID: " + this.rater.getId() + 
				"\nDate: " + this.ratingDate.toString() +
				"\nStars: " + Integer.toString(this.stars) + 
				"\nComment: " + this.comment);
	}
	
	
	// =========================================================================================================================================================
	
	// This will be used later on, when not all getters and setters are available due to encapsulation requirements
	
	// GETTERS AND SETTERS --------------------------------------------------------------------------------------------------------------------------------------

	    public UUID getId() {
	        return ratingId;
	    }

	    public void setId(UUID ratingId) {
	        this.ratingId = ratingId;
	    }

	    public eCommerceUser getRater() {
	        return rater;
	    }

	    public void setRater(eCommerceUser rater) {
	        this.rater = rater;
	    }

	    public int getStars() {
	        return stars;
	    }

	    public void setStars(int stars) {
	        this.stars = stars;
	    }

	    public LocalDateTime getRatingDate() {
	        return ratingDate;
	    }

	    public void setRatingDate(LocalDateTime ratingDate) {
	        this.ratingDate = ratingDate;
	    }

	    public String getComment() {
	        return comment;
	    }

	    public void setComment(String comment) {
	        this.comment = comment;
	    }
	
}
