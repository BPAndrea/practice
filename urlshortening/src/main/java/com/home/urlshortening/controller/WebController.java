package com.home.urlshortening.controller;

import com.home.urlshortening.model.LogMessage;
import com.home.urlshortening.model.User;
import com.home.urlshortening.service.PasswordService;
import com.home.urlshortening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {
  private UserService userService;
  private PasswordService passwordService;

  @Autowired
  public WebController(UserService userService, PasswordService passwordService) {
    this.userService = userService;
    this.passwordService = passwordService;
  }

  @GetMapping({"/", "/index"})
  public String index(Model model) {
    if(model.asMap().get("existing") != null) {
      model.addAttribute("user", model.asMap().get("existing"));
    } else
      model.addAttribute("user", new User());
    return "url-form";
  }
/*  @GetMapping({"/", "/index"})
  public String index(Model model) {
    model.addAttribute("user", new User());
    return "url-form";
  }*/

/*  @PostMapping("/save-link")
  public String saveLink(@ModelAttribute User user, Model model) {
    if (userService.searchForAlias(user.getAlias())) {
      userService.addUser(user);
      //int password = passwordService.getRandom(user.getUserId());
      int password = passwordService.getRandom(user);
      model.addAttribute("text", "Your URL is aliased to <strong>" + user.getAlias() + "</strong> and your secret code is <strong> " + password + "</strong> .");
      model.addAttribute(new User());
      return "url-form";
    } else {
      model.addAttribute("text", "Your alias is already in use!");
      model.addAttribute("user", user);
      model.addAttribute("color", "red");
      return "url-form";
    }
  }*/

  @PostMapping("/save-link")
  public String saveLink(@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs) {
    if (userService.searchForAlias(user.getAlias())) {
      userService.addUser(user);
      //int password = passwordService.getRandom(user.getUserId());
      int password = passwordService.getRandom(user);
      redirectAttrs.addFlashAttribute("text", "Your URL is aliased to <strong>" + user.getAlias() + "</strong> and your secret code is <strong> " + password + "</strong> .");
     } else {
      redirectAttrs.addFlashAttribute("text", "Your alias is already in use!");
      redirectAttrs.addFlashAttribute("existing", user);
      redirectAttrs.addFlashAttribute("color", "red");
    }
    return "redirect:/";
  }

  @GetMapping("/a/{alias}")
  public Object index(@PathVariable String alias, Model model) {
    if (!userService.searchForAlias(alias)) {
      userService.increaseHitCounter(alias);
      return "redirect:/";
    } else {
      return new ResponseEntity(HttpStatus.NOT_FOUND);//model.addAttribute("error", "Status code: 404");
    }
  }

}

 /*
   @GetMapping("/")
  public String mainPage(Model model) {
    if(model.asMap().get("kutya") != null) {
      model.addAttribute("alma", model.asMap().get("kutya"));
    } else {
      model.addAttribute("alma", new Alias());
    }

    return "main";
  }

 @PostMapping("/save-link")
  public String saveLink(@ModelAttribute Alias alma, RedirectAttributes redirectAttrs) {
    if(service.findByAliasName(alma.getAname()) == null) {
      service.addAlias(alma);
      redirectAttrs.addFlashAttribute("message", "Your URL is aliased to "+ alma.getAname() +" and your secret code is "+ alma.getSecretCode() +".");
    } else {
      redirectAttrs.addFlashAttribute("message", "Your alias is already in use!");
      redirectAttrs.addFlashAttribute("kutya", alma);
    }
    return "redirect:/";
*/
