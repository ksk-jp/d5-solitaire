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

@RunWith(Theories.class)
public class KumifudaTest {
  //////////////////////////////////////////////////////////////////////////////
  @DataPoints
  public static AppendFixture[] appendFixtures = {
      new AppendFixture(
          "(正常系)空のKumifudaにACEを追加する",
          Kumifuda.empty(Mark.SPADE), //
          Card.of(Mark.SPADE, Number.ACE, Side.FRONT), //
          false, //
          Kumifuda.of(Mark.SPADE, Card.of(Mark.SPADE, Number.ACE, Side.FRONT))),
      new AppendFixture(
          "(正常系)ACEにTWOを追加する",
          Kumifuda.of(Mark.SPADE, Card.of(Mark.SPADE, Number.ACE, Side.FRONT)),
          Card.of(Mark.SPADE, Number.TWO, Side.FRONT), //
          false, //
          Kumifuda.of(Mark.SPADE, Card.of(Mark.SPADE, Number.ACE, Side.FRONT),
              Card.of(Mark.SPADE, Number.TWO, Side.FRONT))),
      new AppendFixture(
          "(異常系)空のKumifudaにTWOを追加する",
          Kumifuda.empty(Mark.SPADE), //
          Card.of(Mark.SPADE, Number.TWO, Side.FRONT), //
          true, //
          null),
      new AppendFixture(
          "(異常系)ACEにACEを追加する",
          Kumifuda.of(Mark.SPADE, Card.of(Mark.SPADE, Number.ACE, Side.FRONT)),
          Card.of(Mark.SPADE, Number.ACE, Side.FRONT), //
          true, //
          null),
      new AppendFixture(
          "(異常系)スペードのKumifudaにクラブのACEを追加する",
          Kumifuda.empty(Mark.SPADE), //
          Card.of(Mark.CLUB, Number.ACE, Side.FRONT), //
          true, //
          null),

  };

  @Theory
  public void append(AppendFixture fixture) {
    if (fixture.exptectedExceptionOccur) {
      assertThrows(SolitaireException.class, () -> fixture.beforeAppendKumifuda.append(fixture.card));
    } else {
      assertEquals(fixture.afterAppendKumifuda,
          fixture.beforeAppendKumifuda.append(fixture.card));
    }
  };

  static class AppendFixture {
    String message;
    Kumifuda beforeAppendKumifuda;
    Card card;
    boolean exptectedExceptionOccur;
    Kumifuda afterAppendKumifuda;

    public AppendFixture(String message, Kumifuda before, Card card, boolean exptectedExceptionOccur,
        Kumifuda after) {
      this.message = message;
      this.beforeAppendKumifuda = before;
      this.card = card;
      this.exptectedExceptionOccur = exptectedExceptionOccur;
      this.afterAppendKumifuda = after;
    }
  }

  //////////////////////////////////////////////////////////////////////////////
  @DataPoints
  public static SelectableCardFixture[] SelectableCardFixtures = {
      new SelectableCardFixture("空の組札", Kumifuda.empty(Mark.SPADE), null),
      new SelectableCardFixture("エースのみの組札", Kumifuda.of(Mark.SPADE, Card.of(Mark.SPADE, Number.ACE, Side.FRONT)),
          Card.of(Mark.SPADE, Number.ACE, Side.FRONT)),
      new SelectableCardFixture("エースと２の組札",
          Kumifuda.of(Mark.SPADE, Card.of(Mark.SPADE, Number.ACE, Side.FRONT),
              Card.of(Mark.SPADE, Number.TWO, Side.FRONT)),
          Card.of(Mark.SPADE, Number.TWO, Side.FRONT)),
  };

  @Theory
  public void selectableCard(SelectableCardFixture fixture) {
    assertEquals(fixture.expectedCard, fixture.kumifuda.selectableCard());
  };

  static class SelectableCardFixture {
    String message;
    Kumifuda kumifuda;
    Card expectedCard;

    public SelectableCardFixture(String message, Kumifuda kumifuda, Card expectedCard) {
      this.message = message;
      this.kumifuda = kumifuda;
      this.expectedCard = expectedCard;
    }
  }

  //////////////////////////////////////////////////////////////////////////////
  @DataPoints
  public static RemoveLastFixture[] RemoveLastFixtures = {
      new RemoveLastFixture("空の組札", Kumifuda.empty(Mark.SPADE), true, null, null),
      new RemoveLastFixture("ACEのみの組札", //
          Kumifuda.of(Mark.SPADE, Card.of(Mark.SPADE, Number.ACE, Side.FRONT)), //
          false,
          Card.of(Mark.SPADE, Number.ACE, Side.FRONT), //
          Kumifuda.empty(Mark.SPADE)),
      new RemoveLastFixture("ACEとTWOの組札", //
          Kumifuda.of(Mark.SPADE, Card.of(Mark.SPADE, Number.ACE, Side.FRONT),
              Card.of(Mark.SPADE, Number.TWO, Side.FRONT)), //
          false,
          Card.of(Mark.SPADE, Number.TWO, Side.FRONT), //
          Kumifuda.of(Mark.SPADE, Card.of(Mark.SPADE, Number.ACE, Side.FRONT))),
  };

  @Theory
  public void removeLast(RemoveLastFixture fixture) {
    if (fixture.exptectedExceptionOccur) {
      assertThrows(SolitaireException.class, () -> fixture.before.removeLast());
    } else {
      assertEquals(fixture.expectedCard, fixture.before.removeLast());
      // removeLast実行後、expectedAfterとbeforeが一致していることを確認
      assertEquals(fixture.expectedAfter, fixture.before);
    }
  };

  static class RemoveLastFixture {
    String message;
    Kumifuda before;
    boolean exptectedExceptionOccur;
    Card expectedCard;
    Kumifuda expectedAfter;

    public RemoveLastFixture(String message, Kumifuda before, boolean exptectedExceptionOccur, Card expectedCard,
        Kumifuda expectedAfter) {
      this.message = message;
      this.before = before;
      this.exptectedExceptionOccur = exptectedExceptionOccur;
      this.expectedCard = expectedCard;
      this.expectedAfter = expectedAfter;
    }

  }
}
