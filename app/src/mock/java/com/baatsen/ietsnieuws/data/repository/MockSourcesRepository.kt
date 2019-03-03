import com.baatsen.ietsnieuws.data.model.SourceMapper
import com.baatsen.ietsnieuws.data.service.MockNewsService
import com.baatsen.ietsnieuws.domain.SourcesRepository
import com.baatsen.ietsnieuws.domain.model.Source
import io.reactivex.Single

class MockSourcesRepository(private val newsService: MockNewsService, private val sourceMapper: SourceMapper) :
    SourcesRepository {

    override fun getSources(): Single<List<Source>> {
        return newsService.getSources()
            .map { sourceMapper.transform(it) }
    }
}