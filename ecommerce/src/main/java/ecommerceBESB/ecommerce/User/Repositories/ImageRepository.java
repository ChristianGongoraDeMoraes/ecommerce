
package ecommerceBESB.ecommerce.User.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ecommerceBESB.ecommerce.User.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	 @Query(value = "SELECT * FROM image Where name = :name", nativeQuery = true)
	Optional<Image> findByName(String name);
}
