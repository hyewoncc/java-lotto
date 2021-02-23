package lotto.lottogame;

import lotto.lottoticket.BonusBall;
import lotto.lottoticket.LottoTickets;
import lotto.lottoticket.WinnerTicket;
import lotto.lottoticket.ticketnumber.RandomNumbersGenerator;
import lotto.money.Money;
import lotto.money.PrizeMoney;
import lotto.ranking.Statistics;

public class LottoGame {
    private final LottoTickets lottoTickets;

    public LottoGame(LottoCount lottoCount) {
        this.lottoTickets = new LottoTickets(lottoCount, new RandomNumbersGenerator());
    }

    public Statistics createStatistics(WinnerTicket winnerTicket, BonusBall bonusBall) {
        return new Statistics(lottoTickets.makeResult(winnerTicket, bonusBall));
    }

    public double createResult(Statistics statistics, Money money) {
        return new PrizeMoney(statistics).calculateProfit(money);
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }
}