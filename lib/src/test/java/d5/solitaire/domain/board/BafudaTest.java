package d5.solitaire.domain.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import d5.solitaire.domain.card.Card;
import d5.solitaire.domain.card.Mark;
import d5.solitaire.domain.card.Number;
import d5.solitaire.domain.card.Side;
import d5.solitaire.domain.exception.SolitaireException;
import io.vavr.collection.List;

@RunWith(Theories.class)
public class BafudaTest {
  //////////////////////////////////////////////////////////////////////////////
  @DataPoints
  public static AppendFixture[] appendFixtures = {
      new AppendFixture(
          "(正常系)空のBafudaにKINGを追加する",
          Bafuda.of(List.empty()), //
          List.of(Card.of(Mark.SPADE, Number.KING, Side.FRONT)), //
          false, //
          Bafuda.of(List.of(Card.of(Mark.SPADE, Number.KING, Side.FRONT)))),
      new AppendFixture(
          "(異常系)空のBafudaにACEを追加する",
          Bafuda.of(List.empty()), //
          List.of(Card.of(Mark.SPADE, Number.ACE, Side.FRONT)), //
          true, //
          null),
      new AppendFixture(
          "(正常系)スペードのKINGのBafudaにハートのQUEENを追加する",
          Bafuda.of(List.of(Card.of(Mark.SPADE, Number.KING, Side.FRONT))), //
          List.of(Card.of(Mark.HEART, Number.QUEEN, Side.FRONT)), //
          false, //
          Bafuda.of(
              List.of(Card.of(Mark.SPADE, Number.KING, Side.FRONT), Card.of(Mark.HEART, Number.QUEEN, Side.FRONT)))), //
      new AppendFixture(
          "(異常系)スペードのKINGのBafudaにクラブのQUEENを追加する",
          Bafuda.of(List.of(Card.of(Mark.SPADE, Number.KING, Side.FRONT))), //
          List.of(Card.of(Mark.CLUB, Number.QUEEN, Side.FRONT)), //
          true, //
          null), //
      new AppendFixture(
          "(正常系)スペードのKINGのBafudaにハートのQUEENとスペードのJACKを追加する",
          Bafuda.of(List.of(Card.of(Mark.SPADE, Number.KING, Side.FRONT))), //
          List.of(Card.of(Mark.HEART, Number.QUEEN, Side.FRONT), Card.of(Mark.SPADE, Number.JACK, Side.FRONT)), //
          false, //
          Bafuda.of(
              List.of(Card.of(Mark.SPADE, Number.KING, Side.FRONT), Card.of(Mark.HEART, Number.QUEEN, Side.FRONT),
                  Card.of(Mark.SPADE, Number.JACK, Side.FRONT)))), //
  };

  @Theory
  public void append(AppendFixture fixture) {
    if (fixture.exptectedExceptionOccur) {
      assertThrows(SolitaireException.class, () -> fixture.beforeAppendBafuda.append(fixture.cards));
    } else {
      fixture.beforeAppendBafuda.append(fixture.cards);
      var actual = fixture.beforeAppendBafuda;
      assertEquals(fixture.afterAppendBafuda, actual);
    }
  };

  static class AppendFixture {
    String message;
    Bafuda beforeAppendBafuda;
    List<Card> cards;
    boolean exptectedExceptionOccur;
    Bafuda afterAppendBafuda;

    public AppendFixture(String message, Bafuda before, List<Card> cards, boolean exptectedExceptionOccur,
        Bafuda after) {
      this.message = message;
      this.beforeAppendBafuda = before;
      this.cards = cards;
      this.exptectedExceptionOccur = exptectedExceptionOccur;
      this.afterAppendBafuda = after;
    }
  }

  ////////////////////////////////////////////////////////////////////////////
  @DataPoints
  public static MovableCardsFixture[] MovableCardsFixtures = {
      new MovableCardsFixture("空の場札", Bafuda.of(List.empty()), List.empty()),
      new MovableCardsFixture("表の場札のみが返ること", Bafuda.of(
          List.of(Card.of(Mark.SPADE, Number.ACE, Side.BACK), Card.of(Mark.HEART, Number.QUEEN, Side.FRONT),
              Card.of(Mark.SPADE, Number.JACK, Side.FRONT))),
          List.of(Card.of(Mark.HEART, Number.QUEEN, Side.FRONT), Card.of(Mark.SPADE, Number.JACK, Side.FRONT))),
  };

  @Theory
  public void selectableCard(MovableCardsFixture fixture) {
    assertEquals(fixture.expectedCards, fixture.Bafuda.movableCards());
  };

  static class MovableCardsFixture {
    String message;
    Bafuda Bafuda;
    List<Card> expectedCards;

    public MovableCardsFixture(String message, Bafuda Bafuda, List<Card> expectedCards) {
      this.message = message;
      this.Bafuda = Bafuda;
      this.expectedCards = expectedCards;
    }
  }

  // //////////////////////////////////////////////////////////////////////////////
  @DataPoints
  public static RemoveBehindFromFixture[] RemoveBehindFromFixtures = {
      new RemoveBehindFromFixture("(異常系)空の場札", //
          Bafuda.of(List.empty()), //
          Card.of(Mark.SPADE, Number.ACE, Side.FRONT), //
          true, //
          null),
      new RemoveBehindFromFixture("(正常系)♠1, ♡Q, ♠J で♡Qを選択", //
          Bafuda.of(
              List.of(Card.of(Mark.SPADE, Number.ACE, Side.BACK), Card.of(Mark.HEART, Number.QUEEN, Side.FRONT),
                  Card.of(Mark.SPADE, Number.JACK, Side.FRONT))), //
          Card.of(Mark.HEART, Number.QUEEN, Side.FRONT), //
          false, //
          Bafuda.of(List.of(Card.of(Mark.SPADE, Number.ACE, Side.BACK)))),
  };

  @Theory
  public void removeLast(RemoveBehindFromFixture fixture) {
    if (fixture.exptectedExceptionOccur) {
      assertThrows(SolitaireException.class, () -> fixture.before.removeBehindFrom(fixture.selectCard));
    } else {
      fixture.before.removeBehindFrom(fixture.selectCard);
      var actual = fixture.before;
      assertEquals(fixture.expectedAfter, actual);
    }
  };

  static class RemoveBehindFromFixture {
    String message;
    Bafuda before;
    Card selectCard;
    boolean exptectedExceptionOccur;

    Bafuda expectedAfter;

    public RemoveBehindFromFixture(String message, Bafuda before, Card selectCard,
        boolean exptectedExceptionOccur, Bafuda expectedAfter) {
      this.message = message;
      this.before = before;
      this.selectCard = selectCard;
      this.exptectedExceptionOccur = exptectedExceptionOccur;
      this.expectedAfter = expectedAfter;
    }
  }
}
