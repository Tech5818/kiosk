package com.example.kiosk.service

import com.example.kiosk.entity.dto.RegisterKioskDTO
import com.example.kiosk.repository.KioskRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
class KioskService(@Autowired private val kioskRepository: KioskRepository) {
    fun register(dto: RegisterKioskDTO): RegisterKioskDTO {
        val savedKiosk = kioskRepository.save(dto.toEntity())

        return RegisterKioskDTO(
            savedKiosk.id,
            savedKiosk.number
        )
    }
}