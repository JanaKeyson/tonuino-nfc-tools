package de.mw136.tonuino

import android.os.Parcel
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import de.mw136.tonuino.nfc.TagData

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("de.mw136.tonuino", appContext.packageName)
    }


    @Test
    fun parceling() {
        val input = TagData.createDefault()
        val parcel = Parcel.obtain()
        input.writeToParcel(parcel, input.describeContents())
        parcel.setDataPosition(0)
        val actual1 = TagData.CREATOR.createFromParcel(parcel)

        assertNotEquals(input, actual1)
        assertArrayEquals(input.bytes.toByteArray(), actual1.bytes.toByteArray())

        parcel.setDataPosition(0)
        val actual2 = TagData(parcel)

        assertNotEquals(input, actual2)
        assertArrayEquals(input.bytes.toByteArray(), actual2.bytes.toByteArray())
    }
}
