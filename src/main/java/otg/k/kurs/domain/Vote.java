package otg.k.kurs.domain;

import lombok.Data;
import otg.k.kurs.dto.VoteDto;

import javax.persistence.*;

@Data
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    private int vote;

    public Vote(){}

    public Vote(VoteDto voteDto){
        this.id = voteDto.getId();
        this.vote = voteDto.getVote();
        this.user = new User(voteDto.getUsername()); // username is already ID so we haven't make DB query ?
        this.vote = voteDto.getVote();
    }

}
