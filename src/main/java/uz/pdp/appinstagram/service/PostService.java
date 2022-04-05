package uz.pdp.appinstagram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appinstagram.entity.Attachment;
import uz.pdp.appinstagram.entity.Like;
import uz.pdp.appinstagram.entity.Post;
import uz.pdp.appinstagram.entity.User;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.PostDto;
import uz.pdp.appinstagram.repository.AttachmentRepository;
import uz.pdp.appinstagram.repository.PostRepository;
import uz.pdp.appinstagram.repository.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public record PostService(AttachmentRepository attachmentRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {

    public ApiResponse add(PostDto dto) {
        Post post = new Post();
        for (Attachment attachment : attachmentRepository.findAllById(dto.getAttachmentIds())) {
            if (attachment==null) {
                return new ApiResponse("Error",false);
            }
        }
            post.setAttachments(attachmentRepository.findAllById(dto.getAttachmentIds()));
        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found",false);
        }
        post.setUser(optionalUser.get());
        if (dto.getDescription()==null) {
            post.setDescription("");
        }
        else {
            post.setDescription(dto.getDescription());
        }

        if (dto.isTagged()) {
            post.setTaggedUsers(userRepository.findAllById(dto.getTaggedUserIds()));
        }
        postRepository.save(post);
        return new ApiResponse("Added",true);
    }

    public List<Post> get(Integer userId) {
        return postRepository.findAllByUserId(userId);
    }

    //getAll only videos
    public List<Post> getOnlyVideos(Integer userId) {
        List<Post> videoPosts  = new ArrayList<>();
        List<Post> posts = postRepository.findAllByUserId(userId);
        for (Post post : posts) {
            for (Attachment attachment : post.getAttachments()) {
                if (attachment.getContentType().contains("video")) {
                    videoPosts.add(post);
                }
            }
        }
        return videoPosts;
    }

    //taggedUser qilingan aynan man un postlar lekin meniki emas
    public List<Post> getAllByTagged(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        return postRepository.findAllByTaggedUsersIs(Collections.singletonList(user));
    }

    public Optional<Post> getOne(UUID postId) {
        return postRepository.findById(postId);
    }

    public ApiResponse edit(UUID postId, PostDto dto) {

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            return new ApiResponse("Not found post",false);
        }
        Post editingPost = optionalPost.get();
        if (dto.getDescription()==null) {
            editingPost.setDescription("");
        }
        else {
            editingPost.setDescription(dto.getDescription());
        }
        postRepository.save(editingPost);
        return new ApiResponse("Edited",true);
    }

    public ApiResponse addOrDeleteLike(UUID postId, Integer userId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            return new ApiResponse("Not found post",false);
        }
        Post editingPost = optionalPost.get();
        List<Like> likeList = editingPost.getLikes();
        for (Like like : editingPost.getLikes()) {
            if (like.getUser().getId().equals(userId)) {
                likeList.remove(like);
                editingPost.setLikes(likeList);
            }
            else {
                likeList.add(like);
                editingPost.setLikes(likeList);
            }
        }
        return new ApiResponse("Like added", true);
    }

    public ApiResponse delete(UUID postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            return new ApiResponse("Not found post",false);
        }
        postRepository.delete(optionalPost.get());
        return new ApiResponse("Deleted",true);
    }




}
