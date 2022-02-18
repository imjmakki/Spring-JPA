package app.jpa.v1.Resource;

import app.jpa.v1.DAO.ProductDAO;
import app.jpa.v1.Domain.Product;
import app.jpa.v1.Utility.Exception.ProductNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts() {

        return ResponseEntity.ok(productDAO.findAll());
    }

    @GetMapping("/paginate")
    public ResponseEntity<List<Product>> Paginate(@RequestParam Integer page, @RequestParam Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        List<Product> list = productDAO.findAll(pageable).getContent();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Integer id) {

        return ResponseEntity.ok(productDAO.findById(id).orElseThrow(()-> new ProductNotFound("Product Not Found")));
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<String> handleProductNotFound() {
        return ResponseEntity.ok("Product Not Found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody Product product) {

        Product prod = productDAO.findById(product.getId()).get();
        prod.setName(prod.getName());
        prod.setPrice(prod.getPrice());
        prod = productDAO.save(prod);
        return ResponseEntity.ok(prod);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delProduct(@PathVariable Integer id) {

        productDAO.deleteById(id);
        return ResponseEntity.ok("Product Deleted Successfully");
    }
}
