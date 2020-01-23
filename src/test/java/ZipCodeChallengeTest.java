import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ZipCodeChallengeTest {

    private static final List<int[]> TEST_INPUT = new ArrayList<int[]>();

    private static final List<int[]> TEST_INPUT_NULL = null;

    private static final List<int[]> TEST_INPUT_SIZE_ONE = new ArrayList<int[]>();

    private ZipCodeChallenge zipCodeChallenge;

    @Before
    public void setup() {
        TEST_INPUT.add(new int[]{94133,94133});
        TEST_INPUT.add(new int[]{94200,94299});
        TEST_INPUT.add(new int[]{94226,94699});

        zipCodeChallenge = new ZipCodeChallenge();
    }

    @Test
    public void getMinimumRangesTest_Success() {

        // Arrange
        List<int[]> EXPECTED_OUTPUT = new ArrayList<int[]>();
        EXPECTED_OUTPUT.add(new int[]{94133, 94133});
        EXPECTED_OUTPUT.add(new int[]{94200, 94699});

        // Act
        List<int[]> result = zipCodeChallenge.getMinimumRanges(TEST_INPUT);

        // Assert
        Assert.assertEquals(result.size(), EXPECTED_OUTPUT.size());

        for (int i = 0; i < result.size(); i++) {
            Assert.assertEquals(EXPECTED_OUTPUT.get(i)[0], result.get(i)[0]);
            Assert.assertEquals(EXPECTED_OUTPUT.get(i)[1], result.get(i)[1]);
        }
    }

    @Test
    public void getMinimumRangesTest_sizeOne() {

        // Arrange
        TEST_INPUT_SIZE_ONE.add(new int[]{94133, 94299});

        // Act
        List<int[]> result = zipCodeChallenge.getMinimumRanges(TEST_INPUT_SIZE_ONE);

        // Assert
        System.out.println(TEST_INPUT_SIZE_ONE.size());
        Assert.assertEquals(TEST_INPUT_SIZE_ONE.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            Assert.assertEquals(result.get(i)[0], TEST_INPUT_SIZE_ONE.get(i)[0]);
            Assert.assertEquals(result.get(i)[1], TEST_INPUT_SIZE_ONE.get(i)[1]);
        }
    }

    @Test
    public void getMinimumRangesTest_nullInput() {

        // Act
        List<int[]> result = zipCodeChallenge.getMinimumRanges(TEST_INPUT_NULL);

        // Assert
        Assert.assertEquals(result, null);
    }

}
