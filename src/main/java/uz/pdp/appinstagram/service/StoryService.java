package uz.pdp.appinstagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appinstagram.repository.StoryRepository;

@Service
public class StoryService {

    @Autowired
    StoryRepository storyRepository;



}
