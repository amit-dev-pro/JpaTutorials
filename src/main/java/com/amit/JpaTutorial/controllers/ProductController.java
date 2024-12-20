package com.amit.JpaTutorial.controllers;

import com.amit.JpaTutorial.entities.ProductEntity;
import com.amit.JpaTutorial.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final int PAGE_SIZE=5;

    private  final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber) {

        return productRepository.findByTitleContainingIgnoreCase(
                title,
                (Pageable) PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy))
        );


//
//        return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy, "price"));
//        return productRepository.findBy(Sort.by(
//                Sort.Order.desc(sortBy),
//                Sort.Order.desc("title")
//        ));


//        Pageable pageable = PageRequest.of(
//                pageNumber,
//                PAGE_SIZE,
//                Sort.by(sortBy));
//
//        return productRepository.findAll(pageable).getContent();


    }

}
