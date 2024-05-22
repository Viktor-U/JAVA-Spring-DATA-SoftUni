package bg.softuni.advqueringapplication;

import bg.softuni.advqueringapplication.entities.Size;
import bg.softuni.advqueringapplication.services.IngredientService;
import bg.softuni.advqueringapplication.services.LabelService;
import bg.softuni.advqueringapplication.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    private ShampooService shampooService;
    private IngredientService ingredientService;
    private LabelService labelService;

    @Autowired
    public Main(ShampooService shampooService, IngredientService ingredientService, LabelService labelService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
        this.labelService = labelService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);

        //Ex1
//        String input = sc.nextLine();
//        shampooService.findBySize(Size.valueOf(input.toUpperCase())).forEach(System.out::println);

        //Ex2
//        Size size = Size.valueOf(sc.nextLine().toUpperCase());
//        Long label = Long.parseLong(sc.nextLine());
//
//        shampooService.findBySizeOrLabel(size, label).forEach(System.out::println);

        //Ex3
//        String price = sc.nextLine();3
//        shampooService.findByPriceIsGreaterThan(new BigDecimal(price)).forEach(System.out::println);

        //Ex4
//        String letters = sc.nextLine();
//        ingredientService.findByNameStartingWith(letters).forEach(System.out::println);

        //Ex5
//        String input = sc.nextLine();
//        List<String > list = new ArrayList<>();
//        while (!input.isBlank()){
//            list.add(input);
//
//            input = sc.nextLine();
//        }
//        ingredientService.findByNameIn(list).forEach(System.out::println);

        //Ex6
//        String price = sc.nextLine();
//        System.out.println(shampooService.countShampooByPriceLessThan(new BigDecimal(price)));

        //Ex7 is not right
//        String input = sc.nextLine();
//        List<String > list = new ArrayList<>();
//        while (!input.isBlank()){
//            list.add(input);
//
//            input = sc.nextLine();
//        }
//        shampooService.findByIngredientList(list).forEach(System.out::println);

        //Ex8
//        long num = Long.parseLong(sc.nextLine());
//        shampooService.findAllByIngredientsLessThan(num).forEach(System.out::println);

        //Ex9
//        String name = sc.nextLine();
//        ingredientService.deleteIngredientByName(name);

        //Ex10
//        ingredientService.updateIngredientsByPrice();

        //Ex11
//        String name = sc.nextLine();
//        ingredientService.updateIngredientsPriceByName(name);
    }
}
