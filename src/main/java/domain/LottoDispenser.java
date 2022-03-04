package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoDispenser {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final String ERROR_UNDER_MINIMUM_MONEY = "최소 1000원을 입력하세요.";
    private static final String ERROR_INVALID_COUNT = "로또 수는 자연수로 입력하세요.";
    private static final String ERROR_NOT_ENOUGH_MONEY = "돈이 모자라 구매할 수 없습니다.";

    private Money money;

    public LottoDispenser(int money) {
        this.money = new Money(money);
    }

    public void checkEnoughMoneyRemain(int lottoCount) {
        if (lottoCount < 0) {
            throw new IllegalArgumentException(ERROR_INVALID_COUNT);
        }
        money.enoughToBuy(lottoCount);
    }

    public Lotto buyManualLotto(List<Integer> lottoNumbers) {
        money.buyOne();
        return new Lotto(lottoNumbers);
    }

    public List<Lotto> buyAutoLottos() {
        List<Lotto> autoLottos = new ArrayList<>();
        while (money.enoughToBuyOne()) {
            money.buyOne();
            autoLottos.add(new Lotto(getRandomNumbers()));
        }
        return autoLottos;
    }

    private List<Integer> getRandomNumbers() {
        List<Integer> balls = new ArrayList<>();
        for (int i = MINIMUM_NUMBER; i <= MAXIMUM_NUMBER; i++) {
            balls.add(i);
        }
        Collections.shuffle(balls);

        return balls.subList(0, 6).stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private static class Money{

        private int amount;

        private Money(int amount) {
            this.amount = amount;
            if (!enoughToBuyOne()) {
                throw new IllegalArgumentException(ERROR_UNDER_MINIMUM_MONEY);
            }
        }

        private void buyOne() {
            amount -= Lotto.PRICE;
        }

        private void enoughToBuy(int lottoCount) {
            if (this.amount < lottoCount * Lotto.PRICE) {
                throw new IllegalArgumentException(ERROR_NOT_ENOUGH_MONEY);
            }
        }

        private boolean enoughToBuyOne() {
            return amount >= Lotto.PRICE;
        }
    }

}
