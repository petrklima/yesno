package com.klima.yesno

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParser
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tiles = parseTileResources()

        val geometry: Geometry = calculateGeometry(tiles.size)

        val rowIds = createTableRows(geometry)

        spreadTiles(tiles, geometry, rowIds)
    }

    private fun spreadTiles(
        tiles: ArrayList<Tile>,
        geometry: Geometry,
        rowIds: ArrayList<Int>
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var column = 1
        var row = 0
        for (tile: Tile in tiles) {
            if (column > geometry.columns) {
                column = 1
                row += 1
            }
            column += 1

            val fragment = TextInCircle.newInstance(tile.colour, tile.text)
            fragmentTransaction.add(rowIds[row], fragment)
        }
        fragmentTransaction.commit()
    }

    private fun createTableRows(geometry: Geometry): ArrayList<Int> {
        val rowIds = ArrayList<Int>()
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        for (rowNumber in 1..geometry.rows) {
            val tableRow = TableRow(this)
            tableRow.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
            )
            val viewId = View.generateViewId()
            tableRow.id = viewId
            tableLayout.addView(tableRow,
                TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT,
                    1F / geometry.rows
                ))
            rowIds.add(viewId)
        }
        return rowIds
    }

    private fun calculateGeometry(numberOfTiles: Int): Geometry {
        val screenRatio = 16F/9F
        var rowCount = 1F
        var columnCount = numberOfTiles
        var nextRatio = numberOfTiles / (rowCount + 1)
        while (screenRatio <= nextRatio) {
            rowCount += 1
            columnCount = (numberOfTiles / rowCount).roundToInt()
            nextRatio = columnCount / rowCount
        }
        return Geometry(rowCount.toInt(), columnCount)
    }

    private fun parseTileResources(): ArrayList<Tile>
    {
        val tiles = ArrayList<Tile>()
        val parser = resources.getXml(R.xml.tiles)
        parser.next()
        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            val name: String? = parser.name
            if (name == "tile") {
                if (eventType == XmlPullParser.START_TAG) {
                    val tile = Tile(colour = Color.parseColor(parser.getAttributeValue(0)),
                        text = parser.getAttributeValue(2)
                    )
                    tiles.add(tile)
                }
            }
            eventType = parser.next()
        }
        return tiles
    }
}