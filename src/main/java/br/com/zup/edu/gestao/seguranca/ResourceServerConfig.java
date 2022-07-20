package br.com.zup.edu.gestao.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                    .csrf().disable()
                    .httpBasic().disable()
                    .rememberMe().disable()
                    .formLogin().disable()
                    .logout().disable()
                    .requestCache().disable()
                    .headers().frameOptions().deny()
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(STATELESS)
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/api/funcionarios").hasAuthority("SCOPE_funcionarios:write")
                        .antMatchers(HttpMethod.DELETE, "/api/funcionarios/**").hasAuthority("SCOPE_funcionarios:write")
                        .antMatchers(HttpMethod.GET, "/api/funcionarios").hasAuthority("SCOPE_funcionarios:read")
                    .anyRequest()
                        .authenticated()
                .and()
                    .oauth2ResourceServer()
                        .jwt()
        ;
    }

}
