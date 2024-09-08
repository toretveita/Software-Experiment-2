package no.hvl.experiment1.domain;
import no.hvl.experiment1.service.DomainManager;

import java.time.Instant;

public class Vote {
    private long id;
    private long userid;
    private long voteOptionid;
    private long pollid;

    public Vote(long userid, long pollId, long voteOptionId) {
        this.userid = userid;
        this.pollid = pollId;
        this.voteOptionid = voteOptionId;
    }


    //Get / set

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getUserid() {
        return this.userid;
    }
    public void setVoteOption(long voteOption) {
        this.voteOptionid = voteOption;
    }
    public long getVoteOptionId() {
        return voteOptionid;
    }

    public long getPollId() {
        return pollid;
    }

    public void setPollId(long poll) {
        this.pollid = poll;
    }

    public void setVoteOptionId(long voteOption) {
        this.voteOptionid = voteOption;
    }

}
