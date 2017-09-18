import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect
import org.junit.Assert
import org.junit.Test

class RectTestBorders() {

    @Test
    fun rectTestIsOutside(){

        val rect = Rect(10f, 10f, 5f, 5f)
        val touch1 = Vector2(10f,10f)
        val touch2 = Vector2(1f,10f)
        val touch3 = Vector2(10f,1f)
        val touch4 = Vector2(25f,10f)
        val touch5 = Vector2(10f,25f)

        Assert.assertEquals(false, !(rect intersect touch1))
        Assert.assertEquals(true, !(rect intersect touch2))
        Assert.assertEquals(true, !(rect intersect touch3))
        Assert.assertEquals(true, !(rect intersect touch4))
        Assert.assertEquals(true, !(rect intersect touch5))
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

        Assert.assertEquals(true, rect intersect touch1)
        Assert.assertEquals(true, rect intersect touch2)
        Assert.assertEquals(false, rect intersect touch3)
        Assert.assertEquals(false, rect intersect touch4)
        Assert.assertEquals(false, rect intersect touch5)
        Assert.assertEquals(false, rect intersect touch6)
    }

}
