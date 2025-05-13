package crud2.Crud2.repository;

import crud2.Crud2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);   // Find category by exact name

    List<Category> findByCategoryNameContainingIgnoreCase(String Keyword);  //categories where name contain keyword case-insensitive

    boolean existsByCategoryName(String categoryName);                                        //category exists by name

    Optional<Category> findByIdAndCategoryName(Long id, String categoryName);              //category by and  id  & name
}

