package bg.softuni.advqueringapplication.services;

import bg.softuni.advqueringapplication.entities.Shampoo;
import bg.softuni.advqueringapplication.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {

    List<Shampoo> findBySize(Size size);

    List<Shampoo> findBySizeOrLabel(Size size, Long label);
    List<Shampoo> findByPriceIsGreaterThan(BigDecimal price);

    Long countShampooByPriceLessThan(BigDecimal price);

    List<Shampoo> findByIngredientList(List<String> list);

    List<Shampoo> findAllByIngredientsLessThan(long num);
}
