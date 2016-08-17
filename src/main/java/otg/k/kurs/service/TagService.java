package otg.k.kurs.service;

import otg.k.kurs.domain.Tag;

import java.util.List;

public interface TagService {
    List<String> getAllStringTags();

    Tag getTagFromString(String stringTag);
}
