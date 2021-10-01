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
        tableRow.layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.MATCH_PARENT,
            10.0F
        )
        tableRow.tag = "tableRow0"
        tableLayout.addView(tableRow)

        val tiles = ArrayList<Tile>()
        val parser = resources.getXml(R.xml.tiles)
        parser.next()
        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            val name: String? = parser.name
            if (name == "tile") {
                if (eventType == XmlPullParser.START_TAG) {
                    val tile = Tile(tag = parser.getAttributeValue(1),
                        colour = Color.parseColor(parser.getAttributeValue(0)),
                        text = parser.getAttributeValue(2)
                    )
                    tiles.add(tile)
                }
            }
            eventType = parser.next()
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        for (tile: Tile in tiles) {
            val fragment = TextInCircle.newInstance(tile.colour, tile.text)
            fragmentTransaction.add(R.id.tableRow0, fragment, tile.tag)
        }
        fragmentTransaction.commit()
    }
}