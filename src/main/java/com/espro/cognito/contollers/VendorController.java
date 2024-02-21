package com.espro.cognito.contollers;

import com.espro.cognito.model.BaseResponse;
import com.espro.cognito.services.VendorService;
import com.espro.cognito.validation.ValidVid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VendorController {
    private final VendorService vendorService;

    @GetMapping("/unauthenticated")

    public ResponseEntity<BaseResponse> unAuthenticated() {
        return vendorService.getUnAuthenticated();
    }

    @GetMapping("/authenticated")
    public ResponseEntity<BaseResponse> authenticated() {

        return vendorService.getAuthenticated();
    }

    @GetMapping("/vendors")
    public ResponseEntity<BaseResponse> vendorGroup() {
        return vendorService.getVendor();

    }

    @GetMapping("vendors/{vid}")
    public ResponseEntity<BaseResponse> getVendorByVid(@ValidVid @PathVariable String vid) {
        return vendorService.getVendorByVid(vid);
    }
}
