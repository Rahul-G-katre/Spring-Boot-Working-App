package crud2.Crud2.tests.service_tests;

import crud2.Crud2.exception.ResourceNotFoundException;
import crud2.Crud2.model.Brand;
import crud2.Crud2.repository.BrandRepository;
import crud2.Crud2.service.BrandService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    private Brand brand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brand = new Brand();
        brand.setId(111L);
        brand.setBrandname("Nothing");
    }

    @Test
    void testSaveBrand() {
        when(brandRepository.save(brand)).thenReturn(brand);
        Brand saved = brandService.saveBrand(brand);
        assertEquals("Nothing", saved.getBrandname());
        verify(brandRepository, times(1)).save(brand);
    }

    @Test
    void testGetAllBrands() {
        List<Brand> brands = Arrays.asList(brand);
        when(brandRepository.findAll()).thenReturn(brands);
        List<Brand> result = brandService.getAllBrands();
        assertEquals(1, result.size());
        verify(brandRepository).findAll();
    }

    @Test
    void testGetBrandById_Found() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        Optional<Brand> result = brandService.getBrandById(1L);
        assertTrue(result.isPresent());
        assertEquals("Nothing", result.get().getBrandname());
    }

    @Test
    void testGetBrandById_NotFound() {
        when(brandRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> brandService.getBrandById(2L));
    }

    @Test
    void testDeleteBrand() {
        Long id = 1L;
        brandService.deleteBrand(id);
        verify(brandRepository).deleteById(id);
    }

    @Test
    void testSaveBrandWithId_UpdateExisting() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(brandRepository.save(brand)).thenReturn(brand);
        Brand updated = brandService.saveBrand(1L, brand);
        assertNotNull(updated);
        assertEquals("Nothing", updated.getBrandname());
    }

    @Test
    void testSaveBrandWithId_NotFound() {
        when(brandRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> brandService.saveBrand(99L, brand));
    }

    @Test
    void testUpdateBrand() {
        when(brandRepository.save(brand)).thenReturn(brand);
        Brand updated = brandService.updateBrand(1L, brand);
        assertEquals("Nothing", updated.getBrandname());
        verify(brandRepository).save(brand);
    }
}
