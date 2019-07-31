package fh.fa.data.repository;

import fh.fa.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductReactiveRepository extends ReactiveRepository<Product> {

    private static final String ERROR_SOURCE = "Product Repository";

    public ProductReactiveRepository(final JpaRepository<Product, Long> repository) {
        super(repository);
    }

    @Override
    String getRepositoryIdentification() {
        return ERROR_SOURCE;
    }
}
