package com.example.cochceszsewpisz;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LocalUserControllerIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validPayloadShouldCreateLocalUser() throws Exception {
        final var payload = """
                {
                    "name": "Marcin Toster",
                    "email": "marcin.toster@gmail.com"
                }
                """;

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", not(empty())))
                .andExpect(jsonPath("$.name", is("Marcin Toster")))
                .andExpect(jsonPath("$.email", is("marcin.toster@gmail.com")));
    }

    @Test
    public void shouldShowLocalUserList() throws Exception {
        final var payload = """
                {
                    "name": "Marcin Toster",
                    "email": "marcin.toster@gmail.com"
                }
                """;

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload));
        mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", not(empty())))
                .andExpect(jsonPath("$.name", is("Marcin Toster")))
                .andExpect(jsonPath("$.email", is("marcin.toaster@gmail.com")));
    }

    @Test
    public void shouldShowLocalUserById() throws Exception {
        final var payload = """
                {
                    "name": "Marcin Toster",
                    "email": "marcin.toster@gmail.com"
                }
                """;

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload));
        mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldEditLocalUserById() throws Exception {
        final var payload = """
                {
                    "name": "Marcin Toster",
                    "email": "marcin.toster@gmail.com"
                }
                """;

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload));
        final var payload1 = """
                {
                    "name": "Marcin Toaster",
                    "email": "marcin.toaster@gmail.com"
                }
                """;
        mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload1)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", not(empty())))
                .andExpect(jsonPath("$.name", is("Marcin Toaster")))
                .andExpect(jsonPath("$.email", is("marcin.toaster@gmail.com")));
    }

    @Test
    public void shouldDeleteLocalUser() throws Exception {
        final var payload = """
                {
                    "name": "Marcin Toster",
                    "email": "marcin.toster@gmail.com"
                }
                """;

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload));
        mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());
    }

}
