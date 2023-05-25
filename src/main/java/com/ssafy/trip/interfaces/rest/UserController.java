package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.failed;
import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.exception.member.NoSuchMemberException;
import com.ssafy.trip.interfaces.rest.dto.Email;
import com.ssafy.trip.interfaces.rest.dto.UserDto;
import com.ssafy.trip.interfaces.rest.dto.UserPasswordUpdateRequest;
import com.ssafy.trip.models.User;
import com.ssafy.trip.services.UserService;
import com.ssafy.trip.web.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService userService;


    public ApiResult<UserDto> findById(@PathVariable long id){
        try {
            return succeed(userService.findById(id).map(UserDto::new).orElseThrow(NoSuchMemberException::new));
        } catch (Exception e) {
            return failed(e);
        }
    }

    @DeleteMapping("/deleteuser/{id}")
    public ApiResult<String> withdrawal(@PathVariable long id){
        System.out.println("삭제"+id);
        try {
            return succeed(userService.delete(id));
        } catch (Exception e) {
            return failed(e);
        }
    }


    @PostMapping("/registeruser")

    public  ApiResult<String> register(@RequestBody User user){
        try {
            return succeed(userService.insert(user));
        } catch (Exception e) {
            System.out.println(e);
            return failed(e);
        }
    }

    @PutMapping("/updateloginuser")
    public  ApiResult<String> update(@RequestBody User user){
        try {
            return succeed(userService.update(user));
        } catch (Exception e) {
            return failed(e);
        }
    }
    @PutMapping("/modifyuserpw")

    public  ApiResult<String> updatePassword(@RequestBody UserPasswordUpdateRequest request){
        try {
            return succeed(userService.updatePassword(request.getId(),request.getPassword()));
        } catch (Exception e) {
            return failed(e);
        }
    }
    @PostMapping("/findbyemail")

    public  ApiResult<UserDto> findByEmail(@RequestBody Email email){
        System.out.println("tests");
        try {
            return succeed(userService.findByEmail(email.getEmail()).map(UserDto::new).orElseThrow(IllegalAccessError::new));
        } catch (Exception e) {
            return failed(e);
        }
    }

}
