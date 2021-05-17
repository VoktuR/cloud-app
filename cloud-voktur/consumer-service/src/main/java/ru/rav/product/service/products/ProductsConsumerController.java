package ru.rav.product.service.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.rav.common.dto.ProductRequest;
import ru.rav.common.dto.ProductResponse;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/show")
public class ProductsConsumerController {

    private final RestTemplate restTemplate;

    @GetMapping("/all")
    public String all(Model model) {
        ResponseEntity<ProductResponse[]> response = restTemplate.getForEntity(
                "http://products-producer/api/v1/products", ProductResponse[].class);
        ProductResponse[] productResponses = response.getBody();
        List<ProductResponse> pr = Arrays.asList(productResponses);
        model.addAttribute("products", pr);
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") String id, Model model) {
        model.addAttribute("product", restTemplate.getForObject(
                "http://products-producer/api/v1/products/" + id, ProductResponse.class));
        return "show";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("product", new ProductRequest());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("product") ProductRequest productRequest) {
        restTemplate.postForEntity(
                "http://products-producer/api/v1/products", productRequest, ProductResponse.class);
        return "redirect:/api/v1/show/all";
    }


    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", restTemplate.getForObject(
                "http://products-producer/api/v1/products/" + id, ProductResponse.class));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("product") ProductRequest productRequest, @PathVariable("id") long id) {
        productRequest.setId(id);
        restTemplate.put("http://products-producer/api/v1/products", productRequest, ProductResponse.class);
        return "redirect:/api/v1/show/all";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        restTemplate.delete("http://products-producer/api/v1/products/" + id);
        return "redirect:/api/v1/show/all";
    }


}
