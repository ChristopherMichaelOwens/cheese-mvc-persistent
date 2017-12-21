package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=128)
    private String name;

    @ManyToMany
    private List<Cheese> cheeses;

    public Menu(){}

    public Menu(String name){
        this();
        this.name = name;
    }

    public void addItem(Cheese item){
        cheeses.add(item);
    }

    public int getId() {
        return id;
    }
    public List<Cheese> getCheeses() {
        return cheeses;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }





}