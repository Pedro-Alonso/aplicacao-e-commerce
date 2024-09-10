package ECommerceApp.src.Classes;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ProductRating {
	
	// ATTRIBUTES
	private String comment;
	private ECommerceUser rater;
	private UUID id;
	private LocalDateTime creationDate;
	private LocalDateTime lastEditDate;
	private int stars;
	private ArrayList<Report> reports;
	private ArrayList<ECommerceUser> upVoters;
	private ArrayList<ECommerceUser> downVoters;
	
	
	// CONSTRUCTOR
	/**
     * Constructs a ProductRating object.
     *
     * @param id    		the rating's id
     * @param rater			the user that has created this rating
     * @param comment    	the comment the user created when creating this rating	
     * @param stars 		the number of stars the user has rated
     * @param creationDate  the time of object creation
     * @param lastEditDate  the last time the object's attributes have been changed
     * @param reports 		a list of reports created for this rating
     * @param downVoters	a list of users that have downvoted the rating 
     * @param upVoters		a list of users that have upvoted the rating 
     */
	public ProductRating(String comment, ECommerceUser rater, int stars) 
	{
		this.id = UUID.randomUUID();
		this.stars = stars;
		this.comment = comment;
		this.creationDate = LocalDateTime.now();
		this.lastEditDate = LocalDateTime.now();
		this.rater = rater;
		this.upVoters = new ArrayList<ECommerceUser>();
		this.downVoters = new ArrayList<ECommerceUser>();
	}
	
	
	// METHODS -------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	// Returns the rating's information
	public String getInformation() {
		return  ("Rating ID: " + this.id.toString() +
				"\nRater ID: " + this.rater.getId() + 
				"\nDate: " + this.creationDate.toString() +
				"\nLast changed: " + this.lastEditDate.toString() + 
				"\nStars: " + Integer.toString(this.stars) + 
				"\nComment: " + this.comment);
	}
	
	// METHODS RELATED TO VOTES -------------------------------------------------------------------------------------------------------------
	
	// Logic for managing votes
	public void upVote(ECommerceUser voter) throws Exception {
		
		
		// If the user has not voted yet, add the vote
		if (!this.downVoters.contains(voter) && !this.upVoters.contains(voter)) {
			this.downVoters.add(voter);
		}
		else {
			
			// If the user has already up voted, block this action
			if(this.upVoters.contains(voter)) {
				throw new Exception("The user has already up voted!"); 
			}
			
			// If the user has down voted, its vote will be removed and the up vote will be added
			if(this.downVoters.contains(voter)) {
				this.removeVote(voter);
				this.upVoters.add(voter);
				
			}
		}
		
	}
	
	public void downVote(ECommerceUser downVoter) throws Exception {
		
		
		// If the user has not voted yet, add the vote
		if (!this.downVoters.contains(downVoter) && !this.upVoters.contains(downVoter)) {
			this.downVoters.add(downVoter);
		}
		else {
			
			// If the user has already down voted, block this action
			if(this.downVoters.contains(downVoter)) {
				throw new Exception("The user has already down voted!"); 
			}
			
			// If the user has up voted, its vote will be removed and the down vote will be added
			if(this.upVoters.contains(downVoter)) {
				this.removeVote(downVoter);
				this.downVoters.add(downVoter);
				
			}
		}
		
	}
	
	public void removeVote(ECommerceUser user)throws Exception{
		
		if(this.downVoters.contains(user)) {
			this.downVoters.remove(user);
		}
		else if(this.upVoters.contains(user)) {
			this.upVoters.remove(user);
		}
		else {
			throw new Exception("There are no votes associated with this user");
		}
	}
	
	
	// METHODS RELATED TO REPORTS ------------------------------------------------------------------------------------------------------------
	
	// Returns the required report or null
	public Report getReport(UUID reportId) {
		
		for(Report report : this.reports) {
			
			if(report.getId() == reportId){
				return report;
			}
			
		}
		return null;
	}
	
	public String getReportsInformation() {
	
		String information = "REPORTS INFORMATION: \n\n";
		
		for(Report report : this.reports) {
			information = information + report.getInformation() + "\n\n";
		}
		
		return information;
		
	}
	
	public int getReportCount() {
		return this.reports.size();
	}

	public void addReport(Report report) {
		this.reports.add(report);
	}
	
	public void removeReport(UUID reportId) {
		
		for(Report report : this.reports) {
			
			if(report.getId() == reportId) {
				this.reports.remove(report);
			}
		}
		
	}

	
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
	        this.lastEditDate = LocalDateTime.now();
	    	this.stars = stars;
	    }

	    public LocalDateTime getCreationDate() {
	        return creationDate;
	    }

	    public String getComment() {
	        return comment;
	    }

	    public void setComment(String comment) {
	    	this.lastEditDate = LocalDateTime.now();
	        this.comment = comment;
	    }
	
}
