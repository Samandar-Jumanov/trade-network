package com.trade.health;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("health")
@RestController
@RequiredArgsConstructor
@Tag(name = "health checker")
public class HealthController {

    @GetMapping("")
    public  String health() {
        return "Health checker ";
    }

}
