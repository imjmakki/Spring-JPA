package app.jpa.v1.Resource;

import app.jpa.v1.DAO.ProductDAO;
import app.jpa.v1.Domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductAPI {

    private ProductDAO productDAO;

    @Autowired
    public ProductAPI(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        return ResponseEntity.ok(productDAO.save(product));
    }
}
