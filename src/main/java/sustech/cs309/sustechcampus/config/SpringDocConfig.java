package sustech.cs309.sustechcampus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

  @Bean
  public OpenAPI myOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("SustechCampusApis")
        .version("v1.0.0"));
  }

  @Bean
  public GroupedOpenApi pageApi() {
    return GroupedOpenApi.builder()
      .group("api")
      .pathsToMatch("/api/**")
      .build();
  }

  @Bean
  public GroupedOpenApi restApi() {
    return GroupedOpenApi.builder()
      .group("page")
      .pathsToMatch("/page/**")
      .build();
  }
}
