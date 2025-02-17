package ecommerceBESB.ecommerce.Products;

import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import ecommerceBESB.ecommerce.Orders.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Table(name = "Products")
public class Product implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = false, nullable = false)
    private Integer price;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private final Set<Order> orders = new HashSet<>();
}
