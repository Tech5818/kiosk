package com.example.kiosk.service

import com.example.kiosk.entity.dto.KioskDTO
import com.example.kiosk.entity.toDto
import com.example.kiosk.exception.CustomException
import com.example.kiosk.exception.ErrorCode
import com.example.kiosk.repository.KioskRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
@Transactional
class KioskService(@Autowired private val kioskRepository: KioskRepository) {
    fun register(dto: KioskDTO): KioskDTO = dto.number.takeIf {
            !kioskRepository.existsByNumber(it)
        }
        ?.let {
            kioskRepository.findByIdOrNull(dto.id)?.let {
                throw CustomException(
                    ErrorCode.ALREADY_EXIST,
                    "ID(${dto.id}) is already registration"
                )
            } ?: kioskRepository.save(dto.toEntity()).toDto()
        }
        ?: throw CustomException(
            ErrorCode.BAD_REQUEST,
            "Duplicate Table number ${dto.number}"
        )


    fun findAll(): List<KioskDTO> = kioskRepository.findAll().map { KioskDTO(it.id, it.number) }

    fun findById(id: UUID): KioskDTO {
        val kiosk = kioskRepository.findByIdOrNull(id) ?: throw CustomException(ErrorCode.NOT_FOUND)

        return KioskDTO(kiosk.id, kiosk.number)
    }

    fun deleteById(id: UUID): KioskDTO = kioskRepository.findByIdOrNull(id)
        ?.let {
            kioskRepository.deleteById(it.id)

            it.toDto()
        }
        ?: throw CustomException(
            ErrorCode.BAD_REQUEST,
            "ID($id) is not registered kiosk"
        )
}