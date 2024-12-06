package com.example.kiosk.repository

import com.example.kiosk.entity.Kiosk
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface KioskRepository: JpaRepository<Kiosk, UUID> {
    fun existsByNumber(number:Int): Boolean
    fun findByNumber(number: Int): Kiosk?
}