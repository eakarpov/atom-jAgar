package database;

import dao.UserDao;
import entities.user.UserEntity;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class UserEntityDaoTest {

    private UserDao userDao;
    private UserEntity firstTestUser;
    private UserEntity secondTestUser;
    private UserEntity thirdTestUser;

    @Before
    public void setUp() {
        userDao = new UserDao();
        firstTestUser = new UserEntity("TestName", "TestPassword");
        secondTestUser = new UserEntity("user", "pass");
        thirdTestUser = new UserEntity("Jegor", "dmonelove");
    }

    @Test
    public void getAllUsersTest() {
        assertThat(userDao.getAll()).hasSize(0);
    }

    @Test
    public void getAllLoginUsersTest() {
        assertThat(userDao.getAllLoginUsers()).hasSize(0);
    }

    @Test
    public void insertUserTest() {
        final int initialSize = userDao.getAll().size();
        userDao.insert(firstTestUser);
        assertThat(userDao.getAll())
                .hasSize(initialSize + 1)
                .extracting(UserEntity::getName, UserEntity::getRegistrationDate)
                .contains(tuple("TestName", LocalDate.now()));
        userDao.delete(firstTestUser);
    }

    @Test
    public void deleteTest() {
        userDao.insert(secondTestUser);
        final int initialSize = userDao.getAll().size();
        userDao.delete(secondTestUser);
        assertThat(userDao.getAll()).hasSize(initialSize - 1);
    }

    @Test
    public void insertAllTest(){
        final int initialSize = userDao.getAll().size();
        userDao.insertAll(firstTestUser, secondTestUser, thirdTestUser);
        assertThat(userDao.getAll()).hasSize(initialSize + 3);
        userDao.deleteAll(firstTestUser, secondTestUser, thirdTestUser);
    }

    @Test
    public void deleteAllTest(){
        userDao.insertAll(firstTestUser, secondTestUser, thirdTestUser);
        final int initialSize = userDao.getAll().size();
        userDao.deleteAll(firstTestUser, secondTestUser, thirdTestUser);
        assertThat(userDao.getAll()).hasSize(initialSize - 3);
    }

}
