package org.launchcode.controllers;


import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "menu")
public class MenuController {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Menu");
        model.addAttribute("menus", menuDao.findAll());

        return "menu/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add a Menu:");
        //model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute(new Menu());

        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu menu, Errors errors /*Cheese cheese*/) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Menu:");
            return "menu/add";
        } else {
            //menu.addItem(cheese);
            menuDao.save(menu);
            return "redirect:view/"+ menu.getId();
        }

    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable int menuId/*@RequestParam Menu menu*/){

        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("title", "Cheese menu: " + menu.getName());
        model.addAttribute("cheeses", menu.getCheeses());
        model.addAttribute("menuId", menu.getId());
        //model.addAttribute("menuCheeses", menuDao.findOne(menu.getId()));

        return "menu/view";
    }

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem (Model model, @PathVariable int menuId){
        //model.addAttribute("title", "Add an item to " + menu.getName());
        Menu menu = menuDao.findOne(menuId);

        AddMenuItemForm form = new AddMenuItemForm(
                cheeseDao.findAll(), menu);


        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("form", form);
        return "menu/add-item";

    }
    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem (Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "menu/add-item";
        }
        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
        Menu theMenu = menuDao.findOne(form.getMenuId());
        theMenu.addItem(theCheese);
        menuDao.save(theMenu);

        //model.addAttribute("title", "Add an item to " + menu.getName());
        //model.addAttribute();

        return "redirect:view/" + theMenu.getId();
    }
}
