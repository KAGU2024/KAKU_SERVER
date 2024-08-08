package server.gagu.global.utils.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.gagu.global.template.ResponseTemplate;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {
    @GetMapping
    public ResponseTemplate<?> healthCheck() {
        return new ResponseTemplate<>(HttpStatus.OK, "Health Check");
    }
}
