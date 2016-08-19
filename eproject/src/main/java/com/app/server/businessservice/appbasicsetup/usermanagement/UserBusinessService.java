package com.app.server.businessservice.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.User;
import java.util.List;

public interface UserBusinessService {

    void update(User entity) throws Exception;

    void update(List<User> entity) throws Exception;
}
