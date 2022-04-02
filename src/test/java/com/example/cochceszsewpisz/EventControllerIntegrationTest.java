package com.example.cochceszsewpisz;

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
class EventControllerIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validPayloadShouldCreateEvent() throws Exception {
        final var payload = """
                {
                    "name": "Some event",
                    "date": "2023-01-01",
                    "place": "Warsaw",
                    "description": "An example description of the event"
                }
                """;

        mockMvc.perform(post("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", not(empty())))
                .andExpect(jsonPath("$.name", is("Some event")))
                .andExpect(jsonPath("$.date", is("2023-01-01")))
                .andExpect(jsonPath("$.place", is("Warsaw")))
                .andExpect(jsonPath("$.description", is("An example description of the event")));
    }

    @Test
    public void shouldShowEventList() throws Exception {
        final var payload = """
                {
                    "name": "Some event",
                    "date": "2023-01-01",
                    "place": "Warsaw",
                    "description": "An example description of the event"
                }
                """;

        mockMvc.perform(post("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload));
        mockMvc.perform(get("/event")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldShowEventById() throws Exception {
        final var payload = """
                {
                    "name": "Some event",
                    "date": "2023-01-01",
                    "place": "Warsaw",
                    "description": "An example description of the event"
                }
                """;

        String body = mockMvc.perform(post("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = JsonPath.read(body, "$.id");

        mockMvc.perform(get("/event/" + id)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", not(empty())))
                .andExpect(jsonPath("$.name", is("Some event")))
                .andExpect(jsonPath("$.date", is("2023-01-01")))
                .andExpect(jsonPath("$.place", is("Warsaw")))
                .andExpect(jsonPath("$.description", is("An example description of the event")));
    }

    @Test
    public void shouldEditEventById() throws Exception {
        final var payload = """
                {
                    "name": "Some event",
                    "date": "2023-01-01",
                    "place": "Warsaw",
                    "description": "An example description of the event"
                }
                """;

        final var update = """
                {
                    "name": "Amazing event",
                    "date": "2024-01-01",
                    "place": "Warsaw",
                    "description": "An example description of the amazing event"
                }
                """;

        String body = mockMvc.perform(post("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = JsonPath.read(body, "$.id");

        mockMvc.perform(put("/event/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(update)
        ).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", not(empty())))
                .andExpect(jsonPath("$.name", is("Amazing event")))
                .andExpect(jsonPath("$.date", is("2024-01-01")))
                .andExpect(jsonPath("$.place", is("Warsaw")))
                .andExpect(jsonPath("$.description", is("An example description of the amazing event")));
    }

    @Test
    public void shouldDeleteEvent() throws Exception {
        final var payload = """
                {
                    "name": "Some event",
                    "date": "2023-01-01",
                    "place": "Warsaw",
                    "description": "An example description of the event"
                }
                """;

        String body = mockMvc.perform(post("/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = JsonPath.read(body, "$.id");

        mockMvc.perform(delete("/event/" + id)
        ).andExpect(status().is2xxSuccessful());
    }

}
