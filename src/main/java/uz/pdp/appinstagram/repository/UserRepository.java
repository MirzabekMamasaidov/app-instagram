package uz.pdp.appinstagram.repository;

//import com.example.soliqjwttask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appinstagram.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
