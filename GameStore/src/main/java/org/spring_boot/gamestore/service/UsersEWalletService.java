package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.MethodOfReplenishment;
import org.spring_boot.gamestore.entity.UsersEWallet;
import org.spring_boot.gamestore.repository.UsersEWalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersEWalletService implements IUsersEWalletService{

    @Autowired
    private UsersEWalletRepo usersEWalletRepo;

    @Override
    public UsersEWallet addMethodOfReplenishmentForThisWallet(MethodOfReplenishment methodOfReplenishment, int usersEWalletId){
//        ищем конкретный кошелек по id
        UsersEWallet usersEWallet = usersEWalletRepo.findById(usersEWalletId).orElse(null);
//        создаем новый список методов пополнения, беря за основу те, что есть у кошелька в БД
        List<MethodOfReplenishment> methodsOfReplenishments = new ArrayList<>(usersEWallet.getMethodOfReplenishment());
//        добавим в метод пополнения кошелек
        methodOfReplenishment.setUsersEWallet(usersEWallet);
//        добавляем наш новый метод пополнения
        methodsOfReplenishments.add(methodOfReplenishment);
//        устанавливаем обновленный список методов пополнения для кошелька
        usersEWallet.setMethodOfReplenishment(methodsOfReplenishments);
//        сохранить кошелек
        UsersEWallet res = usersEWalletRepo.save(usersEWallet);
        return res;
    }

    @Override
    public UsersEWallet upBalanceOnWallet(int usersEWalletId, BigDecimal sum) {
        //        ищем конкретный кошелек по id
        UsersEWallet usersEWallet = usersEWalletRepo.findById(usersEWalletId).orElse(null);
        BigDecimal balance = usersEWallet.getBalance();
        usersEWallet.setBalance(balance.add(sum));
        return usersEWalletRepo.save(usersEWallet);
    }
}
