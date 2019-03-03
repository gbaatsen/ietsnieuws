import com.baatsen.ietsnieuws.data.model.NewsMapper
import com.baatsen.ietsnieuws.data.service.MockNewsService
import com.baatsen.ietsnieuws.domain.ArticleRepository
import com.baatsen.ietsnieuws.domain.model.Article
import io.reactivex.Single

class MockArticleRepository(
    private val newsService: MockNewsService,
    private val newsMapper: NewsMapper
) : ArticleRepository {

    override fun getNews(): Single<List<Article>> {
        return newsService.getNews()
            .map { newsMapper.transform(it) }
    }
}