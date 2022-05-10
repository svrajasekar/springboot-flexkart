package training.lab.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.lab.products.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
