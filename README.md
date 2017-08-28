package A;




import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;




@Configuration

@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {




  @Override

  public void configure(AuthenticationManagerBuilder auth) throws Exception {




    auth.inMemoryAuthentication()

      .withUser("user").password("user").roles("USER").and()

      .withUser("admin").password("admin").roles("USER", "ADMIN");

  }




  

  @Override

  protected void configure(HttpSecurity http) throws Exception {




    http

        .httpBasic().and()

        .authorizeRequests()

        .antMatchers(HttpMethod.GET, "/doctors").hasRole(“USER”)

        .antMatchers(HttpMethod.GET, "/doctors/**").hasRole(“USER”)

        .and()

        .csrf().disable();
   
    http

        .httpBasic().and()

        .authorizeRequests()

        .antMatchers(HttpMethod.GET, “/patients”).hasRole(“USER”)

        .antMatchers(HttpMethod.GET, “/patients/**").hasRole(“USER”)

        .and()

        .csrf().disable();
   
   http

        .httpBasic().and()

        .authorizeRequests()

        .antMatchers(HttpMethod.GET, “/assigned”).hasRole(“USER”)

        .antMatchers(HttpMethod.GET, “/assigned/**”).hasRole(“USER”)

        .and()

        .csrf().disable();
   
http

      .httpBasic().and()

        .authorizeRequests()

        .antMatchers(HttpMethod.POST, "/doctors").hasRole("ADMIN")

        .antMatchers(HttpMethod.PUT, "/doctors/**").hasRole("ADMIN")

        .antMatchers(HttpMethod.PATCH, "/doctors/**").hasRole("ADMIN").and()

        .csrf().disable();

    

    http

      .httpBasic().and()

      .authorizeRequests()

        .antMatchers(HttpMethod.POST, "/patients").hasRole("ADMIN")

        .antMatchers(HttpMethod.PUT, "/patients/**").hasRole("ADMIN")

        .antMatchers(HttpMethod.PATCH, "/patients/**").hasRole("ADMIN").and()

      .csrf().disable();

    

    http

      .httpBasic().and()

      .authorizeRequests()

        .antMatchers(HttpMethod.POST, "/assigned").hasRole("ADMIN")

        .antMatchers(HttpMethod.PUT, "/assigned/**").hasRole("ADMIN")

        .antMatchers(HttpMethod.PATCH, "/assigned/**").hasRole("ADMIN").and()

      .csrf().disable();

  }

}

