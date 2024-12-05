package com.example.kiosk.repository

import com.example.kiosk.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MenuRepository: JpaRepository<Menu, Long> {
}