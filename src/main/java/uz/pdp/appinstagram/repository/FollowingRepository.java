package uz.pdp.appinstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appinstagram.entity.Following;

import java.util.UUID;

@Repository
public interface FollowingRepository extends JpaRepository<Following, UUID> {

    boolean deleteById(Integer id);
}
