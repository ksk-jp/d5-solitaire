package d5.solitaire.domain.card;

public enum Color {
  RED, BLACK;

  public boolean isReverseColor(Color color) {
    return this != color;
  }
}
