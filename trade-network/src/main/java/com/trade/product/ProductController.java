package com.trade.product;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Products")
public class ProductController {



    @GetMapping("")
    public String products() {
        return "Product list";
    }


    @PostMapping("")
    public String productsPost(
            @RequestBody @Valid ProductPostRequest  productPostRequest
    ) {
        return "Product post";
    };



    @PutMapping("")
    public String productsPut(
            @RequestBody @Valid ProductPutRequest productPutRequest
    )
    {
        return "Product put";
    }


    @DeleteMapping("")
    public String productsDelete(
            @RequestParam int productId
    ){
        return "Product delete";
    }
}
