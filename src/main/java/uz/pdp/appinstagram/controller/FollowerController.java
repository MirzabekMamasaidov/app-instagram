package uz.pdp.appinstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appinstagram.entity.Follower;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.FollowerDto;
import uz.pdp.appinstagram.payload.FollowingDto;
import uz.pdp.appinstagram.repository.FollowerRepository;
import uz.pdp.appinstagram.repository.FollowingRepository;
import uz.pdp.appinstagram.service.FollowerService;

@RestController
@RequestMapping("/follower")
public class FollowerController {

    @Autowired
    FollowerService followerService;

    @Autowired
    FollowerRepository followerRepository;


    @GetMapping
    public HttpEntity<?> getALl(){
        ApiResponse apiResponse=followerService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping
    public  HttpEntity<?> add(@RequestBody FollowerDto followerDto){
        ApiResponse add=followerService.add(followerDto);
        return ResponseEntity.status(add.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(add);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse delet = followerService.delet(id);
        return  ResponseEntity.status(delet.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(delet);
    }

}
