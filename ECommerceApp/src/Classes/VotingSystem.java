package ECommerceApp.src.Classes;

// Enum to represent vote types
enum VoteType {
    UP_VOTE,
    DOWN_VOTE
}

class VotingSystem {

    // Set instead of arrayList, so that duplicate values are not allowed
    private Set<ECommerceUser> upVoters;
    private Set<ECommerceUser> downVoters;

    // Constructor
    public VotingSystem() {
        this.upVoters = new HashSet<>();
        this.downVoters = new HashSet<>();
    }


    public void vote(ECommerceUser voter, VoteType voteType) throws Exception {
        switch (voteType) {
            case UP_VOTE:
                handleUpVote(voter);
                break;
            case DOWN_VOTE:
                handleDownVote(voter);
                break;
        }
    }

    private void handleUpVote(ECommerceUser voter) throws Exception {
        if (upVoters.contains(voter)) {
            throw new Exception("User has already up voted!");
        }

        if (downVoters.contains(voter)) {
            downVoters.remove(voter);
        }
        upVoters.add(voter);
    }

    private void handleDownVote(ECommerceUser voter) throws Exception {
        if (downVoters.contains(voter)) {
            throw new Exception("User has already down voted!");
        }

        if (upVoters.contains(voter)) {
            upVoters.remove(voter);
        }
        downVoters.add(voter);
    }

    public void removeVote(ECommerceUser user) throws Exception {
        boolean removed = upVoters.remove(user) || downVoters.remove(user);
        if (!removed) {
            throw new Exception("No votes found for this user");
        }
    }

    // Getter methods for vote counts
    public int getUpVoteCount() {
        return upVoters.size();
    }

    public int getDownVoteCount() {
        return downVoters.size();
    }

    public int getTotalVoteCount() {
        return getUpVoteCount() - getDownVoteCount();
    }

    // Methods to check user's voting status
    public boolean hasUserVoted(ECommerceUser user) {
        return upVoters.contains(user) || downVoters.contains(user);
    }
}