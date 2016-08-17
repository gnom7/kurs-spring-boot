package otg.k.kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otg.k.kurs.domain.Site;
import otg.k.kurs.domain.Tag;
import otg.k.kurs.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<String> getAllStringTags(){
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(Tag::getTag).collect(Collectors.toList());
    }

//    @Override
//    public void saveTags(List<String> stringTags, Site site){
//
//    }

    @Override
    public Tag getTagFromString(String stringTag){
        Tag tag = tagRepository.findByTag(stringTag);
        if(tag != null) {return tag;}
        tag = new Tag();
        tag.setTag(stringTag);
        tag.setSites(new ArrayList<>());
        return tag;
    }

}
