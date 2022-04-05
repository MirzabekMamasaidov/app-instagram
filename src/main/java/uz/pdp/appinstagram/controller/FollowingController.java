package uz.pdp.appinstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appinstagram.entity.Following;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.FollowingDto;
import uz.pdp.appinstagram.repository.FollowingRepository;
import uz.pdp.appinstagram.service.FollowingService;

@RestController
@RequestMapping("/following")
public class FollowingController {

    @Autowired
    FollowingService followingService;


    @Autowired
    FollowingRepository followingRepository;


    @GetMapping
    public HttpEntity<?> getALl(){
        ApiResponse apiResponse=followingService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping
    public  HttpEntity<?> add(@RequestBody FollowingDto followingDto){
        ApiResponse add=followingService.add(followingDto);
        return ResponseEntity.status(add.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(add);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id){
        ApiResponse delet = followingService.delet(id);
        return  ResponseEntity.status(delet.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(delet);
    }
}
