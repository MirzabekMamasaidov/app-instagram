package uz.pdp.appinstagram.service;

import org.springframework.stereotype.Service;
import uz.pdp.appinstagram.repository.StoryRepository;

@Service
public class StoryService {

    final StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }
}
