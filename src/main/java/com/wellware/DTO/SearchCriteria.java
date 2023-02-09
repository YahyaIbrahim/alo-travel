package com.wellware.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class SearchCriteria {
    private String city;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date start;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date end;

}
