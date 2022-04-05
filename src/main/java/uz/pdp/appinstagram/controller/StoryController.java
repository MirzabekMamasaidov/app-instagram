package uz.pdp.appinstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appinstagram.service.StoryService;

@RestController
@RequestMapping("/story")
public class StoryController {

    @Autowired
    StoryService storyService;


}
