package crud2.Crud2.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

@Data
@Entity
//@NoArgsConstructor
//@Getter
//@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("category_id")
    private Long id;


    @JsonProperty("categoryName")
    private String categoryName;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty
    @JsonManagedReference
    private List<Brand> brands;

    public Category(Long id,String categoryName) {
        this.id = id;
        this.categoryName = categoryName;

    }
    public Category() {
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return  categoryName;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


}
