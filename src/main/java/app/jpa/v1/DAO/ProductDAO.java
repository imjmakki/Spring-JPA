package app.jpa.v1.DAO;

import app.jpa.v1.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    public List<Product> findByName(String name);
}
