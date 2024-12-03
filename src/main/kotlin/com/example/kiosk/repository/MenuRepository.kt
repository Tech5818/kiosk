package com.example.kiosk.repository

import com.example.kiosk.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository: JpaRepository<Menu, Long> {
}