package no.hvl.experiment1.controller;

import no.hvl.experiment1.domain.Poll;
import no.hvl.experiment1.domain.User;
import no.hvl.experiment1.domain.Vote;
import no.hvl.experiment1.domain.VoteOption;
import no.hvl.experiment1.service.DomainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polls/{pollId}/votes")
public class VoteController {

    @Autowired
    private DomainManager domainManager;

    // Cast a vote
    @PostMapping
    public ResponseEntity<Vote> castVote(@PathVariable Long pollId, @RequestBody Vote vote) {
        Poll poll = domainManager.getPoll(pollId);
        if (poll == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        VoteOption voteOption = domainManager.getVoteOption(vote.getVoteOptionId());
        if (voteOption == null || !voteOption.getPollId().equals(pollId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Vote createdVote = domainManager.addVote(
        vote.getUserid(),
        pollId,
                vote.getVoteOptionId());
        return new ResponseEntity<>(createdVote, HttpStatus.CREATED);
    }



    // List votes
    @GetMapping
    public ResponseEntity<List<Vote>> listVotes(@PathVariable Long pollId) {
        List<Vote> votes = domainManager.getVotesForPoll(pollId);
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }


    @PutMapping("/{voteId}")
    public ResponseEntity<Vote> changeVote(@PathVariable Long pollId, @PathVariable Long voteId, @RequestBody Vote updatedVote) {
        Vote existingVote = domainManager.getVote(voteId);

        if (existingVote == null || existingVote.getPollId() != pollId) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        VoteOption voteOption = domainManager.getVoteOption(updatedVote.getVoteOptionId());
        if (voteOption == null || !voteOption.getPollId().equals(pollId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        existingVote.setVoteOptionId(updatedVote.getVoteOptionId());
        domainManager.updateVote(existingVote);

        return new ResponseEntity<>(existingVote, HttpStatus.OK);
    }

}
