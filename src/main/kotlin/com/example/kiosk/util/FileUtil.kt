package com.example.kiosk.util

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.kiosk.exception.CustomException
import com.example.kiosk.exception.ErrorCode
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Component
class FileUtil(
    private val s3Client: AmazonS3,
    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String
) {
    fun uploadFile(file: MultipartFile): String {
        val originalFilename = file.originalFilename
            ?: throw CustomException(ErrorCode.BAD_REQUEST)
        val fileName = "${UUID.randomUUID()}-${originalFilename}"
        val objectMetadata = setFileDateOption(
            type = "image",
            file = getFileExtension(originalFilename),
            multipartFile = file
        )
        val putObjectRequest = PutObjectRequest(bucket, fileName, file.inputStream, objectMetadata)
            .withCannedAcl(CannedAccessControlList.PublicRead)
        s3Client.putObject(putObjectRequest)
        return s3Client.getUrl(bucket, fileName).toString()
    }

    private fun getFileExtension(fileName: String): String {
        val extensionIndex = fileName.lastIndexOf('.')
        return fileName.substring(extensionIndex + 1)
    }

    private fun setFileDateOption(
        type: String,
        file: String,
        multipartFile: MultipartFile
    ): ObjectMetadata {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentType = "/${type}/${getFileExtension(file)}"
        objectMetadata.contentLength = multipartFile.inputStream.available().toLong()
        return objectMetadata
    }
}