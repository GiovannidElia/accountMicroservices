package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account;

import com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions.NoDataFoundException;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.model.BasicResponse;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.model.ClientErrorInformation;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.utility.DatePatternType;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.User;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.delegate.AccountDetailDelegate;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.exceptions.AccountDetailException;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controllers.account.model.response.AccountDetailResponse;
import com.quicktutorial.learnmicroservices.accountMicroservices.utils.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class AccountDetailController {

    SimpleDateFormat fmt = new SimpleDateFormat(DatePatternType.TIMESTAMP_YYYY_MM_DD_T_HH_MM_SS.pattern);

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

    @RequestMapping(value = "/accountDetail/{userCode}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody BasicResponse<List<AccountDetailResponse>> accountDetail(@PathVariable(name = "userCode") String userCode) throws InvalidParameterException, NoDataFoundException, AccountDetailException {

        log.info("Entering in accountDetail service - PathVariable: [{}]", userCode);

        BasicResponse objResponse = new BasicResponse();
        try {
            List<AccountDetailResponse> delegateResult= delegate.getAccountDetail(userCode);
            if (delegateResult.size()!=0){
                objResponse.setData(delegateResult);
                objResponse.setTimestamp(fmt.format(new Date()));
            } else {
                throw new NoDataFoundException("No data found for request param: "+userCode);
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", objResponse);
        } catch (InvalidParameterException | NoDataFoundException e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);
            throw new AccountDetailException("Error processing request", e);
        }

        return objResponse;
    }

    @ExceptionHandler({AccountDetailException.class})
    public ResponseEntity<ClientErrorInformation> handleServiceException(Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
    }
}
