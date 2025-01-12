package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.MethodOfReplenishment;
import org.spring_boot.gamestore.entity.UsersEWallet;

import java.math.BigDecimal;

public interface IUsersEWalletService {

    UsersEWallet addMethodOfReplenishmentForThisWallet(MethodOfReplenishment methodOfReplenishment, int usersEWalletId);

    UsersEWallet upBalanceOnWallet(int usersEWalletId, BigDecimal sum);

}
