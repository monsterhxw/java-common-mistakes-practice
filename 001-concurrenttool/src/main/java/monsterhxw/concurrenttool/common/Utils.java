package monsterhxw.concurrenttool.common;

import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@Slf4j
public class Utils {

    public static void loadPropertySource(String fileName) {
        Resource resource = new ClassPathResource(fileName);
        try {
            Properties p = PropertiesLoaderUtils.loadProperties(resource);
            p.forEach((k, v) -> {
                log.info("{}={}", k, v);
                System.setProperty(k.toString(), v.toString());
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}