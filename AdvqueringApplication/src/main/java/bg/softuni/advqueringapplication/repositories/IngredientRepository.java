package bg.softuni.advqueringapplication.repositories;

import bg.softuni.advqueringapplication.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByNameStartingWith(String letters);

    List<Ingredient> findIngredientByNameInOrderByPrice (List<String> list);

    void deleteIngredientByName(String name);

    @Query("UPDATE Ingredient " +
            "Set price = price * 1.10")
    @Modifying
    void updateIngredientsByPrice();

    @Query("UPDATE Ingredient " +
            "Set price = price * 1.10" +
            " WHERE name = :name")
    @Modifying
    void updateIngredientsByPrice(String name);
}
