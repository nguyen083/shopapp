package com.example.shopapp.services;

import com.example.shopapp.dtos.ProductDTO;
import com.example.shopapp.dtos.ProductImageDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.exceptions.InvalidParamException;
import com.example.shopapp.models.Category;
import com.example.shopapp.models.Product;
import com.example.shopapp.models.ProductImage;
import com.example.shopapp.repositories.CategoryRepository;
import com.example.shopapp.repositories.ProductImageRepository;
import com.example.shopapp.repositories.ProductRepository;
import com.example.shopapp.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;
    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
            Product newProduct = Product.builder()
                    .name(productDTO.getName())
                    .thumbnail(productDTO.getThumbnail())
                    .description(productDTO.getDescription())
                    .price(productDTO.getPrice())
                    .category(categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() ->
                            new DataNotFoundException("Cannot find category with id: " + productDTO.getCategoryId())))
                    .build();
            return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(long productId) {

        return productRepository.findById(productId).orElseThrow(()-> new DateTimeException("Cannot find product with id: " + productId));
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        // Lấy danh sách sản phẩm theo trang(page) và giới hạn(limit)
        return productRepository
                .findAll(pageRequest)
                .map(ProductResponse::fromProduct);
    }

    @Override
    public Product updateProduct(long productId, ProductDTO productDTO) throws Exception {
        Product existingProduct = getProductById(productId);
        if(existingProduct != null)
            {
                existingProduct.setName(productDTO.getName());
                existingProduct.setThumbnail(productDTO.getThumbnail());
                existingProduct.setDescription(productDTO.getDescription());
                existingProduct.setPrice(productDTO.getPrice());
                existingProduct.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() ->
                        new DataNotFoundException("Cannot find category with id: " + productDTO.getCategoryId())));
                return productRepository.save(existingProduct);

            }
        return null;
    }

    @Override
    public void deleteProduct(long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        optionalProduct.ifPresent(productRepository::delete);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
    @Override
    public ProductImage createProductImage  ( ProductImageDTO productImageDTO) throws Exception {
        ProductImage newProductImage = ProductImage.builder()
               .imageUrl(productImageDTO.getImageUrl())
               .product(productRepository.findById(productImageDTO.getProductId()).orElseThrow(() ->
                        new DataNotFoundException("Cannot find product with id: " + productImageDTO.getProductId())))
               .build();
        int size = productImageRepository.findByProductId(productImageDTO.getProductId()).size();
        if(size < ProductImage.MAXIMUM_IMAGEs_PER_PRODUCT)
            return productImageRepository.save(newProductImage);
        else
            throw new InvalidParamException("Number of images must be less than or equal " + ProductImage.MAXIMUM_IMAGEs_PER_PRODUCT);
    }
}
