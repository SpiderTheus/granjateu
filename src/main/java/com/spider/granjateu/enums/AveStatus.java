package com.spider.granjateu.enums;

import com.spider.granjateu.services.exceptions.StatusInvalidException;

public enum AveStatus {
    CRIA, RECRIA, POSTURA, ABATE;

    
    public static String getEnumString(AveStatus status) {
        if (status == null) 
            return "";
        return status.name();
     }

     public static AveStatus parseStatus(String status) {
     try {
        return AveStatus.valueOf(status.toUpperCase());

    } catch (IllegalArgumentException e) {
            throw new StatusInvalidException(status);
    }
    }
}  
