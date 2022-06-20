package d5.solitaire.domain.board;

import com.google.common.annotations.VisibleForTesting;

import d5.solitaire.domain.card.Card;
import d5.solitaire.domain.card.Mark;
import d5.solitaire.domain.card.Number;
import d5.solitaire.domain.card.Side;
import d5.solitaire.domain.exception.SolitaireException;
import io.vavr.collection.List;

public class Kumifuda {
  private Mark mark;
  private List<Card> cards;

  private Kumifuda(Mark mark) {
    this.mark = mark;
    this.cards = List.empty();
  }

  public static Kumifuda empty(Mark mark) {
    return new Kumifuda(mark);
  }

  // FIXME: 本当はいらないメソッド。テストのために作った。testクラス内でwrapperを作ることも考えたが、面倒になったので一旦ここに作る
  @VisibleForTesting
  protected static Kumifuda of(Mark mark, Card... cards) {
    var kumifuda = Kumifuda.empty(mark);
    for (Card card : cards) {
      kumifuda.append(card);
    }
    return kumifuda;
  }

  private boolean isAppendable(Card card) {
    if (!this.mark.equals(card.getMark())) {
      return false;
    }
    if (card.getSide() == Side.BACK) {
      return false;
    }

    if (this.cards.isEmpty()) {
      if (card.getNumber() == Number.ACE) {
        return true;
      } else {
        return false;
      }
    }

    if (card.getNumber().isOneLargerThan(this.cards.last().getNumber())) {
      return true;
    }
    return false;
  }

  // XXX: voidでも良いか？
  public Kumifuda append(Card card) {
    if (!this.isAppendable(card)) {
      // TODO: 本当はメッセージ出力したい
      throw new SolitaireException();
    }
    this.cards = this.cards.append(card);
    return this;
  }

  public Card selectableCard() {
    if (this.cards.isEmpty()) {
      return null;
    }
    return this.cards.last();
  }

  // FIXME: なんか一貫性がないmoveとかselectとか
  public Card removeLast() {
    var removedCard = selectableCard();
    if (removedCard == null) {
      // TODO: 本当はメッセージ出力したい
      throw new SolitaireException();
    }
    this.cards = this.cards.removeLast(card -> true);
    return removedCard;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj instanceof Kumifuda) {
      Kumifuda kumifuda = (Kumifuda) obj;
      return this.mark.equals(kumifuda.mark)
          && this.cards.equals(kumifuda.cards);
    }
    return false;
  }

}
