package ru.tokarev.shop;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@Data
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MvcTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void startPageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.core.StringContains.containsString("Shop now")));
    }

    @Test
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "1111", roles = "ADMIN")
//    @WithAnonymousUser
    public void securityAllowedTest() throws Exception {
        mockMvc.perform(get("/admin")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void correctLoginTest() throws Exception {
        mockMvc.perform(formLogin("/authenticateTheUser").user("1111").password("1111"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void notCorrectLoginTest() throws Exception {
        mockMvc.perform(formLogin("/authenticateTheUser").user("111111").password("111"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
}
