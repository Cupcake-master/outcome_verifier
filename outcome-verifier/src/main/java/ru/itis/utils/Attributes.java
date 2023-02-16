package ru.itis.utils;

import com.sun.istack.NotNull;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    public static void addErrorAttributesWithFlash(@NotNull RedirectAttributes redirectAttributes, String message){
        redirectAttributes.addFlashAttribute("title", "Oops!");
        redirectAttributes.addFlashAttribute("message", message.isEmpty() ? "Error!" : message);
        redirectAttributes.addFlashAttribute("category", "error");
    }
}
