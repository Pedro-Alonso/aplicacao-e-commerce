package ECommerceApp.src.Classes;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ECommerceApp.src.Interfaces.BidirectionalVoting;

public class ProductRating { 
	
	// ATTRIBUTES
	private String comment;
	private ECommerceUser rater;
	private UUID id;
	private LocalDateTime creationDate;
	private LocalDateTime lastEditDate;
	private int stars;
	private ArrayList<Report> reports;
	private VotingSystem votingSystem;
	
	
	// CONSTRUCTOR
	/**
     * Constructs a ProductRating object.
     *
     * @param rater			the user that has created this rating
     * @param comment    	the comment the user created when creating this rating	
     * @param stars 		the number of stars the user has rated
     */
	public ProductRating(String comment, ECommerceUser rater, int stars) 
	{
		this.id = UUID.randomUUID();
		this.stars = stars;
		this.comment = comment;
		this.creationDate = LocalDateTime.now();
		this.lastEditDate = LocalDateTime.now();
		this.rater = rater;
		this.votingSystem = new VotingSystem();
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
	
	
	public void upVote(ECommerceUser voter) throws Exception {
		votingSystem.vote(voter, UP_VOTE);
		
	}

	public void downVote(ECommerceUser voter) throws Exception {
		votingSystem.vote(voter, DOWN_VOTE)
		
	}
	
	public void removeVote(ECommerceUser user)throws Exception{
		votingSystem.removeVote(user);
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
		
		Report report = this.getReport(reportID);
		if(report != null) this.reports.remove(report);
		 
	}

	
	// GETTERS AND SETTERS --------------------------------------------------------------------------------------------------------------------------------------

	// GETTERS 

	    public UUID getId() {
	        return id;
	    }

	    public ECommerceUser getRater() {
	        return rater;
	    }

	    public int getStars() {
	        return stars;
	    }
		
		public LocalDateTime getCreationDate() {
			return creationDate;
		}

		public String getComment() {
			return comment;
		}

		// Getter methods for vote counts
		public int getUpVoteCount() {
			return votingSystem.getUpVoteCount();
		}
	
		public int getDownVoteCount() {
			return votingSystem.getDownVoteCount()
		}
	
		public int getTotalVoteCount() {
			return votingSystem.getTotalVoteCount();
		}

	    public void setStars(int stars) {
	        this.lastEditDate = LocalDateTime.now();
	    	this.stars = stars;
	    }

	    public void setComment(String comment) {
	    	this.lastEditDate = LocalDateTime.now();
	        this.comment = comment;
	    }


	
}
