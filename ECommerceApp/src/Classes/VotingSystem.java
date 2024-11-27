package ECommerceApp.src.Classes;

import java.util.Set;
import java.util.HashSet;



/**
 * Represents a voting system that allows users to upvote or downvote items.
 * This class implements the `BidirectionalVoting` interface, which defines the
 * methods for adding, removing, and retrieving vote counts.
 *
 * @author Racciatti
 * @version 1.1
 * @since 04/11/2024
 * @see BidirectionalVoting
 */
public class VotingSystem implements BidirectionalVoting {

    /**
     * Enum to represent the different types of votes that can be cast.
     */
    public enum VoteType {
        UP_VOTE,
        DOWN_VOTE
    }

    /**
     * Set to store the users who have cast an upvote.
     * Using a Set ensures that each user can only upvote once.
     */
    private Set<ECommerceUser> upVoters;

    /**
     * Set to store the users who have cast a downvote.
     * Using a Set ensures that each user can only downvote once.
     */
    private Set<ECommerceUser> downVoters;

    /**
     * Constructs a new `VotingSystem` instance.
     * Initializes the `upVoters` and `downVoters` sets as empty `HashSet`s.
     */
    public VotingSystem() {
        this.upVoters = new HashSet<>();
        this.downVoters = new HashSet<>();
    }

    /**
     * Adds a vote to the voting system.
     *
     * @param voter     The `ECommerceUser` who is casting the vote.
     * @param voteType  The type of vote, either `UP_VOTE` or `DOWN_VOTE`.
     * @throws Exception If the user has already cast the same type of vote.
     */
    public void addVote(ECommerceUser voter, VoteType voteType) throws Exception {
        switch (voteType) {
            case UP_VOTE:
                handleUpVote(voter);
                break;
            case DOWN_VOTE:
                handleDownVote(voter);
                break;
        }
    }

    /**
     * Handles the logic for adding an upvote to the voting system.
     *
     * @param voter The `ECommerceUser` who is casting the upvote.
     * @throws Exception If the user has already cast an upvote.
     */
    private void handleUpVote(ECommerceUser voter) throws Exception {
        if (upVoters.contains(voter)) {
            throw new Exception("User has already up voted!");
        }

        if (downVoters.contains(voter)) {
            downVoters.remove(voter);
        }
        upVoters.add(voter);
    }

    /**
     * Handles the logic for adding a downvote to the voting system.
     *
     * @param voter The `ECommerceUser` who is casting the downvote.
     * @throws Exception If the user has already cast a downvote.
     */
    private void handleDownVote(ECommerceUser voter) throws Exception {
        if (downVoters.contains(voter)) {
            throw new Exception("User has already down voted!");
        }

        if (upVoters.contains(voter)) {
            upVoters.remove(voter);
        }
        downVoters.add(voter);
    }

    /**
     * Removes a vote from the voting system.
     *
     * @param voter The `ECommerceUser` who is removing their vote.
     * @throws Exception If the user has not cast any vote.
     */
    public void removeVote(ECommerceUser voter) throws Exception {
        boolean removed = upVoters.remove(voter) || downVoters.remove(voter);
        if (!removed) {
            throw new Exception("No votes found for this user");
        }
    }

    /**
     * Retrieves the number of upvotes in the voting system.
     *
     * @return The number of upvotes.
     */
    public int getUpVoteCount() {
        return upVoters.size();
    }

    /**
     * Retrieves the number of downvotes in the voting system.
     *
     * @return The number of downvotes.
     */
    public int getDownVoteCount() {
        return downVoters.size();
    }

    /**
     * Calculates the total vote count by subtracting the number of downvotes from
     * the number of upvotes.
     *
     * @return The total vote count.
     */
    public int getTotalVoteCount() {
        return getUpVoteCount() - getDownVoteCount();
    }

    /**
     * Checks if the specified user has cast a vote (either upvote or downvote).
     *
     * @param user The `ECommerceUser` to check.
     * @return `true` if the user has voted, `false` otherwise.
     */
    public boolean hasUserVoted(ECommerceUser user) {
        return upVoters.contains(user) || downVoters.contains(user);
    }
}