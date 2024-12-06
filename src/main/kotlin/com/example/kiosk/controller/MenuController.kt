package com.example.kiosk.controller

import com.example.kiosk.entity.Menu
import com.example.kiosk.entity.MenuType
import com.example.kiosk.entity.dto.CategoryDTO
import com.example.kiosk.entity.dto.CreateMenuDTO
import com.example.kiosk.entity.dto.MenuDTO
import com.example.kiosk.service.MenuService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

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

    @DeleteMapping("/category/{id}")
    fun deleteCategory(@PathVariable id: Long) = menuService.deleteCategory(id)

    @PostMapping("")
    fun createMenu(
        @RequestParam name: String,
        @RequestParam img: MultipartFile,
        @RequestParam price: Long,
        @RequestParam type: MenuType,
        @RequestParam discount: Int = 0,
        @RequestParam calories: Double = 0.0,
        @RequestParam categoryId: Long
    ): MenuDTO = menuService.createMenu(name, img, price, type, discount, calories, categoryId)

    @GetMapping("")
    fun findAllMenu() : List<MenuDTO> = menuService.findAllMenu()

    @GetMapping("/{id}")
    fun findByIdMenu(@PathVariable("id") id: Long): MenuDTO = menuService.findByIdMenu(id)

    @DeleteMapping("/{id}")
    fun deleteMenu(@PathVariable("id") id: Long) = menuService.deleteMenu(id)
}