package d5.solitaire.domain.card;

import static org.junit.Assert.assertEquals;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ColorTest {
  @DataPoints
  public static Fixture[] fixtures = {
      new Fixture(Color.RED, Color.BLACK, true),
      new Fixture(Color.RED, Color.RED, false),
      new Fixture(Color.BLACK, Color.BLACK, false),
      new Fixture(Color.BLACK, Color.RED, true),

  };

  @Theory
  public void isOneSmallerThan(Fixture fixture) {
    assertEquals(fixture.message, fixture.x.isReverseColor(fixture.y), fixture.isReverseColorYExpected);
  };

  static class Fixture {
    String message;
    Color x;
    Color y;
    boolean isReverseColorYExpected;

    Fixture(Color x, Color y, boolean isReverseColorYExpected) {
      message = "x: " + x + ", y: " + y;
      this.x = x;
      this.y = y;
      this.isReverseColorYExpected = isReverseColorYExpected;
    }
  }

}
