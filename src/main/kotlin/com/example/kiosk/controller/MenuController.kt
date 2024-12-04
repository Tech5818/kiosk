package com.example.kiosk.controller

import com.example.kiosk.entity.dto.CategoryDTO
import com.example.kiosk.service.MenuService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/menu")
class MenuController(@Autowired private val menuService: MenuService) {
    @PostMapping("/category")
    fun createCategory(@Valid @RequestBody dto: CategoryDTO): CategoryDTO = menuService.createCategory(dto)

    @GetMapping("/category/{id}")
    fun findByIdCategory(@PathVariable("id") id: Long): CategoryDTO = menuService.findByIdCategory(id)

    @GetMapping("/category")
    fun findAllCategory(): List<CategoryDTO> = menuService.findAllCategory()

    @PatchMapping("/category/{id}")
    fun updateCategory(
        @Valid @RequestBody dto: CategoryDTO,
        @PathVariable id: Long): CategoryDTO = menuService.updateCategory(
        CategoryDTO(
            id, 
            dto.name,
            dto.isSpecial
        )
    )
}