package d5.solitaire.domain.board;

import d5.solitaire.domain.card.Card;
import d5.solitaire.domain.card.Side;
import io.vavr.collection.List;

public class Yamafuda {
  private List<Card> cards;

  private Yamafuda(List<Card> cards) {
    this.cards = cards;
  }

  public static Yamafuda of(List<Card> cards) {
    // TODO: 自由度が高すぎるので、改善したい
    return new Yamafuda(cards);
  }

  public Card movableCard() {
    return this.cards.filter(card -> card.getSide() == Side.FRONT).last();
  }

  public void turn() {
    if (this.cards.exists(card -> card.getSide() == Side.BACK)) {
      this.cards.filter(card -> card.getSide() == Side.BACK).head().open();
      return;
    }
    this.cards.forEach(card -> card.close());
  }

  public void remove(Card card) {
    this.cards = this.cards.remove(card);
  }

}
