package com.example.kiosk.service

import com.example.kiosk.entity.MenuType
import com.example.kiosk.entity.dto.StoreDTO
import com.example.kiosk.repository.MenuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StoreService(
    @Autowired private val menuRepository: MenuRepository,
    @Autowired private val menuCategoryRepository: MenuRepository
) {
    fun findStore(): StoreDTO {
        val menu = menuRepository.findAll()

        var total = 0
        val category = menuCategoryRepository.findAll().size
        var new = 0
        var season = 0
        var time = 0

        menu.forEach{
            total++
            when(it.type) {
                MenuType.NEW -> new++
                MenuType.SEASON -> season++
                MenuType.TIME -> time++
                else -> TODO()
            }
        }

        return StoreDTO(
            "맥도날드",
            "햄버거 프렌차이즈",
            total,
            category,
            new,
            season,
            time
        )

    }
}