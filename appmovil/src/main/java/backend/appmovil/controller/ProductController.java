package backend.appmovil.controller;
import backend.appmovil.model.Product;
import backend.appmovil.repository.ProductRepository;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*") //permite peticiones desde android
public class ProductController {
    private final ProductRepository repository;
    public ProductController(ProductRepository repository){
        this.repository = repository;
    }
    
    @GetMapping
    public List<Product> getAllProducts(){
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }
    
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return repository.save(product);
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product newProduct){
      Product p = repository.findById(id).orElseThrow();
      p.setName(newProduct.getName());
      p.setPrice(newProduct.getPrice());
      p.setCategory(newProduct.getCategory());
      return repository.save(p);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String productName) {
        return repository.findByNameContainingIgnoreCase(productName);
    }
}
