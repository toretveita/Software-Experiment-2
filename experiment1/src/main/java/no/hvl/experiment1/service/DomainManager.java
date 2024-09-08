package no.hvl.experiment1.service;

import no.hvl.experiment1.domain.Poll;
import no.hvl.experiment1.domain.User;
import no.hvl.experiment1.domain.Vote;
import no.hvl.experiment1.domain.VoteOption;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class DomainManager {

    // AtomicLong counters for IDs
    private final AtomicLong userIdCounter = new AtomicLong();
    private final AtomicLong pollIdCounter = new AtomicLong();
    private final AtomicLong voteOptionIdCounter = new AtomicLong();
    private final AtomicLong voteIdCounter = new AtomicLong();

    // HashMaps to store objects in memory
    private final Map<Long, User> userMap = new HashMap<>();
    private final Map<Long, Poll> pollMap = new HashMap<>();
    private final Map<Long, VoteOption> voteOptionMap = new HashMap<>();
    private final Map<Long, Vote> voteMap = new HashMap<>();

    // Methods to add and retrieve Users
    public User addUser(User user) {
        long id = userIdCounter.incrementAndGet();
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    public User getUser(Long id) {
        return userMap.get(id);
    }

    // Methods to add and retrieve Polls
    public Poll addPoll(Poll poll) {
        long id = pollIdCounter.incrementAndGet();
        poll.setId(id);
        pollMap.put(id, poll);
        return poll;
    }

    public Poll getPoll(Long id) {
        return pollMap.get(id);
    }

    // Methods to add and retrieve VoteOptions
    public VoteOption getVoteOption(Long id) {
        return voteOptionMap.get(id);
    }

    // Methods to add and retrieve Votes
    public Vote addVote(Long userId, Long pollId, Long voteOptionId) {
        long id = voteIdCounter.incrementAndGet();

        Vote vote = new Vote(userId, pollId, voteOptionId);
        vote.setId(id);

        voteMap.put(id, vote);
        return vote;
    }

    public Vote getVote(Long id) {
        return voteMap.get(id);
    }


    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public List<Poll> getAllPolls() {
        return new ArrayList<>(pollMap.values());
    }

    public void removePoll(Long id) {
        pollMap.remove(id);
    }

    public List<Vote> getVotesForPoll(Long pollId) {
        return voteMap.values().stream()
                .filter(vote -> pollId.equals(vote.getPollId()))
                .collect(Collectors.toList());
    }


    public User getUserbyId(long id){
        return userMap.get(id);
    }

    public Poll getPollbyId(long id){
        return pollMap.get(pollIdCounter.incrementAndGet());
    }

    public VoteOption getVoteOptionbyId(long id){
        return voteOptionMap.get(voteOptionIdCounter.incrementAndGet());
    }


    public VoteOption addVoteOption(String caption, int presentationOrder, Long pollId) {
        long id = voteOptionIdCounter.incrementAndGet();
        VoteOption voteOption = new VoteOption(caption, presentationOrder, pollId);
        voteOption.setId(id);
        voteOptionMap.put(id, voteOption);
        return voteOption;
    }

    public List<VoteOption> getVoteOptionsForPoll(Long pollId) {
        return voteOptionMap.values().stream()
                .filter(option -> option.getPollId().equals(pollId))
                .collect(Collectors.toList());
    }

    public Vote updateVote(Vote vote) {
        voteMap.put(vote.getId(), vote);
        return vote;
    }

}