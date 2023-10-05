package com.example.api.enums;

import io.micrometer.common.util.StringUtils;

import java.util.Arrays;

public enum CellphoneNameEnum {
    S9("Samsung Galaxy S9"),
    S8("Samsung Galaxy S8"),
    NEXUS_6("Motorola Nexus 6"),
    ONEPLUS_9("Oneplus 9"),
    IPHONE_13("Apple iPhone 13"),
    IPHONE_12("Apple iPhone 12"),
    IPHONE_11("Apple iPhone 11"),
    IPHONE_X("iPhone X"),
    NOKIA_3310("Nokia 3310");

    private String fullName;

    private CellphoneNameEnum(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public static boolean validName(String phoneName) {
        if (StringUtils.isBlank(phoneName)) {
            return false;
        }
        var optionalName = Arrays.stream(CellphoneNameEnum.values()).filter(cellphoneNameEnum -> cellphoneNameEnum.fullName.equals(phoneName)).findAny();
        return optionalName.isPresent();
    }
}
