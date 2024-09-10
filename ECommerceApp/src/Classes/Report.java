package ECommerceApp.src.Classes;
import java.time.LocalDateTime;
import java.util.UUID;

public class Report {
	
	private UUID id;
	private ECommerceUser reporter;
	private String reason;
	private String description;
	private LocalDateTime creationDate;	
	private LocalDateTime lastEditDate;
	
	// CONSTRUCTOR
	/**
     * Constructs a Report object.
     *
     * @param id    		the report's id
     * @param reporter 		the user that has reported the rating
     * @param reason		the reporter's reason for the report
     * @param description   Further information on the report provided by the reporter
     * @param creationDate  the time of object creation
     * @param lastEditDate  the last time the object's attributes have been changed
     */
	public Report(String description, String reason, ECommerceUser reporter) {
		
		this.id = UUID.randomUUID();
		this.reason = reason;
		this.reporter = reporter;
		this.description = description;
		this.creationDate = LocalDateTime.now();
		this.lastEditDate = LocalDateTime.now();
		
	}
	
	// METHODS -----------------------------------------------------------------------------------------------------------------
	
	// Returns general information on the report
	public String getInformation() {
		
		return (
				"\nReporter ID: " + reporter.getId().toString() + 
				"\nReson: " + this.reason +
				"\nDescription: " + this.description +
				"\nCreation Date: " + this.creationDate.toString() +
				"\nLast edit: " + this.lastEditDate.toString()
				);
	}
	
	// GETTERS AND SETTERS -----------------------------------------------------------------------------------------------------------------
    public UUID getId() {
        return this.id;
    }

    public ECommerceUser getReporter() {
        return this.reporter;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
    	this.lastEditDate = LocalDateTime.now();
        this.reason = reason;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
    	this.lastEditDate = LocalDateTime.now();
        this.description = description;
    }

    public LocalDateTime getDate() {
        return this.creationDate;
    }

}

