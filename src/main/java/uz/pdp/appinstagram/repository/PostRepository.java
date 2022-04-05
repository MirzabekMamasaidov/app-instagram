package uz.pdp.appinstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appinstagram.entity.Attachment;
import uz.pdp.appinstagram.entity.Post;
import uz.pdp.appinstagram.entity.User;

import java.util.List;
import java.util.UUID;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByUserId(Integer user_id);

    List<Post> findAllByTaggedUsersIs(List<User> taggedUsers);
}
