package com.example.Managment.Managment.controller;

import com.example.Managment.Managment.dto.UserRegistrationDto;
import com.example.Managment.Managment.model.User;
import com.example.Managment.Managment.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model){
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserRegistrationDto userRegistrationDto){
        userService.save(userRegistrationDto);
        return "redirect:/registration?success";
    }
}
