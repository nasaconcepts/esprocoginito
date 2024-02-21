package com.espro.cognito.com.espro.cognito.services.impl;

import com.espro.cognito.model.BaseResponse;
import com.espro.cognito.services.impl.VendorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ContextConfiguration(classes = {VendorServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class VendorServiceImplTest {
    @InjectMocks
    VendorServiceImpl vendorService;

    @Mock
    private SecurityContext securityContext;

    @Test
    @DisplayName("No Token provided but Api Should Pass")
    public void testUnAuthenticated_service() {
        // Invoke the method
        ResponseEntity<BaseResponse> responseEntity = vendorService.getUnAuthenticated();

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Free for all to see", responseEntity.getBody().getMessage());
        assertEquals("Anonymous", responseEntity.getBody().getName());

    }


}
