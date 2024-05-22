package bg.softuni.l14springdataadvancedqueryingexercice.service;


import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
