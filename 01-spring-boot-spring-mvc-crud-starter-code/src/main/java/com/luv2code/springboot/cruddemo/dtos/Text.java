package com.luv2code.springboot.cruddemo.dtos;

import java.util.List;

public class Text {
    private List<String> text;

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
