package ecommerceBESB.ecommerce.Products.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ecommerceBESB.ecommerce.Products.ImageProd;



public interface ImageProdRepository extends JpaRepository<ImageProd, Long> {
	@Query(value = "SELECT * FROM image_product where name = :name", nativeQuery = true)
	Optional<ImageProd> findProductImageByName(String name);

}
