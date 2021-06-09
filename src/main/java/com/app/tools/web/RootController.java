package com.app.tools.web;

import com.app.tools.web.tool.AbstractToolController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController extends AbstractToolController {

    @GetMapping("/")
    public String root() {
        return "redirect:tools";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/tools")
    public String getTools(Model model) {
        model.addAttribute("tools", super.getAll());
        model.addAttribute("locations", super.getAllLocations());
        model.addAttribute("types", super.getAllTypes());
        return "tools";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String getUsers() {
        return "users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tools/types")
    public String getTypes(Model model) {
        model.addAttribute("types", super.getAllTypes());
        return "types";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tools/locations")
    public String getLocations(Model model) {
        model.addAttribute("locations", super.getAllLocations());
        return "locations";
    }

}
