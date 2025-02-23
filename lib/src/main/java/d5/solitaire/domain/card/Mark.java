package d5.solitaire.domain.card;

import io.vavr.collection.List;

public enum Mark {
  SPADE(Color.BLACK, "♠"), HEART(Color.RED, "♡"), DIAMOND(Color.RED, "♢"), CLUB(Color.BLACK, "♣");

  private final Color color;
  private final String name;

  private Mark(Color color, String name) {
    this.color = color;
    this.name = name;
  }

  protected static List<Mark> all() {
    return List.of(SPADE, HEART, DIAMOND, CLUB);
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
