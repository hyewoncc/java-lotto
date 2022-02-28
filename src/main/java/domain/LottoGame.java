package domain;

import domain.strategy.PurchaseStrategy;
import domain.strategy.RandomPurchaseStrategy;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_MONEY_UNDER_MINIMUM = "최소 1000원의 금액을 입력해주세요.";

    private List<Lotto> lottos = new ArrayList<>();
    private PrizeResult prizeResult = new PrizeResult();

    public LottoGame(int inputMoney) {
        if (inputMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_MONEY_UNDER_MINIMUM);
        }
        purchaseMaximumLottos(inputMoney);
    }

    private void purchaseMaximumLottos(int money) {
        for (int i = 0; i < money; i += LOTTO_PRICE) {
            purchase(new RandomPurchaseStrategy());
        }
    }

    public void purchase(PurchaseStrategy purchaseStrategy) {
        Lotto lotto = new Lotto(purchaseStrategy.getNumbers());
        lottos.add(lotto);
    }

    public PrizeResult calculatePrizeResult(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            prizeResult.updatePrizeResult(winningLotto.calculatePrize(lotto));
        }
        return prizeResult;
    }

    public double calculateEarningRate() {
        long totalPrize = prizeResult.totalPrize();
        double earningRate = (double)totalPrize / (lottos.size() * LOTTO_PRICE);
        return Math.floor(earningRate * 100) / 100.0;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public PrizeResult getPrizeResult() {
        return prizeResult;
    }

}
