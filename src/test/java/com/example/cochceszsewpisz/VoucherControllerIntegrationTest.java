package com.example.cochceszsewpisz;

import com.example.cochceszsewpisz.Voucher.VoucherStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class VoucherControllerIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validPayloadShouldCreateLocalUser() throws Exception {
        final var payload = """
                {
                    "status": "CREATED"
                }
                """;

        mockMvc.perform(post("/voucher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.code", not(empty())))
                .andExpect(jsonPath("$.status", is("CREATED")));
    }

}
