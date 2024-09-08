package no.hvl.experiment1.domain;

public class VoteOption {

    private long id;
    private String caption;
    private int presentationOrder;
    private Long pollId;

    public VoteOption(String caption, int presentationOrder, Long pollId) {
        this.caption = caption;
        this.presentationOrder = presentationOrder;
        this.pollId = pollId;
    }

    //get / set




    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public int getPresentationOrder() {
        return presentationOrder;
    }
    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

}
