package ru.itis.utils;

import org.springframework.ui.ModelMap;

public class Attributes {

    public static void addSuccessAttributes(ModelMap map, String message){
        map.addAttribute("title", "Good job!");
        map.addAttribute("message", message);
        map.addAttribute("category", "success");
    }

    public static void addErrorAttributes(ModelMap map, String message){
        map.addAttribute("title", "Oops!");
        map.addAttribute("message", message);
        map.addAttribute("category", "error");
    }
}
