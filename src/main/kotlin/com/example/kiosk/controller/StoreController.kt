package com.example.kiosk.controller

import com.example.kiosk.entity.dto.StoreDTO
import com.example.kiosk.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/store")
class StoreController(@Autowired val storeService: StoreService) {
    @GetMapping("/")
    fun findStore(): StoreDTO = storeService.findStore()
}