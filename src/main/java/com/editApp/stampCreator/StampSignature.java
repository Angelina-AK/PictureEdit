package com.editApp.stampCreator;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class StampSignature {
    private String m_signatureName;
    private String m_signature;
    private String m_signatureTime;

    public StampSignature(@org.jetbrains.annotations.NotNull String signatureName, @org.jetbrains.annotations.NotNull String signature, String signatureTime) {
        this.m_signatureName = signatureName;
        this.m_signature = signature;

        if (signatureTime.isEmpty()){
            ZonedDateTime currentTime = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss 'GMT'XXX");

            this.m_signatureTime = currentTime.format(formatter);
        }
        else {
            this.m_signatureTime = signatureTime;
        }

    }

//    public StampSignature(@org.jetbrains.annotations.NotNull String signature, @org.jetbrains.annotations.NotNull String signatureName) {
//        this.m_signature = signature;
//        this.m_signatureName = signatureName;
//
//        ZonedDateTime  currentTime = ZonedDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss 'GMT'XXX");
//
//        this.m_signatureTime = currentTime.format(formatter);
//    }

    public String getSignatureName() {
        return m_signatureName;
    }

    public String getSignature() {
        return m_signature;
    }

    public String getSignatureTime() {
        return m_signatureTime;
    }

    public void setSignatureName(String m_signatureName) {
        this.m_signatureName = m_signatureName;
    }

    public void setSignature(String m_signature) {
        this.m_signature = m_signature;
    }

    public void setSignatureTime(String m_signatureTime) {
        this.m_signatureTime = m_signatureTime;
    }
}
