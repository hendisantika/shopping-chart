package com.hendisantika.shoppingchart.dao;

import com.hendisantika.shoppingchart.entity.Account;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * Project : shopping-chart
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/07/18
 * Time: 06.51
 * To change this template use File | Settings | File Templates.
 */
@Transactional
@Repository
@RequiredArgsConstructor
public class AccountDAO {

    private final SessionFactory sessionFactory;

    public Account findAccount(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Account.class, userName);
    }

}
