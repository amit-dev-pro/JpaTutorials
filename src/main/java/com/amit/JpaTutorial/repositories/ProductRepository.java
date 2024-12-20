package com.amit.JpaTutorial.repositories;

import com.amit.JpaTutorial.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findByTitle(String title, Pageable pageable);
    List<ProductEntity> findByCreatedAfterOrderByTitle(LocalDateTime after);
    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int quantity, BigDecimal price);
    List<ProductEntity> findByTitleLike(String title);
    List<ProductEntity> findByTitleContainingIgnoreCase(String title,Pageable pageable);

   // Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    @Query("select e.title, e.price from ProductEntity e where e.title=:title and e.price=:price")
    Optional<ProductEntity> findByTitleAndPrice(String title,BigDecimal price);

}
