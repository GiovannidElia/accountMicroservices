package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.user;

import com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions.UserNotLoggedException;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.model.ClientErrorInformation;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.User;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.user.delegate.LoginDelegate;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.user.exceptions.UserException;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.user.model.response.UserResponse;
import com.quicktutorial.learnmicroservices.accountMicroservices.utils.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
public class UserController {

    @Autowired
    LoginDelegate delegate;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello everybody";
    }

    /*  testare con PostMan con modalità x-www-form-urlencoded */

    //if pwd is null it will still return a user
    @RequestMapping("/newuser1")
    @ResponseBody
    public String addUser(User user){
        return "User added correctly:" + user.getId() + ", "+ user.getUsername();
    }

    //if pwd is null it will return a JAVA JSR-303 error message thanks to @Valid
    @RequestMapping("/newuser2")
    @ResponseBody
    public String addUserValid(@Valid User user){
        return "User added correctly:" + user.getId() + ", "+ user.getUsername();
    }

    //if pwd is null it will return a JAVA JSR-303 error message thanks to Spring object BindingResult
    @RequestMapping("/newuser3")
    @ResponseBody
    public String addUserValidPlusBinding(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return result.toString();
        }
        return "User added correctly:" + user.getId() + ", "+ user.getUsername();
    }

    //if pwd is null it will return a JAVA JSR-303 error message thanks to Spring object BindingResult
    @RequestMapping("/newuser4")
    @ResponseBody
    public String addUserValidPlusBinding2(User user, BindingResult result){
        /* Spring validation */
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);

        if(result.hasErrors()){
            return result.toString();
        }
        return "User added correctly:" + user.getId() + ", "+ user.getUsername();
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<UserResponse> loginUser(@RequestParam(value ="id") String id, @RequestParam(value="password") @Nullable String pwd) throws InvalidParameterException, UserNotLoggedException {
        //check if user exists in DB -> if exists generate JWT and send back to client
        String jwt=null;
        User delegateResult =  null;

        try {
            delegateResult = delegate.getUserFromDbAndVerifyPassword(id, pwd);
            if(delegateResult.getId()!=null){
                jwt = delegate.createJwt(delegateResult.getId(), delegateResult.getUsername(), delegateResult.getPermission(), new Date());
            }
        } catch (InvalidParameterException | UserNotLoggedException e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);
            throw new UserNotLoggedException("Error processing request", e);
        }

        return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt).body(new UserResponse(HttpStatus.OK.value(), "Success! User logged in!"));
    }

    @ExceptionHandler({UserException.class})
    public ResponseEntity<ClientErrorInformation> handleServiceException(Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
    }
}

