package com.example.kiosk.service

import com.example.kiosk.repository.CategoryRepository
import com.example.kiosk.repository.MenuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuService(
    @Autowired private val menuRepository: MenuRepository,
    @Autowired private val categoryRepository: CategoryRepository) {

}