package com.jshi.laughtale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jshi.laughtale.member.dto.MemberUpdate;
import com.jshi.laughtale.member.dto.MemberUpdate.RoleRequest;
import com.jshi.laughtale.member.service.MemberService;
import com.jshi.laughtale.security.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    @WithMockUser
    public void testAccessDenied() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/member/role"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testAuthenticationFailure() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member/modify")
                        .param("username", "invalid_username")
                        .param("password", "invalid_password"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testAuthorizedEndpoint() throws Exception {
        RoleRequest request = RoleRequest.builder().email("test@test.com").role(Role.ROLE_USER).build();

        doNothing().when(memberService).updateRole(any(MemberUpdate.RoleRequest.class));

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member/role")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}