package no.hvl.experiment1.controller;
import no.hvl.experiment1.domain.Poll;

import no.hvl.experiment1.domain.VoteOption;
import no.hvl.experiment1.service.DomainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polls/{pollId}/voteOptions")
public class VoteOptionsController {

    @Autowired
    private DomainManager domainManager;

    // Create a new vote option
    @PostMapping
    public ResponseEntity<VoteOption> createVoteOption(@PathVariable Long pollId, @RequestBody VoteOption voteOptionRequest) {
        Poll poll = domainManager.getPoll(pollId);
        if (poll == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        VoteOption createdVoteOption = domainManager.addVoteOption(
                voteOptionRequest.getCaption(),
                voteOptionRequest.getPresentationOrder(),
                pollId
        );
        return new ResponseEntity<>(createdVoteOption, HttpStatus.CREATED);
    }

    // List all vote options
    @GetMapping
    public ResponseEntity<List<VoteOption>> listVoteOptions(@PathVariable Long pollId) {
        Poll poll = domainManager.getPoll(pollId);
        if (poll == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<VoteOption> voteOptions = domainManager.getVoteOptionsForPoll(pollId);
        return new ResponseEntity<>(voteOptions, HttpStatus.OK);
    }
}
