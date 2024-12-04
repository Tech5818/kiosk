package com.example.kiosk.controller

import com.example.kiosk.entity.dto.KioskDTO
import com.example.kiosk.exception.CustomException
import com.example.kiosk.exception.ErrorCode
import com.example.kiosk.service.KioskService
import jakarta.validation.Valid
import jdk.jshell.execution.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/kiosk")
class KioskController(@Autowired private val kioskService: KioskService) {
    @PostMapping("")
    fun register(@Valid @RequestBody dto: KioskDTO): KioskDTO = kioskService.register(dto)

    @GetMapping("")
    fun findAll() : List<KioskDTO> = kioskService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): KioskDTO = kioskService.findById(id)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID) = kioskService.deleteById(id)

    /**
     * Custom Exception
     */
//    @RequestMapping("/error")
//    fun errorException(): Util = throw CustomException(ErrorCode.NOT_FOUND)
}