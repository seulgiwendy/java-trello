package codesquad.dto;

import codesquad.model.User;

public class UserDto {

    private String userId;
    private String password;
    private String name;

    public UserDto(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.name = user.getName();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
