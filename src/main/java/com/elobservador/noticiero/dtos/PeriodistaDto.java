package com.elobservador.noticiero.dtos;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PeriodistaDto {

    private String id;
    private String name;
    private Integer document;
    private Integer age;
    private String email;
    private String password;
    private String nickName;
    private String address;
    @JsonSerialize
    private MultipartFile archivo;
    private Integer matricula;
}
