package com.trade.email;

public enum  EmailTemplate {
    ACTIVATATE_ACCOUNT ("activate_account");

    private final String templateName;


    EmailTemplate(String templateName) {
        this.templateName = templateName;
    }



}
