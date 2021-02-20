# java-lotto
로또 미션 진행을 위한 저장소
- [x] 금액을 입력받는다.
```
[예외]
🆗 숫자가 아닌 경우
🆗 음수인 경우
🆗 공백 입력
```
- [x] 금액에 맞게 로또를 구매해야한다.
- [x] 무작위로 로또 번호를 추첨한다.
- [x] 당첨 번호를 입력받는다.
- [x] 보너스 볼을 입력받는다.
```
[공통 예외]
🆗 숫자가 아닌 경우
🆗 1~45까지의 범위가 아닌 경우
🆗 공백 입력

[당첨 번호 예외]
🆗 입력 값이 6개가 아닌 경우
🆗 같은 번호를 중복해서 입력한 경우
🆗 구분자가 콤마가 아닌 경우

[보너스볼 예외]
🆗 당첨 번호 중 하나를 다시 입력한 경우
```
- [x] 당첨 통계를 계산한다.
  - [x] 몇개를 맞췄는지 구한다.
  - [x] 일치하는 번호 개수에 따른 통계값을 구한다. (3개 일치 - 1개, 4개 일치 - 2개...)  
  - [x] 맞춘 개수에 따라 상금을 구한다.
- [x] 수익률을 계산한다.

## 구현 규칙
- 배열 대신 ArrayList를 사용한다.
- enum을 이용한다.
- 모든 원시값과 문자열을 포장한다.
- 줄여쓰지 않는다.
- 일급 콜렉션을 쓴다. -> 로또 번호 6개
