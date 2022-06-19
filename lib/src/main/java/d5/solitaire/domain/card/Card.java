package d5.solitaire.domain.card;

public class Card {
  private Mark mark;
  private Number number;
  private Side side;

  private Card(Mark mark, Number number, Side side) {
    this.mark = mark;
    this.number = number;
    this.side = side;
  }

  public static Card of(Mark mark, Number number, Side side) {
    return new Card(mark, number, side);
  }

  public Mark getMark() {
    return mark;
  }

  public Number getNumber() {
    return number;
  }

  public Side getSide() {
    return side;
  }

  public void open() {
    this.side = Side.FRONT;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj instanceof Card) {
      Card card = (Card) obj;
      return this.mark.equals(card.getMark())
          && this.number.equals(card.getNumber())
          && this.side.equals(card.getSide());
    }
    return false;
  }

}
