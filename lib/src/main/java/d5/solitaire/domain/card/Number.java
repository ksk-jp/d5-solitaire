package d5.solitaire.domain.card;

public enum Number {
  ACE(1, "1"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"),
  NINE(9, "9"), TEN(10, "10"), JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K");

  private final int value;
  private final String name;

  private Number(int value, String name) {
    this.value = value;
    this.name = name;
  }

  // private int getValue() {
  // return value;
  // }

  // XXX:ひょっとしたらいらない？
  public String getName() {
    return name;
  }

  public boolean isOneSmallerThan(Number number) {
    return this.value == number.value - 1;
  }

  public boolean isOneLargerThan(Number number) {
    return this.value == number.value + 1;
  }

  @Override
  public String toString() {
    return name;
  }
}
