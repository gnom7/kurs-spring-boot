package otg.k.kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otg.k.kurs.domain.Comment;
import otg.k.kurs.repository.CommentRepository;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void addComment(Comment comment){
        commentRepository.save(comment);
    }

}
