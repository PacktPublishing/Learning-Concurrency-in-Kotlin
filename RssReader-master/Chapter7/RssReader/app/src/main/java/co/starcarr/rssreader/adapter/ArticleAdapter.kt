package co.starcarr.rssreader.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import co.starcarr.rssreader.R
import co.starcarr.rssreader.model.Article
import kotlinx.coroutines.experimental.launch

interface ArticleLoader {
    suspend fun loadMore()
}

class ArticleAdapter()
    : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val articles: MutableList<Article> = mutableListOf()

    class ViewHolder(val layout: LinearLayout,
                     val feed: TextView,
                     val title: TextView,
                     val summary: TextView
                    ) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.article, parent, false) as LinearLayout

        val feed = layout.findViewById<TextView>(R.id.feed)
        val title = layout.findViewById<TextView>(R.id.title)
        val summary = layout.findViewById<TextView>(R.id.summary)

        return ViewHolder(layout, feed, title, summary)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]

        holder.feed.text = article.feed
        holder.title.text = article.title
        holder.summary.text = article.summary
    }

    fun add(articles: List<Article>) {
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    fun add(article: Article) {
        this.articles.add(article)
        notifyDataSetChanged()
    }

    fun clear() {
        this.articles.clear()
        notifyDataSetChanged()
    }


}