package com.example.kiosk.repository

import com.example.kiosk.entity.Kiosk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.UUID

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class KioskRepositoryTest {
    @Autowired
    private lateinit var repository: KioskRepository

    @Test
    @DisplayName("키오스크 등록")
    fun `kiosk save`() {
        val uuid = UUID.randomUUID()
        val number = 1
        val savedKiosk = repository.save(
            Kiosk(
                uuid,
                number
            )
        )
        assertNotNull(savedKiosk)
        assertEquals(savedKiosk.id, uuid)
        assertEquals(savedKiosk.number, number)
    }

    @Test
    @DisplayName("키오스크 전체 조회")
    fun `kiosk find all`() {
        val savedKiosks = repository.saveAll(
            listOf(
                Kiosk(
                    UUID.randomUUID(),
                    1),
                Kiosk(
                    UUID.randomUUID(),
                    2)
            )
        )

        val findAllKiosk = repository.findAll()

        assertNotNull(findAllKiosk)
        assertEquals(findAllKiosk.size, savedKiosks.size, "키오스크가 조회되지 않았습니다")

    }

    @Test
    @DisplayName("키오스크 ID로 조회")
    fun `kiosk find by id`() {
        val uuid = UUID.randomUUID()
        val savedKiosk = repository.save(
            Kiosk(
                uuid,
                1
            )
        )

        val findByIdKiosk = repository.findById(uuid).get()

        assertNotNull(findByIdKiosk)
        assertEquals(findByIdKiosk.id, savedKiosk.id)
        assertEquals(findByIdKiosk.number, savedKiosk.number)
    }

    @Test
    @DisplayName("키오스크 ID로 삭제")
    fun `kiosk delete by id`() {
        val uuid = UUID.randomUUID()
        val saveKiosk = repository.save(
            Kiosk(
                uuid,
                1
            )
        )

        repository.deleteById(uuid)

        val findByIdKiosk = repository.findById(uuid)

        assertEquals(false, findByIdKiosk.isPresent)
    }

    @Test
    @DisplayName("키오스크 테이블 번호 존재")
    fun `is exist kiosk`() {
        val number = 10

        repository.save(Kiosk(
            UUID.randomUUID(),
            number
        ))

        val isExist = repository.existsByNumber(number)

        assertEquals(true, isExist)
    }

    @Test
    @DisplayName("키오스크 테이블 번호 존재하지 않음")
    fun `isn't exist kiosk`() {
        val number = 10

        repository.save(Kiosk(
            UUID.randomUUID(),
            number
        ))

        val isExist = repository.existsByNumber(number+1)

        assertEquals(false, isExist)
    }
}