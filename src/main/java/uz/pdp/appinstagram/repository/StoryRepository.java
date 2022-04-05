package uz.pdp.appinstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appinstagram.entity.Story;

import java.util.UUID;

@Repository
public interface StoryRepository extends JpaRepository<Story, UUID> {


}
