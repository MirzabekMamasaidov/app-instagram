package uz.pdp.appinstagram.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appinstagram.entity.Post;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.PostDto;
import uz.pdp.appinstagram.service.PostService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/post")
public class PostController {

    final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    public HttpEntity<?> add(@RequestBody PostDto dto){
        ApiResponse response = postService.add(dto);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/{userId}")
    public HttpEntity<?> get(@PathVariable Integer userId){
        List<Post> posts = postService.get(userId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/onlyVideos/{userId}")
    public HttpEntity<?> getAllByVideos(@PathVariable Integer userId){
        List<Post> onlyVideos = postService.getOnlyVideos(userId);
        return ResponseEntity.ok(onlyVideos);
    }
    @GetMapping("/isTagged/{userId}")
    public HttpEntity<?> getAllByTagged(@PathVariable Integer userId){
        List<Post> allByTagged = postService.getAllByTagged(userId);
        return ResponseEntity.ok(allByTagged);
    }
    @GetMapping("/{postId}")
    public HttpEntity<?> getOne(@PathVariable UUID postId){
        Optional<Post> optionalPost = postService.getOne(postId);
        return ResponseEntity.status(optionalPost.isPresent()?
                HttpStatus.OK:HttpStatus.NO_CONTENT).body(optionalPost.get());
    }
    @PutMapping("/{postId}")
    public HttpEntity<?> edit(@PathVariable UUID postId,@RequestBody PostDto dto){
        ApiResponse response = postService.edit(postId, dto);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }
    @PutMapping("/like/{postId}")
    public HttpEntity<?> addOrDeleteLike(@PathVariable UUID postId,@RequestParam Integer userId){
        ApiResponse apiResponse = postService.addOrDeleteLike(postId, userId);
        return ResponseEntity.status(apiResponse.isSuccess()?
                HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("{postId}")
    public HttpEntity<?> delete(@PathVariable UUID postId){
        ApiResponse response = postService.delete(postId);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

}
