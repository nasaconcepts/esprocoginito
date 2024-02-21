package com.espro.cognito.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {
    private String message;
    private String name;
    private List<GrantedAuthority> grantedAuthorities;
    private String vid;
    private Object principal;

}
