package view;

import domain.Lotto;
import domain.Prize;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LOTTOS_COUNT_MESSAGE = "%n수동으로 %d장, 자동으로 %d장을 구매했습니다.%n";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final String DELIMITER = ", ";
    private static final String WINNING_STATISTIC = "당첨 통계%n---------%n";
    private static final String STATISTICS_PRIZE_MESSAGE = "%d개 일치 (%d원)- %d개%n";
    private static final String STATISTICS_SECOND_PRIZE_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d원)- %d개%n";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %.2f입니다.";

    public static void printLottosCount(int manualLottos, int autoLottos) {
        System.out.printf(LOTTOS_COUNT_MESSAGE, manualLottos, autoLottos);
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.print(LEFT_BRACKET);
            String lottoNumbers = String.join(DELIMITER, lotto.getLottoNumbers().stream()
                    .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                    .collect(Collectors.toList()));
            System.out.print(lottoNumbers);
            System.out.println(RIGHT_BRACKET);
        }
        System.out.print(System.lineSeparator());
    }

    public static void printFinalStatistic(Map<Prize, Integer> prizeResult) {
        System.out.printf(System.lineSeparator() + WINNING_STATISTIC);
        for (Prize winnerPrice : Prize.getValidPrizes()) {
            printEachStatistic(winnerPrice, prizeResult);
        }
    }

    private static void printEachStatistic(Prize winnerPrize, Map<Prize, Integer> prizeResult) {
        if (winnerPrize == Prize.SECOND) {
            System.out.printf(STATISTICS_SECOND_PRIZE_MESSAGE,
                    winnerPrize.getMatched(), winnerPrize.getPrize(), prizeResult.get(winnerPrize));
            return;
        }
        System.out.printf(STATISTICS_PRIZE_MESSAGE,
                winnerPrize.getMatched(), winnerPrize.getPrize(), prizeResult.get(winnerPrize));
    }

    public static void printEarningRate(double earningRate) {
        System.out.printf(EARNING_RATE_MESSAGE, earningRate);
    }

}
