package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account;

import com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions.NoDataFoundException;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.model.BasicResponse;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.model.ClientErrorInformation;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.model.ExtendedResponse;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.model.SourceUpdate;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.utility.DatePatternType;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.delegate.AccountDetailDelegate;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.exceptions.AccountDetailException;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.model.request.AccountDetailRequest;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.model.response.AccountDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Slf4j
public class AccountDetailController {

    SimpleDateFormat fmt = new SimpleDateFormat(DatePatternType.TIMESTAMP_YYYY_MM_DD_T_HH_MM_SS.pattern);

    @Autowired
    AccountDetailDelegate delegate;

    @RequestMapping(value = "/accountDetailExtendedResponse/{userCode}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ExtendedResponse<List<AccountDetailResponse>>> accountDetailExtendedResponse(@PathVariable(name = "userCode") String userCode) throws InvalidParameterException, NoDataFoundException, AccountDetailException {

        log.info("Entering in accountDetail service - PathVariable: [{}]", userCode);

        List<AccountDetailResponse> delegateResult =  null;
        ExtendedResponse<List<AccountDetailResponse>> response = new ExtendedResponse<>();
        try {
            delegateResult= delegate.getAccountDetail(userCode);
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
                response.setLastUpdates(new SourceUpdate("CUSTOMER", "07/06/2019 15:13"));

            } else {
                throw new NoDataFoundException("No data found for request param: "+userCode);
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", response);
        } catch (InvalidParameterException | NoDataFoundException e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);
            throw new AccountDetailException("Error processing request", e);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/accountDetailBasicResponse",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<AccountDetailResponse>>> accountDetailBasicResponse(@RequestBody AccountDetailRequest userCode) throws InvalidParameterException, NoDataFoundException, AccountDetailException {

        log.info("Entering in accountDetail service - PathVariable: [{}]", userCode.getCf());

        List<AccountDetailResponse> delegateResult =  null;
        BasicResponse<List<AccountDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getAccountDetail(userCode.getCf());
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                throw new NoDataFoundException("No data found for request param: "+userCode);
            }
            log.debug("result delegate.getAccountDetail(account) [{}]", response);
        } catch (InvalidParameterException | NoDataFoundException e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);
            throw new AccountDetailException("Error processing request", e);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @ExceptionHandler({AccountDetailException.class})
    public ResponseEntity<ClientErrorInformation> handleServiceException(Exception e) {
        ClientErrorInformation error = new ClientErrorInformation(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
    }
}