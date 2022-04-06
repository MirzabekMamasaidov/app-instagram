package uz.pdp.appinstagram.service;


import org.springframework.stereotype.Service;
import uz.pdp.appinstagram.entity.Following;
import uz.pdp.appinstagram.entity.User;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.FollowingDto;
import uz.pdp.appinstagram.repository.FollowingRepository;
import uz.pdp.appinstagram.repository.UserRepository;

import java.util.List;

@Service
public class FollowingService {

    final FollowingRepository followingRepository;
    final UserRepository userRepository;

    public FollowingService(FollowingRepository followingRepository, UserRepository userRepository) {
        this.followingRepository = followingRepository;
        this.userRepository = userRepository;
    }

    public ApiResponse getAll() {
        List<Following> all = followingRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse("List bo'sh", false);
        }
        return new ApiResponse("Mana", true, followingRepository.findAll());
    }


    public ApiResponse add(FollowingDto followingDto) {
        for (User user : userRepository.findAll()) {
            if (user.isActive()) {
                for (Following userFollowing : user.getFollowings()) {
                    if (!userFollowing.getUser().getId().equals(followingDto.getUserId())) {
                        Following following = new Following();
                        following.setUser(userFollowing.getUser());
                        Following save = followingRepository.save(following);
                        return new ApiResponse("Qo'shildi", true, save);
                    }
                }
            }
        }

        return new ApiResponse("Bunday id li user yo'q", false);
    }

    public ApiResponse delet(Integer id) {
        boolean deletByid = followingRepository.deleteById(id);
        if (deletByid) {
            return new ApiResponse("O'chirildi", true);
        }
        return new ApiResponse("Bunday id li following mavjud emas", false);
    }
}
