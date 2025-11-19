package com.mail.demo.controllers;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Getter
class Templogin{
    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
@RestController
@RequestMapping ("/welcome") //adds mapping to whole class...
public class EntryController {
    private ArrayList<User> userArrayList= new ArrayList<>();


   // private Map<String,user> idpwd = new HashMap<>();

    @GetMapping ("/all users")
    public ArrayList<User> display(){
        return new ArrayList<>(userArrayList);
    }

    @PostMapping ("/register")
    public String postlist(@RequestBody User a){
        EDGE edge =new EDGE();
       if(edge.validateFullPassword(a.getPassword(),a.getEmail()).equals("logging in.....")) {userArrayList.add(a);};
        return edge.validateFullPassword(a.getPassword(),a.getEmail());

    }
    @GetMapping("/")
    public String login(){
        return "login.html";
    }
    public String loginlist(@ModelAttribute Templogin temp, RedirectAttributes redirectAttributes){
        boolean vaildPassword=false;
        for(User u : userArrayList){
            if(u.getEmail().equals(temp.getEmail()) && u.getPassword().equals(temp.getPassword()) ){
                vaildPassword=true;
                break; // Exit loop once user is found
            }
        }

        if(vaildPassword){
            // 1. Success: Redirect to a new welcome page
            return "redirect:/welcome/success";
        } else {
            // 2. Failure: Add error message to be carried over the redirect
            redirectAttributes.addFlashAttribute("loginError",
                    "Login failed: Mail ID or password is incorrect.");
            // 3. Redirect back to the GET mapping for the login page (i.e., /welcome/)
            return "redirect:/welcome/";
        }
    }


}