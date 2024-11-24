package com.example.testt.Controller;

import com.example.testt.ApiResponse.ApiResponse;
import com.example.testt.Model.User;
import com.example.testt.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/getUser")
    public ResponseEntity getUser (){
        ArrayList<User> users=userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }


    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser (@PathVariable String id , @RequestBody @Valid User  user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated= userService.updateUser(id, user);
        if (isUpdated){
            userService.updateUser(id,user);
            return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
        }else return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser (@PathVariable String id ){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted){
            userService.deleteUser(id);
            return  ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
        }else return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

@GetMapping("/byBalance/{balance}")
    public ResponseEntity allUserByBalance (@PathVariable double balance ){
        ArrayList<User> byBalance =userService.allUserByBalance(balance);
        if (userService.allUserByBalance(balance)!=null){
        return ResponseEntity.status(200).body(byBalance);
        }else return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @GetMapping("/userByAge/{age}")
    public ResponseEntity userByAge (@PathVariable int age ){
        ArrayList<User> byAge = userService.userByAge(age);
        if (userService.userByAge(age)!=null){
            return ResponseEntity.status(200).body(byAge);
        }else return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }



}
