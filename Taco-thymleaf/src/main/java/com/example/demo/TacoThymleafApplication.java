package com.example.demo;

import com.example.demo.Model.Ingredient;
import com.example.demo.Model.Order;
import com.example.demo.Model.Taco;
import com.example.demo.resources.RecentProcessor;
import com.example.demo.resources.TacoResource;
import com.example.demo.restTemplate.TacoClient;
import com.example.demo.restTemplate.TacoRecentClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.webmvc.ProfileResourceProcessor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.server.EntityLinks;
//ACTIVEMQ
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.config.JmsListenerContainerFactory;
//import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
//END
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import javax.jms.ConnectionFactory;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@Slf4j
@ComponentScan
//@EnableJms
public class TacoThymleafApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TacoThymleafApplication.class, args);
    }


    @Bean
    public RecentProcessor recentProcessor(){
        return new RecentProcessor();
    }
    @Bean
    public Traverson traverson() {
        return new Traverson(URI.create("http://localhost:8080"), MediaTypes.HAL_JSON);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //    @Bean
//    public CommandLineRunner fetchIngredients(TacoClient tacoCloudClient) {
//        return args -> {
//            log.info("----------------------- GET -------------------------");
//            log.info("GETTING INGREDIENT BY IDE");
//            log.info("Ingredient:  " + tacoCloudClient.getIngredientById("CHED"));
//            log.info("GETTING ALL INGREDIENTS");
//            List<Ingredient> ingredients = tacoCloudClient.getAllIngredients();
//            log.info("All ingredients:");
//            for (Ingredient ingredient : ingredients) {
//                log.info("   - " + ingredient);
//
//            }
//        };
//    }
//    @Bean
//    public CommandLineRunner getRecentTacos(TacoRecentClient tacoRecentClient) {
//        return args -> {
//            log.info("-------------------------GET-Recent-Taco-----------------");
//            Iterable<Taco> tacos = tacoRecentClient.getRecentTacos();
//            for (Taco taco : tacos) {
//                log.info(" - " + taco);
//            }
//        };
//    }
//        @Bean
//        public CommandLineRunner addIngredient(TacoClient tacoClient){
//        return args -> {
//            log.info("--------------- ADD INGREDIENT ---------------");
//            Ingredient ingredient = new Ingredient("HHHH","HuyNe",Ingredient.Type.WRAP);
//            Ingredient ingredient1 = tacoClient.addIngredient(ingredient);
//            log.info("sssss" + ingredient1);
//        };
//        }
    //
//    @Bean
//    public CommandLineRunner addAnIngredient(TacoClient tacoCloudClient) {
//        return args -> {
//            log.info("----------------------- POST -------------------------");
//            Ingredient chix = new Ingredient("CHIX", "Shredded Chicken", Ingredient.Type.PROTEIN);
//            Ingredient chixAfter = tacoCloudClient.postAnIngredient(chix);
//            log.info("AFTER=1:  " + chixAfter);
//            Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
//            URI uri = tacoCloudClient.createIngredient(beefFajita);
//            log.info("AFTER-2:  " + uri);
//            Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
//            Ingredient shrimpAfter = tacoCloudClient.createIngredient(shrimp);
//            log.info("AFTER-3:  " + shrimpAfter);
//        };
//    }
//    @Bean
//    public CommandLineRunner updateAnIngredient(TacoClient tacoClient){
//      return args -> {
//          log.info("--------------------PUT-------------");
//          log.info("Ingredient before :" + tacoClient.getIngredientById("CHED"));
//          tacoClient.updateIngredient(new Ingredient("CHED","caiconcac",Ingredient.Type.VEGGIES));
//      };
//    }
//    @Bean
//    public CommandLineRunner deleteAnIngredient(TacoClient tacoCloudClient){
//        return args -> {
//            log.info("-------------------------DELETE---------------");
//            // start by adding a few ingredients so that we can delete them later...
//            Ingredient ingredient = tacoCloudClient.getIngredientById("CHIX");
//            tacoCloudClient.deleteAnIngredient(ingredient);
//        };
//    }
    //	@Bean
//	  public CommandLineRunner dataLoader(IngredientRepository repo) {
//	    return new CommandLineRunner() {
//	      @Override
//	      public void run(String... args) throws Exception {
//	        repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
//	        repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
//	        repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
//	        repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
//	        repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
//	        repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
//	        repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
//	        repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
//	        repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
//	        repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//	      }
//	    };
//	  }
//
//


    //MESSAGECONVERTER OF ACTIVEMQ
//    @Bean
//    public MappingJackson2MessageConverter messageConverter() {
//        MappingJackson2MessageConverter messageConverter =
//                new MappingJackson2MessageConverter();
//        messageConverter.setTypeIdPropertyName("_typeId");
//        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
//        typeIdMappings.put("order", Order.class); messageConverter.setTypeIdMappings(typeIdMappings);
//        return messageConverter;
//    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login");
    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
