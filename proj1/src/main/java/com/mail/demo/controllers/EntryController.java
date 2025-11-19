package com.mail.demo.controllers;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping ("/login")
    public String loginlist(@ModelAttribute Templogin temp){
        boolean vaildPassword=false;
        for(User u : userArrayList){
            if(u.getEmail().equals(temp.getEmail()) && u.getPassword().equals(temp.getPassword()) ){
                vaildPassword=true;
            }
        }
        if(vaildPassword)return "Login successful...";
        return "Login failed mail id or password is incorrect ";
    }


}