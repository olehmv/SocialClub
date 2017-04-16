package plittr.repository;
 
import java.util.Date;
 
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import plittr.entity.PersistentLogin;
 

 
@Repository
@Transactional
public class TokenRepositoryImpl 
        implements PersistentTokenRepository {
 
    static final Logger logger = LoggerFactory.getLogger(TokenRepositoryImpl.class);
    @Autowired
    PersistentLoginRepository persistentLoginRepository;
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        logger.info("Creating Token for user : {}", token.getUsername());
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        persistentLoginRepository.save(persistentLogin);
 
    }
 
    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        logger.info("Fetch Token if any for seriesId : {}", seriesId);
       
            PersistentLogin persistentLogin = persistentLoginRepository.findBySeries(seriesId);
 
            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
    }
 
    @Override
    public void removeUserTokens(String username) {
        logger.info("Removing Token if any for user : {}", username);
       
        PersistentLogin persistentLogin = persistentLoginRepository.findByUsername(username);
        if (persistentLogin != null) {
            logger.info("rememberMe was selected");
            persistentLoginRepository.delete(persistentLogin);
        }
 
    }
 
    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        logger.info("Updating Token for seriesId : {}", seriesId);
        PersistentLogin persistentLogin = persistentLoginRepository.findBySeries(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        persistentLoginRepository.save(persistentLogin);
    }
 
}
