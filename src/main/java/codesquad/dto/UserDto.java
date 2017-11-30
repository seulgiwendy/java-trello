package codesquad.dto;

import codesquad.model.Account;

public class UserDto {

    private String userId;
    private String password;
    private String name;

    public UserDto(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public UserDto(Account account) {
        this.userId = account.getUserId();
        this.password = account.getPassword();
        this.name = account.getName();
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
