package com.projectmanager.app.service;

import com.projectmanager.app.model.Tag;
import com.projectmanager.app.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));
    }

    public Tag createTag(String name, String color) {
        if (tagRepository.existsByName(name)) {
            throw new RuntimeException("Tag already exists: " + name);
        }

        Tag tag = new Tag();
        tag.setName(name);
        tag.setColor(color != null ? color : "#3b82f6");

        return tagRepository.save(tag);
    }

    public Tag updateTag(Long id, String name, String color) {
        Tag tag = getTagById(id);

        if (!tag.getName().equals(name) && tagRepository.existsByName(name)) {
            throw new RuntimeException("Tag already exists: " + name);
        }

        tag.setName(name);
        if (color != null) {
            tag.setColor(color);
        }

        return tagRepository.save(tag);
    }

    public void deleteTag(Long id) {
        Tag tag = getTagById(id);
        tagRepository.delete(tag);
    }
}
