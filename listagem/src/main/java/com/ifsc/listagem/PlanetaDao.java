package com.ifsc.listagem;

import java.util.ArrayList;

public class PlanetaDao {
    ArrayList<Planeta> planetas;

    public PlanetaDao() {
            planetas=new ArrayList<>();
            planetas.add(new Planeta("Mercurio",R.drawable.mercury));
            planetas.add(new Planeta("Venus",R.drawable.venus));
            planetas.add(new Planeta("Terra",R.drawable.earth));
            planetas.add(new Planeta("Marte",R.drawable.mars));
            planetas.add(new Planeta("Jupiter",R.drawable.jupter));
            planetas.add(new Planeta("Saturno",R.drawable.saturn));
            planetas.add(new Planeta("Urano",R.drawable.uranus));
            planetas.add(new Planeta("Sol",R.drawable.sun));
    }

    public ArrayList<Planeta> getAllPlanetas() {
        return this.planetas;
    }
}
