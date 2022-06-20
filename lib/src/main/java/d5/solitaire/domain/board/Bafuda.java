package d5.solitaire.domain.board;

import com.google.common.annotations.VisibleForTesting;

import d5.solitaire.domain.card.Card;
import d5.solitaire.domain.card.Number;
import d5.solitaire.domain.card.Side;
import d5.solitaire.domain.exception.SolitaireException;
import io.vavr.collection.List;

public class Bafuda {
  private List<Card> cards;

  private Bafuda(List<Card> cards) {
    this.cards = cards;
  }

  public static Bafuda of(List<Card> cards) {
    // TODO: 自由度が高すぎるので、改善したい
    return new Bafuda(cards);
  }

  private boolean isEmpty() {
    return this.cards.isEmpty();
  }

  private boolean isAppendable(List<Card> cards) {
    // append対象のカードのリストについて、裏のカードが有る場合はfalseを返す
    if (cards.exists(card -> card.getSide() == Side.BACK)) {
      return false;
    }
    if (this.isEmpty()) {
      if (cards.head().getNumber() == Number.KING) {
        return true;
      } else {
        return false;
      }
    }

    var lastCard = this.cards.last();
    // 場札の最後のカードが裏返しの場合、どんなカードも追加できない
    if (lastCard.getSide() == Side.BACK) {
      return false;
    }

    if (cards.head().getNumber().isOneSmallerThan(lastCard.getNumber())
        && cards.head().getMark().isReverseColor(lastCard.getMark())) {
      return true;
    }
    return false;

  }

  public void append(List<Card> cards) {
    if (!this.isAppendable(cards)) {
      throw new SolitaireException();
    }
    this.cards = this.cards.appendAll(cards);
  }

  // TODO: Listの入れ子を返すようにする。
  public List<Card> movableCards() {
    return this.cards.filter(card -> card.getSide() == Side.FRONT);
  }

  public void removeBehindFrom(Card card) {
    if (!this.movableCards().contains(card)) {
      throw new SolitaireException();
    }

    this.cards = this.cards.zipWithIndex()
        .filter(tuple -> tuple._2 < this.cards.indexOf(card))
        .map(tuple -> tuple._1);
  }

  public void open() {
    if (this.cards.last().getSide() == Side.FRONT) {
      throw new SolitaireException();
    }
    this.cards.last().open();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj instanceof Bafuda) {
      Bafuda kumifuda = (Bafuda) obj;
      return this.cards.equals(kumifuda.cards);
    }
    return false;
  }

}
