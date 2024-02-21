package com.espro.cognito.services.impl;

import com.espro.cognito.model.BaseResponse;
import com.espro.cognito.services.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendorServiceImpl implements VendorService {


    @Override
    public ResponseEntity<BaseResponse> getUnAuthenticated() {
        var response = BaseResponse.builder()
                .message("Free for all to see")
                .name("Anonymous").build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override


    public ResponseEntity<BaseResponse> getAuthenticated() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authentication.getAuthorities());
        var jwtObj = (Jwt) authentication.getPrincipal();
        var given_name = String.valueOf(jwtObj.getClaims().get("given_name"));
        var family_name = String.valueOf(jwtObj.getClaims().get("family_name"));
        String name = "".concat(given_name).concat(" ").concat(family_name);
        String message = "Looks like you have been authenticated";

        var response = BaseResponse.builder()
                .message(message)
                .name(name)
                .grantedAuthorities(grantedAuthorities)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @Override
    @PreAuthorize("hasAuthority('CGROUP_VENDOR')")
    public ResponseEntity<BaseResponse> getVendor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authentication.getAuthorities());
        var jwtObj = (Jwt) authentication.getPrincipal();

        var given_name = String.valueOf(jwtObj.getClaims().get("given_name"));
        var family_name = String.valueOf(jwtObj.getClaims().get("family_name"));
        String name = "".concat(given_name).concat(" ").concat(family_name);
        String message = "Welcome to the vendor group!";

        var response = BaseResponse.builder()
                .message(message)
                .name(name)
                .grantedAuthorities(grantedAuthorities)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    @PreAuthorize("hasAuthority('CGROUP_VENDOR')")
    public ResponseEntity<BaseResponse> getVendorByVid(String vid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authentication.getAuthorities());
        var jwtObj = (Jwt) authentication.getPrincipal();
        var vendorId = String.valueOf(jwtObj.getClaims().get("sub"));
        var given_name = String.valueOf(jwtObj.getClaims().get("given_name"));
        var family_name = String.valueOf(jwtObj.getClaims().get("family_name"));
        String name = "".concat(given_name).concat(" ").concat(family_name);
        String message = "Looks like you’re a specific vendor!";
        if (Objects.nonNull(vid) && vendorId.equals(vid)) {
            var response = BaseResponse.builder()
                    .message(message)
                    .vid(vendorId)
                    .name(name)
                    .grantedAuthorities(grantedAuthorities)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        var errorResponse = BaseResponse.builder()
                .message("The vid provided is incorrect")
                .build();


        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
