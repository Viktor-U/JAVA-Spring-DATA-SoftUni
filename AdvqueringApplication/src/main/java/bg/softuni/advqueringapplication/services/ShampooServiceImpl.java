package bg.softuni.advqueringapplication.services;

import bg.softuni.advqueringapplication.entities.Shampoo;
import bg.softuni.advqueringapplication.entities.Size;
import bg.softuni.advqueringapplication.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;



@Service
public class ShampooServiceImpl implements ShampooService {

    private ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }
    @Override
    public List<Shampoo> findBySize(Size size){
        return shampooRepository.findBySize(size);
    }

    @Override
    public List<Shampoo> findBySizeOrLabel(Size size, Long label) {
        return shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, label);
    }

    @Override
    public List<Shampoo> findByPriceIsGreaterThan(BigDecimal price) {
        return shampooRepository.findByPriceIsGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public Long countShampooByPriceLessThan(BigDecimal price){
        return shampooRepository.countShampooByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> findByIngredientList(List<String> list) {
        return shampooRepository.findByIngredientList(list);
    }

    @Override
    public List<Shampoo> findAllByIngredientsLessThan(long num) {
        return shampooRepository.findAllByIngredientsLessThan(num);
    }
}
