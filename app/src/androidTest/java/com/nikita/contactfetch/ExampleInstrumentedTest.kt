package com.nikita.contactfetch


import androidx.activity.viewModels
import com.nikita.contactfetch.*
import com.nikita.contactfetch.ContentViewModel.ContactsViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nikita.contactfetch.AllActivity.ContactListActivity
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

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
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.nikita.contactfetch", appContext.packageName)
    }




}
