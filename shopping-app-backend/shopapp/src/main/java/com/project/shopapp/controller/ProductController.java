package com.project.shopapp.controller;

import com.project.shopapp.dto.ProductDTO;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.utils.FileUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    @GetMapping
    public ResponseEntity<String> getProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("Get products " + page + " " + limit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String productId) {
        return ResponseEntity.ok("Product with ID: " + productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body("Deleted product with id: " + id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody @ModelAttribute ProductDTO productDTO,
            //@RequestPart("file") MultipartFile file,
            BindingResult result) {

        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            // TODO: throw exception to global
            throw ApiRequestException.badRequest(errorMsg);
        }

        List<MultipartFile> files = productDTO.getFiles();
        files = files == null ? new ArrayList<MultipartFile>() : files;

        for (MultipartFile file : files) {
            if (file.getSize() == 0)
                continue;

            // Check file size (<10MB)
            if (file.getSize() > 10 * 1024 * 1024) {
                // TODO: throw exception to global
                //throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "File is too large! Maximum size is 10MB");
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                        .body("File is too large! Maximum size is 10MB");

            }

            //Check file type
            if (file.getContentType() == null || !file.getContentType().startsWith("image/"))
                // TODO: throw exception to global
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("File must be an image!");

            // TODO: throw exception to global
            try {
                String fileName = FileUtil.storeFile(file);
                // TODO: save files name to DB product_images
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Save file failed!");
            }
        }


        return ResponseEntity.ok("Inserted " + productDTO);
    }


}
