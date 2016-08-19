package otg.k.kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otg.k.kurs.domain.Vote;
import otg.k.kurs.repository.VoteRepository;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote save(Vote vote){
        return voteRepository.save(vote);
    }

}
