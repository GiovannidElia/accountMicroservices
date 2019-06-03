package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account;

import com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions.NoDataFoundException;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.utility.Constant;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.User;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.delegate.AccountDetailDelegate;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.exceptions.AccountDetailException;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.body.AccountDetailResponseBody;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.header.AccountDetailResponseObject;
import com.quicktutorial.learnmicroservices.accountMicroservices.utils.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.InvalidParameterException;

@Controller
@Slf4j
public class AccountDetailController {

    @Autowired
    AccountDetailDelegate delegate;

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

    @RequestMapping(value = "/accountDetail/{account}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountDetailResponseObject accountDetail(@PathVariable(name = "account") String account) throws InvalidParameterException, NoDataFoundException, AccountDetailException {

        log.debug("Entering in accountDetail service - PathVariable: [{}]", account);

        AccountDetailResponseObject objResponse = new AccountDetailResponseObject();
        objResponse.setTypeOp(new Object(){}.getClass().getEnclosingMethod().getName());
        objResponse.setStatusOp(Constant.RESPONSE_STATUS.STATUS_OP_OK);
        objResponse.setMsgOp("MSG OP");

        AccountDetailResponseBody body= delegate.getAccountDetail(account);

        log.debug("result delegate.getAccountDetail(account) [{}]", body);

        objResponse.setValue(body);

        return objResponse;
    }


}
