package uz.pdp.appinstagram.service;

import org.springframework.stereotype.Service;
import uz.pdp.appinstagram.entity.Follower;
import uz.pdp.appinstagram.entity.User;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.FollowerDto;
import uz.pdp.appinstagram.repository.FollowerRepository;
import uz.pdp.appinstagram.repository.UserRepository;

import java.util.List;

@Service
public class FollowerService {

    final FollowerRepository followerRepository;

    final UserRepository userRepository;

    public FollowerService(FollowerRepository followerRepository, UserRepository userRepository) {
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
    }

    public ApiResponse getAll() {
        List<Follower> all = followerRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse("List bo'sh", false);
        }
        return new ApiResponse("Mana", true, followerRepository.findAll());
    }


    public ApiResponse add(FollowerDto followerDto) {
        for (User user : userRepository.findAll()) {
            if (user.isActive()) {
                for (Follower userFollower : user.getFollowers()) {
                    if (!userFollower.getUser().getId().equals(followerDto.getUserId())) {
                        Follower follower = new Follower();
                        follower.setUser(userFollower.getUser());
                        Follower save = followerRepository.save(follower);
                        return new ApiResponse("Qo'shildi", true, save);
                    }
                }
            }
        }

        return new ApiResponse("Bunday id li user yo'q", false);
    }

    public ApiResponse delet(Integer id) {
        boolean deletByid = followerRepository.deleteById(id);
        if (deletByid) {
            return new ApiResponse("O'chirildi", true);
        }
        return new ApiResponse("Bunday id li followermavjud emas", false);
    }
}


