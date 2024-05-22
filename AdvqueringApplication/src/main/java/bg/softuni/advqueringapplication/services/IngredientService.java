package bg.softuni.advqueringapplication.services;

import bg.softuni.advqueringapplication.entities.Ingredient;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IngredientService {


    List<Ingredient> findByNameStartingWith(String letters);

    List<Ingredient> findByNameIn(List<String> list);

    void deleteIngredientByName(String name);

    void updateIngredientsByPrice();

    @Transactional
    void updateIngredientsPriceByName(String name);
}
