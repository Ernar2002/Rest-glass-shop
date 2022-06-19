package kz.yernar.rest_glass_shop.controller.moderator;

import kz.yernar.rest_glass_shop.domain.Category;
import kz.yernar.rest_glass_shop.service.CategoryService;
import kz.yernar.rest_glass_shop.utils.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mod/category", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController (
            CategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        try {
            categoryService.save(category);

            return ResponseEntity.ok(new MessageResponse("Category added successfully"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Something wrong: " + e));
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category){
        categoryService.update(id, category);

        return ResponseEntity.ok(categoryService.getById(id));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        try {
            categoryService.delete(id);

            return ResponseEntity.ok(new MessageResponse("Category deleted successfully"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Something wrong: " + e));
        }
    }
}
