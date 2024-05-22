package bg.softuni.l12springbookshop.services.category;

import bg.softuni.l12springbookshop.domein.entities.Category;

import java.util.List;

public interface CategoryService {
    void seedCategories(List<Category> categories);
}
