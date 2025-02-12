package ecommerceBESB.ecommerce.Products.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ecommerceBESB.ecommerce.Products.Product;


public interface ProductRepository extends JpaRepository<Product, UUID>{

     @Query(value = "SELECT * FROM Products", nativeQuery = true)
    List<Product> findAllProducts();

    @Query(value = "SELECT * FROM Products WHERE id = :id", nativeQuery = true )
    Optional<Product> findProductById(UUID id);

    @Query(value = "SELECT * FROM Products WHERE name = :name", nativeQuery = true )
    Optional<Product> findProductByName(String name);
}
