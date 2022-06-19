package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Category;
import kz.yernar.rest_glass_shop.repository.CategoryRepository;
import kz.yernar.rest_glass_shop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl (
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void update(Long id, Category category) {
        Category categoryFromDb = getById(id);

        if(category.getName() != null) {
            categoryFromDb.setName(category.getName());
        }

        if(category.getThickness() != 0) {
            categoryFromDb.setThickness(category.getThickness());
        }

        save(categoryFromDb);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(getById(id));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
