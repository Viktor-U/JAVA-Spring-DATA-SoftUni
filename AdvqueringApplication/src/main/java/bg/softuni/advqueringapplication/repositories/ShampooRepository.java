package bg.softuni.advqueringapplication.repositories;

import bg.softuni.advqueringapplication.entities.Ingredient;
import bg.softuni.advqueringapplication.entities.Shampoo;
import bg.softuni.advqueringapplication.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findBySize(Size size);


    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, Long label_id);

    List<Shampoo> findByPriceIsGreaterThanOrderByPriceDesc(BigDecimal price);

    Long countShampooByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo AS s" +
            " JOIN s.ingredients AS i" +
            " WHERE i.name IN :list")
    List<Shampoo> findByIngredientList(List<String> list);

    @Query("SELECT s FROM Shampoo s " +
            "WHERE (SELECT COUNT(i) FROM s.ingredients i) < :num")
    List<Shampoo> findAllByIngredientsLessThan(long num);
}
