package otg.k.kurs.dto;

import lombok.Data;
import otg.k.kurs.domain.Vote;

@Data
public class VoteDto {

    private long id;

    private String username;

    private long siteId;

    private int vote;

    public VoteDto(){}

    public VoteDto(Vote vote){
        this.id = vote.getId();
        this.username = vote.getUser().getUsername();
        this.vote = vote.getVote();
        this.siteId = vote.getSite().getId();
    }

}
