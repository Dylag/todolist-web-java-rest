package com.example.todolistwebjavarest;

public class JsonResponse {
    private String text;

    public JsonResponse(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
