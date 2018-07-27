package co.starcarr.rssreader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import co.starcarr.rssreader.adapter.ArticleAdapter
import co.starcarr.rssreader.adapter.ArticleLoader
import co.starcarr.rssreader.producer.ArticleProducer
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

class MainActivity : AppCompatActivity(), ArticleLoader {

    private lateinit var articles: RecyclerView
    private lateinit var viewAdapter: ArticleAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ArticleAdapter(this)
        articles = findViewById<RecyclerView>(R.id.articles).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        launch {
            loadMore()
        }

    }

    override suspend fun loadMore() {
        val producer = ArticleProducer.producer

        if (!producer.isClosedForReceive) {
            val articles = producer.receive()

            launch(UI) {
                findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                viewAdapter.add(articles)
                Log.d("Main", "Currently has ${viewAdapter.itemCount} articles")
            }
        }
    }

}
