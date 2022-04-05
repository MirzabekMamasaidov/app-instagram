package uz.pdp.appinstagram.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.CommentDto;
import uz.pdp.appinstagram.service.CommentService;

import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

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

//    @PutMapping("/{id}")
//    public HttpEntity<?> edit(@PathVariable Integer id , @RequestBody CommentDto commentDto){
//        ApiResponse edit=commentService.edit(id,commentDto);
//        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(edit);
//    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable UUID id){
        ApiResponse delet=commentService.delet(id);
        return ResponseEntity.status(delet.isSuccess()?HttpStatus.OK:HttpStatus.NO_CONTENT).body(delet);
    }
}
