package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class used as the Object tied into the Body of the ResponseEntity.
 * It's important to have this Object because it is composed of server response code and response object.
 * Then, JACKSON LIBRARY automatically convert this JsonResponseBody Object into a JSON response.
 */
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @Getter @Setter
    int status;

    @Getter @Setter
    Object response;
}
