package uz.pdp.appinstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appinstagram.entity.Follower;

import java.util.UUID;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, UUID> {
    boolean deleteById(Integer id);

}
