package com.example.ordersystem.ordering.service;

import com.example.ordersystem.ordering.dto.ProductDto;
import com.example.ordersystem.ordering.dto.ProductUpdateStockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//구현체는 자동으로 만들어준다.
//실제 http 호출이면서, 마치 로컬 메서드인 것처럼 간단하게 사용.
//name은 eureka에 등록된 호출할 서비스의 이름
//url은 k8s에서 service명
//@FeignClient(name = "product-service") //, url="http://product-service") //프로파일로 분리
@FeignClient(url="http://product-service")
public interface ProductFeign {

    @GetMapping("/product/{productId}")
    ProductDto getProductById(@PathVariable Long productId, @RequestHeader("X-User-Id") String userId);

    @PutMapping("/product/updatestock")
    void updateProductStock(@RequestBody ProductUpdateStockDto productUpdateStockDto);
}
