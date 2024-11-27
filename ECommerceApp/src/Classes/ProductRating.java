package ECommerceApp.src.Classes;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a rating for a specific product in an e-commerce application.
 * The `ProductRating` class encapsulates the information related to a product
 * rating, including the user who provided the rating, the number of stars, the
 * comment, and the creation and last edit dates. It also manages the voting
 * system and the reports associated with the rating.
 *
 * @author Racciatti
 * @version 1.2
 * @see Report
 */
public class ProductRating {

    /**
     * The comment provided by the user for the product rating.
     */
    private String comment;

    /**
     * The user who provided the product rating.
     */
    private ECommerceUser rater;

    /**
     * Unique identifier for the product rating.
     */
    private UUID id;

    /**
     * The date and time when the product rating was created.
     */
    private LocalDateTime creationDate;

    /**
     * The date and time when the product rating was last edited.
     */
    private LocalDateTime lastEditDate;

    /**
     * The number of stars (rating) provided by the user.
     */
    private int stars;

    /**
     * List of reports associated with the product rating.
     */
    private ArrayList<Report> reports;

    /**
     * The voting system associated with the product rating.
     */
    private VotingSystem votingSystem;

    /**
     * Constructs a `ProductRating` object with the specified rating information.
     *
     * @param comment The comment provided by the user.
     * @param rater    The user who provided the rating.
     * @param stars    The number of stars (rating) provided by the user.
     */
    public ProductRating(String comment, ECommerceUser rater, int stars) {
        this.id = UUID.randomUUID();
        this.stars = stars;
        this.comment = comment;
        this.creationDate = LocalDateTime.now();
        this.lastEditDate = LocalDateTime.now();
        this.rater = rater;
        this.votingSystem = new VotingSystem();
    }

    /**
     * Retrieves a string containing the information about the product rating.
     *
     * @return A string with the rating information.
     */
    public String getInformation() {
        return "Rating ID: " + this.id.toString() +
               "\nRater ID: " + this.rater.getId() +
               "\nDate: " + this.creationDate.toString() +
               "\nLast changed: " + this.lastEditDate.toString() +
               "\nStars: " + Integer.toString(this.stars) +
               "\nComment: " + this.comment;
    }

    /**
     * Adds an upvote to the product rating.
     *
     * @param voter The user who is casting the upvote.
     * @throws Exception If there is an issue adding the upvote.
     */
    public void upVote(ECommerceUser voter) throws Exception {
        votingSystem.addVote(voter, VotingSystem.VoteType.UP_VOTE);
    }

    /**
     * Adds a downvote to the product rating.
     *
     * @param voter The user who is casting the downvote.
     * @throws Exception If there is an issue adding the downvote.
     */
    public void downVote(ECommerceUser voter) throws Exception {
        votingSystem.addVote(voter, VotingSystem.VoteType.DOWN_VOTE);
    }

    /**
     * Removes a vote (either upvote or downvote) from the product rating.
     *
     * @param user The user who is removing their vote.
     * @throws Exception If there is an issue removing the vote.
     */
    public void removeVote(ECommerceUser user) throws Exception {
        votingSystem.removeVote(user);
    }

    /**
     * Retrieves a specific report associated with the product rating.
     *
     * @param reportId The unique identifier of the report.
     * @return The `Report` object, or `null` if not found.
     */
    public Report getReport(UUID reportId) {
        for (Report report : this.reports) {
            if (report.getId() == reportId) {
                return report;
            }
        }
        return null;
    }

    /**
     * Retrieves a string containing the information about all the reports
     * associated with the product rating.
     *
     * @return A string with the reports information.
     */
    public String getReportsInformation() {
        String information = "REPORTS INFORMATION: \n\n";
        for (Report report : this.reports) {
            information += report.getInformation() + "\n\n";
        }
        return information;
    }

    /**
     * Retrieves the number of reports associated with the product rating.
     *
     * @return The number of reports.
     */
    public int getReportCount() {
        return this.reports.size();
    }

    /**
     * Adds a new report to the list of reports associated with the product rating.
     *
     * @param report The new report to be added.
     */
    public void addReport(Report report) {
        this.reports.add(report);
    }

    /**
     * Removes a specific report from the list of reports associated with the
     * product rating.
     *
     * @param reportID The unique identifier of the report to be removed.
     */
    public void removeReport(UUID reportID) {
        Report report = this.getReport(reportID);
        if (report != null) {
            this.reports.remove(report);
        }
    }

    // Getters and setters for the class properties
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

    public int getUpVoteCount() {
        return votingSystem.getUpVoteCount();
    }

    public int getDownVoteCount() {
        return votingSystem.getDownVoteCount();
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
