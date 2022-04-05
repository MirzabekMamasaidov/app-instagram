package uz.pdp.appinstagram.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.CommentDto;
import uz.pdp.appinstagram.service.CommentService;

import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse getAll=commentService.getAll();
        return ResponseEntity.status(getAll.isSuccess()? HttpStatus.OK:HttpStatus.NO_CONTENT).body(getAll);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody CommentDto commentDto){
        ApiResponse apiResponse=commentService.add(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(apiResponse);
    }


    @PutMapping("/like/{commentId}")
    public HttpEntity<?> addOrDelete(@PathVariable UUID commentId,@RequestParam Integer userId){
        ApiResponse response = commentService.addOrDelete(commentId, userId);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }



    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable UUID id){
        ApiResponse delet=commentService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(delet);
    }
}
