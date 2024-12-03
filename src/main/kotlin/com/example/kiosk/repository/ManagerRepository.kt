package com.example.kiosk.repository

import com.example.kiosk.entity.Manager
import org.springframework.data.jpa.repository.JpaRepository

interface ManagerRepository: JpaRepository<Manager, String> {
}