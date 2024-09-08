package no.hvl.experiment1.controller;

import no.hvl.experiment1.domain.Poll;
import no.hvl.experiment1.service.DomainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private DomainManager domainManager;

    // Create a new poll
    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll createdPoll = domainManager.addPoll(poll);
        return new ResponseEntity<>(createdPoll, HttpStatus.CREATED);
    }

    // List all polls
    @GetMapping
    public ResponseEntity<List<Poll>> listPolls() {
        List<Poll> polls = new ArrayList<>(domainManager.getAllPolls());
        return new ResponseEntity<>(polls, HttpStatus.OK);
    }

    // Delete a poll
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Long id) {
        domainManager.removePoll(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

