package d5.solitaire.domain.card;

public class Card {
  private Mark mark;
  private Number number;
  private Side side;

  public Card(Mark mark, Number number, Side side) {
    this.mark = mark;
    this.number = number;
    this.side = side;
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

}
