package uz.pdp.appinstagram.service;

import org.springframework.stereotype.Service;
import uz.pdp.appinstagram.repository.StoryRepository;

@Service
public record StoryService(StoryRepository storyRepository) {

}
