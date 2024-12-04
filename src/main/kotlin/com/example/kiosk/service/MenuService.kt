package com.example.kiosk.service

import com.example.kiosk.entity.Menu
import com.example.kiosk.entity.dto.CategoryDTO
import com.example.kiosk.entity.toDto
import com.example.kiosk.exception.CustomException
import com.example.kiosk.exception.ErrorCode
import com.example.kiosk.repository.CategoryRepository
import com.example.kiosk.repository.MenuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuService(
    @Autowired private val menuRepository: MenuRepository,
    @Autowired private val categoryRepository: CategoryRepository
) {

    fun createCategory(dto: CategoryDTO): CategoryDTO = dto.takeIf {
            it.id != null && !categoryRepository.existsById(it.id)
        }
        ?.let { categoryRepository.save(dto.toEntity()).toDto() }
        ?: throw CustomException(ErrorCode.ALREADY_EXIST, "ID(${dto.id}) is already exists")

    fun findByIdCategory(id: Long): CategoryDTO = categoryRepository.findById(id).get().toDto()

    fun findAllCategory(): List<CategoryDTO> = categoryRepository.findAll().map { it.toDto() }

    fun updateCategory(dto: CategoryDTO): CategoryDTO = categoryRepository.save(dto.toEntity()).toDto()

    fun findAllMenu(): List<Menu> {
        return menuRepository.findAll()
    }
}