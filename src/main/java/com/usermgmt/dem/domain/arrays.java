package com.usermgmt.dem.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "arrays")
public class arrays implements Serializable {

    private String _id;

    private String title;

    private String problemstatement;

    private List<Example> examples;

    private List<String>  constraints;

    private List<String> images;

    private List<TestCase> testcases;





}
