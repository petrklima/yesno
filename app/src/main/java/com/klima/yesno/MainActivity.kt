package com.klima.yesno

import android.graphics.Color
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParser


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        val tableRow = TableRow(this)
        tableRow.setLayoutParams(
            TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                10.0F
            )
        )
        tableRow.tag = "tableRow0"
        tableLayout.addView(tableRow)


        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val parser = resources.getXml(R.xml.tiles)
        parser.next()
        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            val name: String? = parser.name
            if ("tile" == name) {
                if (eventType == XmlPullParser.START_TAG) {
                    val fragment = TextInCircle.newInstance(Color.parseColor(parser.getAttributeValue(0)), parser.getAttributeValue(2))
                    fragmentTransaction.add(R.id.tableRow0, fragment)
                }
            }
            eventType = parser.next()
        }

        fragmentTransaction.commit()
    }
}