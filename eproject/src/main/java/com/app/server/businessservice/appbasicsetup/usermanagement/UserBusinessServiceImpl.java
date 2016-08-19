package com.app.server.businessservice.appbasicsetup.usermanagement;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.User;
import java.lang.Override;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Update the <User> object
     * @Params Object of User
     * @throws java.lang.Exception
     */
    @Override
    public void update(User entity) throws Exception {
        if (entity.isHardDelete()) {
            userRepository.delete(entity.getUserId());
        } else {
            userRepository.deletePassRecovery(entity.getDeletedPassRecoveryList());
            userRepository.update(entity);
        }
    }

    /**
     * Update the list of <User> object
     * @Params List of User Object
     * @throws java.lang.Exception
     */
    @Override
    public void update(List<User> entity) throws Exception {
        for (User _user : entity) {
            if (_user.isHardDelete()) {
                userRepository.delete(_user.getUserId());
            } else {
                userRepository.deletePassRecovery(_user.getDeletedPassRecoveryList());
                userRepository.update(_user);
            }
        }
    }
}
