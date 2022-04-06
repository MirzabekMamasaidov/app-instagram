package uz.pdp.appinstagram.service;


import org.springframework.stereotype.Service;
import uz.pdp.appinstagram.entity.Comment;
import uz.pdp.appinstagram.entity.Like;
import uz.pdp.appinstagram.entity.Post;
import uz.pdp.appinstagram.entity.User;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.CommentDto;
import uz.pdp.appinstagram.repository.CommentRepository;
import uz.pdp.appinstagram.repository.PostRepository;
import uz.pdp.appinstagram.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    final CommentRepository commentRepository;

    final PostRepository postRepository;
    final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    public ApiResponse getAll() {
        List<Comment> all = commentRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }


    public ApiResponse add(CommentDto commentDto) {

        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent()) {
            return new ApiResponse("Post not found", false);
        }

        Optional<User> optionalUser = userRepository.findById(commentDto.getUserId());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found", false);
        }

        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setPost(optionalPost.get());
        comment.setUser(optionalUser.get());
        comment.setLikes(commentDto.getLikes());
        Comment save = commentRepository.save(comment);
        return new ApiResponse("Added", true, save);
    }


    public ApiResponse delet(UUID id) {

        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent()) {
            return new ApiResponse("Bunday id li comment mavjud emas", false);
        }
        commentRepository.deleteById(id);
        return new ApiResponse("O'chirildi", true);
    }

    public ApiResponse addOrDelete(UUID commentId, Integer userId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (!optionalComment.isPresent()) {
            return new ApiResponse("Not found comment", false);
        }
        Comment editingComment = optionalComment.get();
        List<Like> likeList = editingComment.getLikes();
        for (Like like : editingComment.getLikes()) {
            if (like.getUser().getId().equals(userId)) {
                likeList.remove(like);
                editingComment.setLikes(likeList);
            } else {
                likeList.add(like);
                editingComment.setLikes(likeList);
            }
        }
        return new ApiResponse("Like added or deleted", true);
    }
}
