package domain;

import domain.strategy.CustomPurchaseStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("상금과 수익률을 올바르게 계산하는지 테스트한다.")
public class LottosTest {

    private LottoGame lottos;
    private WinningLotto winningLotto;
    private int inputMoney;

    @BeforeEach
    void init() {
        inputMoney = 1000;
        lottos = new LottoGame(inputMoney);
        lottos.purchase(new CustomPurchaseStrategy(List.of(1, 2, 3, 43, 44, 45)));
        lottos.purchase(new CustomPurchaseStrategy(List.of(1, 2, 3, 4, 44, 45)));
        lottos.purchase(new CustomPurchaseStrategy(List.of(1, 2, 3, 4, 5, 45)));
        lottos.purchase(new CustomPurchaseStrategy(List.of(1, 2, 3, 4, 5, 12)));

        winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 12)), 6);
    }

    @Test
    @DisplayName("올바른 상금이 계산된다.")
    void lottos_makeRightTotalPrize() {
        PrizeResult finalResult = lottos.calculatePrizeResult(winningLotto);
        assertThat(finalResult.totalPrize()).isEqualTo(2001555000);
    }

    @Test
    @DisplayName("올바른 수익률이 계산된다.")
    void lottos_calculateEarningRate() {
        lottos.calculatePrizeResult(winningLotto);
        double expected = 2001555000.0 / 5000.0;
        assertThat(lottos.calculateEarningRate()).isEqualTo(expected);
    }

}
