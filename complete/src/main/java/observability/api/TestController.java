package observability.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/")
    public String index() {
        logger.trace("main");
        logger.debug("main");
        logger.info("main");
        logger.warn("main");
        logger.error("main");
        return "It lives!";
    }

    @RequestMapping("/compute")
    public String compute() {
        double result = 0;
        for (int i = 0; i < 100; ++i) {
            result += Math.sqrt(Math.random()*i);

        }
        logger.debug("Computed " + result);
        return ""+result;
    }

    @RequestMapping("/allocate")
    public String allocate() {
        Set<Object> objectSet = new HashSet<Object>();
        List<String> arrayList = new ArrayList<String>(0);
        for (int i = 0; i < 10000; ++i) {
            arrayList.add("" + i + i + i);
        }
        objectSet.add(arrayList);
        return "allocated";
    }

    @RequestMapping("/exception")
    public void exception() {
        try {
            throw new NullPointerException();
        } catch (NullPointerException exception) {
            logger.error("NullPointer", exception);
        }
    }

    @RequestMapping("/smallObjects")
    public void noException() {
        smallObjects(10);
    }


    public void smallObjects(int n) {
        for (int i = 0; i < n; ++i ) {
            Object small = new Object();
            smallObjects(i-1);
        }
    }


}
