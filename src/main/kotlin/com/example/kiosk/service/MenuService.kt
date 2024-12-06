package com.example.kiosk.service

import com.example.kiosk.entity.Menu
import com.example.kiosk.entity.MenuType
import com.example.kiosk.entity.dto.CategoryDTO
import com.example.kiosk.entity.dto.CreateMenuDTO
import com.example.kiosk.entity.dto.MenuDTO
import com.example.kiosk.entity.toDto
import com.example.kiosk.exception.CustomException
import com.example.kiosk.exception.ErrorCode
import com.example.kiosk.repository.CategoryRepository
import com.example.kiosk.repository.MenuRepository
import com.example.kiosk.util.FileUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Service
class MenuService(
    @Autowired private val menuRepository: MenuRepository,
    @Autowired private val categoryRepository: CategoryRepository,
    private val fileUtil: FileUtil
) {

    fun createCategory(dto: CategoryDTO): CategoryDTO = dto.takeIf {
            !categoryRepository.existsByName(it.name)
    }
        ?.let { categoryRepository.save(dto.toEntity()).toDto() }
        ?: throw CustomException(ErrorCode.ALREADY_EXIST, "name: ${dto.name} is already exists")

    fun findByIdCategory(id: Long): CategoryDTO = categoryRepository.findById(id).get().toDto()

    fun findAllCategory(): List<CategoryDTO> = categoryRepository.findAll().map { it.toDto() }

    fun updateCategory(dto: CategoryDTO): CategoryDTO = categoryRepository.save(dto.toEntity()).toDto()

    fun deleteCategory(id: Long) = categoryRepository.findByIdOrNull(id)
        ?.let { categoryRepository.delete(it) }
        ?: throw CustomException(ErrorCode.BAD_REQUEST, "wrong category ID")

    fun createMenu(
        name: String,
        img: MultipartFile,
        price: Long,
        type: MenuType,
        discount: Int,
        calories: Double,
        categoryId: Long
    ): MenuDTO = categoryRepository.findByIdOrNull(categoryId)
            ?.let {
                val menu = Menu(
                    id = null,
                    name = name,
                    imgPath = fileUtil.uploadFile(img),
                    price = price,
                    type = type,
                    discount = discount,
                    calories = calories,
                    category = it
                )
                menuRepository.save(menu).toDto()
            }
            ?: throw CustomException(ErrorCode.BAD_REQUEST, "wrong category id")


    fun findAllMenu() : List<MenuDTO> = menuRepository.findAll().map { it.toDto() }

    fun findByIdMenu(id: Long): MenuDTO = menuRepository.findByIdOrNull(id)?.toDto()
        ?: throw CustomException(ErrorCode.NOT_FOUND, "menu not found")

    fun deleteMenu(id: Long) = menuRepository.findByIdOrNull(id)
        ?.let { menuRepository.delete(it) }
        ?: throw CustomException(ErrorCode.NOT_FOUND, "wrong menu ID")
}