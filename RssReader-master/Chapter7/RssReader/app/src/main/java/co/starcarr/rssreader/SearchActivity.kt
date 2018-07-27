package co.starcarr.rssreader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import co.starcarr.rssreader.adapter.ArticleAdapter
import co.starcarr.rssreader.search.ResultsCounter
import co.starcarr.rssreader.search.Searcher
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

class SearchActivity : AppCompatActivity() {
    private val searcher = Searcher()

    private lateinit var articles: RecyclerView
    private lateinit var viewAdapter: ArticleAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ArticleAdapter()
        articles = findViewById<RecyclerView>(R.id.articles).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        findViewById<Button>(R.id.searchButton).setOnClickListener {
            viewAdapter.clear()
            launch {
                ResultsCounter.reset()
                search()
            }
        }

        launch {
            updateCounter()
        }

    }

    private suspend fun updateCounter() {
        val notifications = ResultsCounter.getNotificationChannel()
        val results = findViewById<TextView>(R.id.results)

        while (!notifications.isClosedForReceive) {
            val newAmount = notifications.receive()

            withContext(UI) {
                results.text = "Results: $newAmount"
            }
        }
    }

    private suspend fun search() {
        val query = findViewById<EditText>(R.id.searchText)
                .text.toString()

        val channel = searcher.search(query)

        while (!channel.isClosedForReceive) {
            val article = channel.receive()

            launch(UI) {
                viewAdapter.add(article)
            }
        }
    }
}