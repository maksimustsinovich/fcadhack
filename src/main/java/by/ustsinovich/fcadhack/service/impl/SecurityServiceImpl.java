package by.ustsinovich.fcadhack.service.impl;

import by.ustsinovich.fcadhack.entity.User;
import by.ustsinovich.fcadhack.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    @Override
    public boolean isSameUser(User principal, Long id) {
        return principal.getId().equals(id);
    }

}
