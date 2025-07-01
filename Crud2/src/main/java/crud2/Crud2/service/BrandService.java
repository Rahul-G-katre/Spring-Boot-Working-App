package crud2.Crud2.service;

import crud2.Crud2.exception.ResourceNotFoundException;
import crud2.Crud2.model.Brand;
import crud2.Crud2.repository.BrandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private static final Logger logger = LoggerFactory.getLogger(BrandService.class);

    private final BrandRepository brandRepository;


    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // Create Brand
    public Brand saveBrand(Brand brand) {
        logger.info("Saving new brand: {}", brand.getBrandname());
        return brandRepository.save(brand);
    }

    public List<Brand> getAllBrands() {
        logger.info("Fetching all brands from repository");
        return brandRepository.findAll();
    }


    public Optional<Brand> getBrandById(Long id) {
        logger.debug("Attempting to fetch brand by ID: {}", id);
        Optional<Brand>brand = Optional.ofNullable(brandRepository.findById(id).orElseThrow(() ->
        {
            logger.warn("Brand with ID {} not found", id);
            return new ResourceNotFoundException("Brand with ID " + id + " not found.");
        }));
        return brandRepository.findById(id);
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    public Brand saveBrand(Long id, Brand brand) {
        logger.debug("Checking if brand exists before saving update for ID: {}", id);
        Optional<Brand> findById = Optional.ofNullable(brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Does Not Exist")));
        logger.info("Saving updated brand with ID: {}", id);
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Long id, Brand brand) {
        logger.info("Updating brand with ID: {}", id);
        return   brandRepository.save(brand);
    }
// 20 17 3 15 - 55
}
