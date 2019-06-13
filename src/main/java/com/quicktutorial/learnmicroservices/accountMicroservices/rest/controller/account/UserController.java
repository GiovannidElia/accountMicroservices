package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account;

import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.User;
import com.quicktutorial.learnmicroservices.accountMicroservices.utils.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

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

}