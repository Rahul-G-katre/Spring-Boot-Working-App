package crud2.Crud2.controller;

import crud2.Crud2.model.Brand;
import crud2.Crud2.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brand")
@Tag(name = "Brand", description = "Brand management APIs")
public class BrandController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/addbrand")
    @Operation(summary = "Create brand", description = "Creates a new brand")
    public Brand createBrand(@RequestBody Brand brand) {
        logger.info("Received request to create brand: {}", brand.getBrandname());
        return brandService.saveBrand(brand);
    }

    @GetMapping("/getBrands")
    @Operation(summary = "Get all brands", description = "Returns a list of all brands")
    public List<Brand> getAllBrands() {
        logger.info("Received request to fetch all brands");
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a brand", description = "Returns a specific brand")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        logger.debug("Received request to fetch brand with ID: {}", id);
        Optional<Brand> brand = brandService.getBrandById(id);
        return brand.map(ResponseEntity::ok).orElseGet(() -> {
            logger.warn("Brand not found for ID: {}", id);
            return ResponseEntity.notFound().build();
        });
    }

    @PutMapping("/brands/{id}")
    @Operation(summary = "Update brand", description = "Update a new brand")
    public Brand updateProduct(@RequestBody Brand brand, @PathVariable Long id) {
        logger.info("Received request to update brand ID: {} with name: {}", id, brand.getBrandname());
        return brandService.updateBrand(id, brand);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete brand", description = "Delete a brand")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        logger.warn("Received request to delete brand with ID: {}", id);
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

    public BrandService getBrandService() {
        return brandService;
    }
}
