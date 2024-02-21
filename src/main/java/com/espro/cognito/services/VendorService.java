package com.espro.cognito.services;

import com.espro.cognito.model.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;


public interface VendorService {
    ResponseEntity<BaseResponse> getUnAuthenticated();
    ResponseEntity<BaseResponse> getAuthenticated();
    ResponseEntity<BaseResponse> getVendor();
    ResponseEntity<BaseResponse> getVendorByVid(String vid);




}
