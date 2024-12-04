package com.example.kiosk.service

import com.example.kiosk.entity.Menu
import com.example.kiosk.entity.dto.CategoryDTO
import com.example.kiosk.entity.toDTO
import com.example.kiosk.repository.CategoryRepository
import com.example.kiosk.repository.MenuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuService(
    @Autowired private val menuRepository: MenuRepository,
    @Autowired private val categoryRepository: CategoryRepository) {

    fun createCategory(category: CategoryDTO): CategoryDTO = categoryRepository.save(category.toEntity()).toDTO()

    fun findByIdCategory(id: Long): CategoryDTO = categoryRepository.findById(id).get().toDTO()

    fun findAllCategory(): List<CategoryDTO> = categoryRepository.findAll().map { it.toDTO() }

    fun updateCategory(category: CategoryDTO): CategoryDTO = categoryRepository.save(category.toEntity()).toDTO()

    fun findAllMenu(): List<Menu> {
        return menuRepository.findAll()
    }
}