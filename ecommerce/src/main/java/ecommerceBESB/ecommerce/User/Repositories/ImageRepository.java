
package ecommerceBESB.ecommerce.User.Repositories;

import java.lang.classfile.ClassFile.Option;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ecommerceBESB.ecommerce.User.Image;
import java.util.List;


public interface ImageRepository extends JpaRepository<Image, Long> {
	@Query(value = "SELECT * FROM image Where name = :name", nativeQuery = true)
	Optional<Image> findByName(String name);

	@Query(value = "SELECT * FROM image Where user_id = :id", nativeQuery = true)
	Optional<Image> findByUserId(UUID id);
}
