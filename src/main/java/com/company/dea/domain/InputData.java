package com.company.dea.domain;

import lombok.Data;

import java.util.*;

/**
 * @author LZG
 */
@Data
public class InputData {

    private HashSet<Object> dmuCodes = new HashSet<>();

    private HashMap<String, String> dmuCodeToUserName;

    /**
     * 真正用的
     */
    private HashMap<String, String> dmuUserNameToCode;

    private List<String> dmuCodesInAddedOrder = new ArrayList<>();

    private HashSet<Object> categories;

    private HashMap<String, String> coefficients;

    private Set<String> outputCategories;

    private Set<String> inputCategories;


}
