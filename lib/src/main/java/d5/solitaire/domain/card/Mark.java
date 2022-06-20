package d5.solitaire.domain.card;

public enum Mark {
  SPADE(Color.BLACK, "♠"), HEART(Color.RED, "♡"), DIAMOND(Color.RED, "♢"), CLUB(Color.BLACK, "♣");

  private final Color color;
  private final String name;

  private Mark(Color color, String name) {
    this.color = color;
    this.name = name;
  }

  public Color getColor() {
    return color;
  }

  public String getName() {
    return name;
  }

  public boolean isReverseColor(Mark other) {
    return this.color.isReverseColor(other.color);
  }
}
