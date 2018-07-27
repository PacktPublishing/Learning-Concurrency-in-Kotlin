package co.starcarr.rssreader.model

data class Feed(val name: String, val url: String)
data class Article(val feed: String, val title: String, val summary: String)