import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Object.class);
        logger.error("123");
    }
}
