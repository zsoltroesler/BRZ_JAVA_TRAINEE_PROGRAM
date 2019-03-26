package at.gv.brz.configurations;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class Konfiguration {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory =
                new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Type.HTTP, 
                                new InetSocketAddress("proxy1.brz.gv.at", 8080));
        requestFactory.setProxy(proxy);
        return new RestTemplate(requestFactory);
    }
}
