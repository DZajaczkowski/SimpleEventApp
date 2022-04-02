package com.example.cochceszsewpisz;

import com.example.cochceszsewpisz.Voucher.VoucherStatus;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class VoucherControllerIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validPayloadShouldCreateVoucher() throws Exception {
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

    @Test
    public void shouldShowVoucherList() throws Exception {
        final var payload = """
                {
                    "status": "CREATED"
                }
                """;

        mockMvc.perform(post("/voucher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload));
        mockMvc.perform(get("/voucher")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldShowVoucherById() throws Exception {
        final var payload = """
                {
                    "status": "CREATED"
                }
                """;

        String body = mockMvc.perform(post("/voucher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String code = JsonPath.read(body, "$.code");

        mockMvc.perform(get("/voucher/" + code)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.code", not(empty())))
                .andExpect(jsonPath("$.status", is("CREATED")));
    }

    @Test
    public void shouldEditVoucherById() throws Exception {
        final var payload = """
                {
                    "status": "CREATED"
                }
                """;

        final var update = """
                {
                    "status": "ACTIVATED"
                }
                """;

        String body = mockMvc.perform(post("/voucher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String code = JsonPath.read(body, "$.code");

        mockMvc.perform(put("/voucher/" + code)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(update)
        ).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.code", not(empty())))
                .andExpect(jsonPath("$.status", is("ACTIVATED")));
    }

    @Test
    public void shouldDeleteVoucher() throws Exception {
        final var payload = """
                {
                    "status": "CREATED"
                }
                """;

        String body = mockMvc.perform(post("/voucher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String code = JsonPath.read(body, "$.code");

        mockMvc.perform(delete("/voucher/" + code)
        ).andExpect(status().is2xxSuccessful());
    }

}
