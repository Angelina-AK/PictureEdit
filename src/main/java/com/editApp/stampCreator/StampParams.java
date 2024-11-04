package com.editApp.stampCreator;

import java.awt.*;
import java.util.ArrayList;

public class StampParams {
    private int m_width;
    private String m_headerText;
    private String m_footerText;
    private ArrayList<StampSignature> m_signatures;
    private Font m_basicFont;
    private Font m_lowFont;



    public StampParams() {}

    public StampParams(int width, String headerText, String footerText, @org.jetbrains.annotations.NotNull ArrayList<StampSignature> signatures) {
        this.m_width = width;
        this.m_headerText = headerText;
        this.m_footerText = footerText;
        this.m_signatures = signatures;

    }

    public Font getBasicFont() {
        return m_basicFont;
    }

    public void setBasicFont(Font m_basicFont) {
        this.m_basicFont = m_basicFont;
    }

    public Font getLowFont() {
        return m_lowFont;
    }

    public void setLowFont(Font m_lowFont) {
        this.m_lowFont = m_lowFont;
    }
    
    public int getWidth() {
        return m_width;
    }

    public String getHeaderText() {
        return m_headerText;
    }

    public String getFooterText() {
        return m_footerText;
    }

    public ArrayList<StampSignature> getSignatures() {
        return m_signatures;
    }

    public void setWidth(int m_width) {
        this.m_width = m_width;
    }

    public void setHeaderText(String headerText) {
        this.m_headerText = headerText;
    }

    public void setFooterText(String footerText) {
        this.m_footerText = footerText;
    }

    public void setSignatures(ArrayList<StampSignature> signatures) {
        this.m_signatures = signatures;
    }


}

