package co.starcarr.rssreader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

    private val feeds = listOf(
            "https://www.npr.org/rss/rss.php?id=1001",
            "http://rss.cnn.com/rss/cnn_topstories.rss",
            "http://feeds.foxnews.com/foxnews/politics?format=xml",
            "htt:myNewsFeed"
    )
    private val dispatcher = newFixedThreadPoolContext(2, "IO")
    private val factory = DocumentBuilderFactory.newInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        asyncLoadNews()
    }

    private fun asyncLoadNews() = launch {
        val requests = mutableListOf<Deferred<List<String>>>()

        feeds.mapTo(requests) {
            asyncFetchHeadlines(it, dispatcher)
        }

        requests.forEach {
            it.join()
        }

        // If the code below crashes, please change the filter
        // to !it.isCompletedExceptionally until this issue is
        // closed: https://github.com/Kotlin/kotlinx.coroutines/issues/220
        val headlines = requests
                .filter { !it.isCancelled }
                .flatMap { it.getCompleted() }

        // If the counter doesn't work, please change the filter
        // to it.isCompletedExceptionally until this issues is
        // closed: https://github.com/Kotlin/kotlinx.coroutines/issues/220
        val failed = requests
                .filter { it.isCancelled }
                .size

        val newsCount = findViewById<TextView>(R.id.newsCount)
        val warnings = findViewById<TextView>(R.id.warnings)
        val obtained = requests.size - failed

        launch(UI) {
            newsCount.text = "Found ${headlines.size} News " +
                    "in $obtained feeds"

            if (failed > 0) {
                warnings.text = "Failed to fetch $failed feeds"
            }
        }
    }


    private fun asyncFetchHeadlines(feed: String,
            dispatcher: CoroutineDispatcher) = async(dispatcher) {
        val builder = factory.newDocumentBuilder()
        val xml = builder.parse(feed)
        val news = xml.getElementsByTagName("channel").item(0)

        (0 until news.childNodes.length)
                .map { news.childNodes.item(it) }
                .filter { Node.ELEMENT_NODE == it.nodeType }
                .map { it as Element }
                .filter { "item" == it.tagName }
                .map {
                    it.getElementsByTagName("title").item(0).textContent
                }
    }

}
