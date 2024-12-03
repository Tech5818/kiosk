package com.example.kiosk.repository

import com.example.kiosk.entity.MenuCategory
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<MenuCategory, Long> {
}