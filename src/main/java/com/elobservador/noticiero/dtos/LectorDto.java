package com.elobservador.noticiero.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LectorDto {

    private String id;
    private String name;
    private Integer document;
    private Integer age;
    private String email;
    private String password;
    private String nickName;
    private String address;
    private MultipartFile archivo;


}
