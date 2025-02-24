package ecommerceBESB.ecommerce.Products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import ecommerceBESB.ecommerce.Orders.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "image_product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageProd {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Lob
	@Column(name = "image", unique = false, nullable = false, length = 100000)
	private byte[] image;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productImage;
}