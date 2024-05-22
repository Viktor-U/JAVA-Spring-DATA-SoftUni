package bg.softuni.advqueringapplication.services;

import bg.softuni.advqueringapplication.entities.Ingredient;
import bg.softuni.advqueringapplication.repositories.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findByNameStartingWith(String letters){
        return ingredientRepository.findByNameStartingWith(letters);
    }

    @Override
    public List<Ingredient> findByNameIn(List<String> list){
       return ingredientRepository.findIngredientByNameInOrderByPrice(list);
    }

    @Override
    @Transactional
    public void deleteIngredientByName(String name){
        ingredientRepository.deleteIngredientByName(name);
    }

    @Override
    @Transactional
    public void updateIngredientsByPrice() {
        ingredientRepository.updateIngredientsByPrice();
    }

    @Override
    @Transactional
    public void updateIngredientsPriceByName(String name) {
        ingredientRepository.updateIngredientsByPrice(name);
    }
}
