package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.failed;
import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.exception.member.NoSuchMemberException;
import com.ssafy.trip.interfaces.rest.dto.UserDto;
import com.ssafy.trip.interfaces.rest.dto.UserPasswordUpdateRequest;
import com.ssafy.trip.models.User;
import com.ssafy.trip.services.UserService;
import com.ssafy.trip.web.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    public ApiResult<String> withdrawal(@PathVariable long id){
        try {
            return succeed(userService.delete(id));
        } catch (Exception e) {
            return failed(e);
        }
    }



    public  ApiResult<String> register(@RequestBody User user){
        try {
            return succeed(userService.insert(user));
        } catch (Exception e) {
            return failed(e);
        }
    }

    public  ApiResult<String> update(@RequestBody User user){
        try {
            return succeed(userService.update(user));
        } catch (Exception e) {
            return failed(e);
        }
    }

    public  ApiResult<String> updatePassword(@RequestBody UserPasswordUpdateRequest request){
        try {
            return succeed(userService.updatePassword(request.getId(),request.getPassword()));
        } catch (Exception e) {
            return failed(e);
        }
    }

}
