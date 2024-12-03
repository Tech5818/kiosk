package com.example.kiosk.controller

import com.example.kiosk.entity.Kiosk
import com.example.kiosk.entity.dto.RegisterKioskDTO
import com.example.kiosk.service.KioskService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kiosk")
class KioskController(@Autowired val kioskService: KioskService) {
    @PostMapping("")
    fun register(@Valid @RequestBody dto: RegisterKioskDTO): RegisterKioskDTO {
        return kioskService.register(dto)
    }

    @GetMapping("")
    fun findAll() : List<Kiosk> {
        return kioskService.findAll()
    }
}