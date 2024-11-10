package ECommerceApp.src.Interfaces;

public interface BidirectionalVoting {
    public void upVote(ECommerceUser voter) throws Exception;
    public void downVote(ECommerceUser voter) throws Exception;
    public void removeVote(ECommerceUser user)throws Exception;
    public int getUpVoteCount();
    public int getDownVoteCount();
    public int getTotalVoteCount();
}