package org.launchcode.controllers;

import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private MenuDao menuDao;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String index(Model model) {
        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");

        return "/menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title","Create New Menu");
        model.addAttribute(new Menu());
        return "/menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu menu, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create New Menu");
            return "/menu/add";
        }
        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }
}
