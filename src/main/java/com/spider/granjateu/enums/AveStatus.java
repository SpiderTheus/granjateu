package com.spider.granjateu.enums;

public enum AveStatus {
    CRIA, RECRIA, POSTURA, ABATE;

    
    public String getEnumString(AveStatus status) {
        if (status == null) 
            return "";
        return status.name();
     }
}  
