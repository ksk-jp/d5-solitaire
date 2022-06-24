package d5.solitaire.domain.card;

import static org.junit.Assert.assertEquals;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class NumberTest {

  @DataPoints
  public static SmallerFixture[] smallerFixtures = {
      new SmallerFixture(Number.ACE, Number.ACE, false),
      new SmallerFixture(Number.ACE, Number.TWO, true),
      new SmallerFixture(Number.ACE, Number.THREE, false),
      new SmallerFixture(Number.TEN, Number.JACK, true),
      new SmallerFixture(Number.JACK, Number.QUEEN, true),
      new SmallerFixture(Number.QUEEN, Number.KING, true),
      new SmallerFixture(Number.KING, Number.ACE, false),
  };

  @Theory
  public void isOneSmallerThan(SmallerFixture fixture) {
    assertEquals(fixture.message, fixture.xisOneSmallerThanYExpected, fixture.x.isOneSmallerThan(fixture.y));
  };

  static class SmallerFixture {
    String message;
    Number x;
    Number y;
    boolean xisOneSmallerThanYExpected;

    SmallerFixture(Number x, Number y, boolean xisOneSmallerThanYExpected) {
      message = "x: " + x + ", y: " + y;
      this.x = x;
      this.y = y;
      this.xisOneSmallerThanYExpected = xisOneSmallerThanYExpected;
    }
  }

  /////////////
  @DataPoints
  public static LargerFixture[] largerFixtures = {
      new LargerFixture(Number.TWO, Number.ACE, true),
      new LargerFixture(Number.TWO, Number.TWO, false),
      new LargerFixture(Number.TWO, Number.THREE, false),
      new LargerFixture(Number.JACK, Number.TEN, true),
      new LargerFixture(Number.QUEEN, Number.JACK, true),
      new LargerFixture(Number.KING, Number.QUEEN, true),
      new LargerFixture(Number.ACE, Number.KING, false),
  };

  @Theory
  public void isOneLargerThan(LargerFixture fixture) {
    assertEquals(fixture.message, fixture.xisOneLargerThanYExpected, fixture.x.isOneLargerThan(fixture.y));
  };

  static class LargerFixture {
    String message;
    Number x;
    Number y;
    boolean xisOneLargerThanYExpected;

    LargerFixture(Number x, Number y, boolean xisOneLargerThanYExpected) {
      message = "x: " + x + ", y: " + y;
      this.x = x;
      this.y = y;
      this.xisOneLargerThanYExpected = xisOneLargerThanYExpected;
    }
  }
}
