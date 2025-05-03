package com.pharmacy.repository;

import com.pharmacy.dto.ProductDto;
import com.pharmacy.model.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long>, CustomProductRepository {

    //select p.id, p.name,p.generic_name,p.description,p.side_effects, pt.id,pt.name from pharmacy.product p
    //left join pharmacy.product_type pt on p.product_type_id = p.id;
    @Query("select new com.pharmacy.dto.ProductDto(p.id productId, p.name productName, p.genericName, pt.id productTypeId, pt.name productTypeName) from Product p left join fetch ProductType pt on p.productType.id = pt.id")
    @Override
    List<ProductDto> fetchProducts(PageRequest pageRequest);
}
