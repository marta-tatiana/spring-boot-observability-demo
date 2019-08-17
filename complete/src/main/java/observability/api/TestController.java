package observability.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class TestController {
    
    @RequestMapping("/")
    public String index() {
        return "It lives!";
    }

    @RequestMapping("/test")
    public String test() {
        for (int i = 0; i < 15; ++i) {
            testRec(1);
        }
        return "Running test for 15!";
    }

    private void testRec(int n) {
        for (int i = 0; i < n; ++i ) {
            Object small = new Object();
            testRec(i+1);
        }
    }
    
}
