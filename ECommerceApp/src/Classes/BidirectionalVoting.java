package ECommerceApp.src.Classes;


public interface BidirectionalVoting {
    public void addVote(ECommerceUser user, VotingSystem.VoteType type) throws Exception;
    public void removeVote(ECommerceUser user)throws Exception;
    public int getUpVoteCount();
    public int getDownVoteCount();
    public int getTotalVoteCount();
}