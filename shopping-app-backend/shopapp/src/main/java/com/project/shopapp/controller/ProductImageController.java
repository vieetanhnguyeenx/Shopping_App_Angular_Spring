package com.project.shopapp.controller;

import com.project.shopapp.dto.request.ProductImageDTORequest;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.service.IProductImageService;
import com.project.shopapp.utils.FileUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product_images")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductImageController {

    IProductImageService productImageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @Valid @ModelAttribute ProductImageDTORequest productImageDTORequest,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            throw ApiRequestException.badRequest(errorMsg);
        }

        List<MultipartFile> files = productImageDTORequest.getFiles();
        files = files == null ? new ArrayList<MultipartFile>() : files;
        List<String> images = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.getSize() == 0)
                continue;

            // Check file size (<10MB)
            if (file.getSize() > 10 * 1024 * 1024) {
                //throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "File is too large! Maximum size is 10MB");
                throw ApiRequestException.exception(List.of("File is too large! Maximum size is 10MB"),
                        HttpStatus.PAYLOAD_TOO_LARGE);
            }

            //Check file type
            if (file.getContentType() == null || !file.getContentType().startsWith("image/"))
                throw ApiRequestException.exception(List.of("File must be an image!"),
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE);


            try {
                String fileName = FileUtil.storeFile(file);
                //save files name to DB product_images
                images.add(fileName);
            } catch (Exception e) {
                throw ApiRequestException.exception(List.of("Save file failed!"),
                        HttpStatus.BAD_REQUEST);
            }
        }

        return ResponseEntity.ok(productImageService.createProductImages(productImageDTORequest.getProductId(), images));
    }


}
