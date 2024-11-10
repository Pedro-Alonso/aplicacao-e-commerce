package ECommerceApp.src.Interfaces;

public interface Votable {
    void addVote(int voteValue) throws Exception;
    public void removeVote(ECommerceUser voter) throws Exception;
    int getVoteCount();
}
