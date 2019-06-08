package lotto.domain.lotto;

public enum LottoType {
    MANUAL(1),
    AUTOMATIC(2),
    WINNING(3);

    private int code;

    LottoType(int code) {
        this.code = code;
    }
}