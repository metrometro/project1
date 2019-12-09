package com.epam.finalproject.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTag extends TagSupport {

    private String text;

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

            if (text.equals("RU")) {
                out.println("Текущая дата: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            }
            if (text.equals("EN")) {
                out.println("Current date: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            }
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    public void setText(String text) {
        this.text = text;
    }
}
