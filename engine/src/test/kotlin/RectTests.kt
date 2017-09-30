import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.Math.Rect
import org.junit.Assert
import org.junit.Test

class RectTestBorders {

    @Test
    fun rectTestIsOutside(){

        val rect = Rect(10f, 10f, 5f, 5f)
        val touch1 = Vector2(10f,10f)
        val touch2 = Vector2(1f,10f)
        val touch3 = Vector2(10f,1f)
        val touch4 = Vector2(25f,10f)
        val touch5 = Vector2(10f,25f)

        Assert.assertEquals(false, touch1 !in rect)
        Assert.assertEquals(true, touch2 !in rect)
        Assert.assertEquals(true, touch3 !in rect)
        Assert.assertEquals(true, touch4 !in rect)
        Assert.assertEquals(true, touch5 !in rect)
    }

    @Test
    fun rectTestIsInside(){

        val rect = Rect(10f, 10f, 5f, 5f)
        val touch1 = Vector2(10f,10f)
        val touch2 = Vector2(7f,12f)
        val touch3 = Vector2(10f,1f)
        val touch4 = Vector2(25f,10f)
        val touch5 = Vector2(10f,25f)
        val touch6 = Vector2(1f,10f)

        Assert.assertEquals(true, touch1 in rect)
        Assert.assertEquals(true, touch2 in rect)
        Assert.assertEquals(false, touch3 in rect)
        Assert.assertEquals(false, touch4 in rect)
        Assert.assertEquals(false, touch5 in rect)
        Assert.assertEquals(false, touch6 in rect)
    }

}
