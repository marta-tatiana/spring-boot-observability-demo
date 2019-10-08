package observability.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

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

    @RequestMapping("/compute_old")
    public String compute_old() {
        double result = 0;
        for (int i = 0; i < 100000; ++i) {
            result += Math.sqrt(Math.random()*i);

        }
        logger.debug("Computed " + result);
        return ""+result;
    }

    @RequestMapping("/compute")
    public String compute() {
        List<List<Boolean>> result = generate(21);
        return ""+result.size();
    }

    @RequestMapping("/allocate")
    public String allocate() {
        createDataSize(20);
        return "allocated";
    }

    private static String createDataSize(int msgSize) {
        StringBuilder sb = new StringBuilder(msgSize);
        for (int i=0; i<msgSize*(1000000/2); i++) {
            sb.append('a');
        }
        return sb.toString();
    }

    public static List<List<Boolean>> generate(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        if (n == 1) {
            List<Boolean> zero = Collections.singletonList(false);
            List<Boolean> one = Collections.singletonList(true);
            List<List<Boolean>> result = new ArrayList<>();
            result.add(zero);
            result.add(one);
            return result;
        }
        List<List<Boolean>> listOfLists = generate(n-1);
        List<List<Boolean>> extendedList = new ArrayList<>();
        for (List<Boolean> list : listOfLists){
            List<Boolean> with0 = new ArrayList(list);
            with0.add(false);
            List<Boolean> with1 = new ArrayList(list);
            with1.add(true);
            extendedList.add(with0);
            extendedList.add(with1);
        }
        return extendedList;
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
