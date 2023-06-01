package ru.aberezhnoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aberezhnoy.persist.Category;
import ru.aberezhnoy.persist.CategoryRepository;
import ru.aberezhnoy.service.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //    @Override
//    public List<CategoryDto> findAll() {
//        return categoryRepository.findAll()
//                .stream()
//                .map(CategoryServiceImpl::convertToDto)
//                .collect(Collectors.toList());
//    }
    @Override
    public List<CategoryDto> findAll() {
        List<Category> listCat = categoryRepository.findAll();
        List<CategoryDto> listDto = new ArrayList<>();
        for (Category category : listCat) {
            listDto.add(convertToDto(category));
        }
        return listDto;
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return categoryRepository.findById(id).
                map(CategoryServiceImpl::convertToDto);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = new Category(
                categoryDto.getId(),
                categoryDto.getName()
        );
        Category saved = categoryRepository.save(category);
        return convertToDto(saved);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);

    }

    private static CategoryDto convertToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
