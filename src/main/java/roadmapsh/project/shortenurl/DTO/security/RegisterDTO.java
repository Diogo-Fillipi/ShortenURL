package roadmapsh.project.shortenurl.DTO.security;

import roadmapsh.project.shortenurl.security.UserRoles;

public record RegisterDTO(
        String login,
        String password
) {
}
