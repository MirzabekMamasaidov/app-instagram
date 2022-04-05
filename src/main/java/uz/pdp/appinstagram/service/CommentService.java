package uz.pdp.appinstagram.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appinstagram.entity.Comment;
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

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;


    public ApiResponse getAll() {
        List<Comment> all = commentRepository.findAll();
        return new ApiResponse("Mana",true,all);
    }


    public ApiResponse add(CommentDto commentDto) {

        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent()) {
            return new ApiResponse("Post mavjud emas",false);
        }

        Optional<User> optionalUser = userRepository.findById(commentDto.getUserId());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User mavjud emas",false);
        }

        Comment comment=new Comment();
        comment.setText(commentDto.getText());
        comment.setPost(optionalPost.get());
        comment.setUser(optionalUser.get());
        comment.setLikes(commentDto.getLikes());
        Comment save = commentRepository.save(comment);
        return  new ApiResponse("Qo'shildi",true,save);
    }


    public ApiResponse delet(UUID id) {

        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent()) {
            return new ApiResponse("Bunday id li comment mavjud emas",false);
        }
        commentRepository.deleteById(id);
        return new ApiResponse("O'chirildi",true);
    }
}
