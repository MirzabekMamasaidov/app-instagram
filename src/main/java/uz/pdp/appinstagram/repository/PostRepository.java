package uz.pdp.appinstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appinstagram.entity.Attachment;
import uz.pdp.appinstagram.entity.Post;

import java.util.UUID;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
}
