import com.baatsen.ietsnieuws.data.model.NewsJson
import com.baatsen.ietsnieuws.data.model.SourcesJson
import com.google.gson.Gson
import io.reactivex.Single

class MockNewsService() {

    fun getNews() = Single.just(Gson().fromJson(newsJson, NewsJson::class.java))
    fun getSources() = Single.just(Gson().fromJson(sourcesJson, SourcesJson::class.java))


    val newsJson = "{\n" +
            "  \"status\": \"ok\",\n" +
            "  \"totalResults\": 5,\n" +
            "  \"articles\": [\n" +
            "    {\n" +
            "      \"source\": {\n" +
            "        \"id\": \"bbc-news\",\n" +
            "        \"name\": \"BBC News\"\n" +
            "      },\n" +
            "      \"author\": \"BBC News\",\n" +
            "      \"title\": \"Mock news\",\n" +
            "      \"description\": \"Cyclone Owen is expected to intensify into a category four system and may \\\"wreak havoc\\\", officials say.\",\n" +
            "      \"url\": \"http://www.bbc.co.uk/news/world-australia-46562082\",\n" +
            "      \"urlToImage\": \"file:///android_asset/mock.png\",\n" +
            "      \"publishedAt\": \"2018-12-14T06:36:51Z\",\n" +
            "      \"content\": \"Image copyright EPA Image caption Australia's east coast has been pummelled by heavy rain in recent days Australian authorities have issued a warning about a powerful cyclone that is tracking towards the coast of Queensland. Cyclone Owen is moving slowly over… [+1050 chars]\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"source\": {\n" +
            "        \"id\": \"bbc-news\",\n" +
            "        \"name\": \"BBC News\"\n" +
            "      },\n" +
            "      \"author\": \"BBC News\",\n" +
            "      \"title\": \"Girl dies in custody at US-Mexico border\",\n" +
            "      \"description\": \"A seven-year-old migrant from Guatemala with dehydration suffers a cardiac arrest, US media report.\",\n" +
            "      \"url\": \"http://www.bbc.co.uk/news/world-us-canada-46562499\",\n" +
            "      \"urlToImage\": \"file:///android_asset/mock.png\",\n" +
            "      \"publishedAt\": \"2018-12-14T04:57:19Z\",\n" +
            "      \"content\": \"Image copyright Getty Images Image caption Migrants arriving at the US-Mexico border say they are fleeing persecution, poverty and violence A seven-year-old girl from Guatemala who illegally crossed the US-Mexico border with her father died hours after being … [+2015 chars]\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"source\": {\n" +
            "        \"id\": \"bbc-news\",\n" +
            "        \"name\": \"BBC News\"\n" +
            "      },\n" +
            "      \"author\": \"BBC News\",\n" +
            "      \"title\": \"Man jailed over sex assault on US flight\",\n" +
            "      \"description\": \"The Indian man gets nine years in jail for assaulting a woman who was asleep next to him.\",\n" +
            "      \"url\": \"http://www.bbc.co.uk/news/world-asia-india-46563084\",\n" +
            "      \"urlToImage\": \"file:///android_asset/mock.png\",\n" +
            "      \"publishedAt\": \"2018-12-14T04:05:57Z\",\n" +
            "      \"content\": \"Image copyright Wayne County Sheriff's Office Image caption The accused was sitting next to his wife when he allegedly assaulted someone sitting next to him A US court has sentenced an Indian man to nine years in jail for sexually assaulting a woman who sat n… [+1531 chars]\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"source\": {\n" +
            "        \"id\": \"bbc-news\",\n" +
            "        \"name\": \"BBC News\"\n" +
            "      },\n" +
            "      \"author\": \"BBC News\",\n" +
            "      \"title\": \"Flight U-turns over human heart mishap\",\n" +
            "      \"description\": \"A US plane travelling from Seattle to Dallas turns back after failing to unload the donated organ.\",\n" +
            "      \"url\": \"http://www.bbc.co.uk/news/world-us-canada-46562494\",\n" +
            "      \"urlToImage\": \"file:///android_asset/mock.png\",\n" +
            "      \"publishedAt\": \"2018-12-14T01:54:12Z\",\n" +
            "      \"content\": \"Image copyright Getty Images Image caption The plane was reportedly in the air for about three hours A US passenger plane travelling from Seattle to Dallas was forced to turn back hours into its flight because a human heart had been left on board. Southwest A… [+1170 chars]\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"source\": {\n" +
            "        \"id\": \"bbc-news\",\n" +
            "        \"name\": \"BBC News\"\n" +
            "      },\n" +
            "      \"author\": \"BBC News\",\n" +
            "      \"title\": \"'Hell to pay' if climate talks fail\",\n" +
            "      \"description\": \"Weary delegates enter the last official day of UN climate talks with many critical issues undecided.\",\n" +
            "      \"url\": \"http://www.bbc.co.uk/news/science-environment-46554254\",\n" +
            "      \"urlToImage\": \"file:///android_asset/mock.png\",\n" +
            "      \"publishedAt\": \"2018-12-14T00:37:53Z\",\n" +
            "      \"content\": \"Image copyright Getty Images Amid impassioned pleas for progress, negotiators at the UN climate talks in Poland are facing the final day with many issues undecided. Former Maldives president Mohamed Nasheed said there would be \\\"hell to pay\\\" if countries faile… [+6109 chars]\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    val sourcesJson = "{\n" +
            "  \"status\": \"ok\",\n" +
            "  \"sources\": [\n" +
            "    {\n" +
            "      \"id\": \"abc-news\",\n" +
            "      \"name\": \"Mock News\",\n" +
            "      \"description\": \"No matter what you choose here, you will always get mocked news anyway.\",\n" +
            "      \"url\": \"https://abcnews.go.com\",\n" +
            "      \"category\": \"general\",\n" +
            "      \"language\": \"en\",\n" +
            "      \"country\": \"us\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"abc-news-au\",\n" +
            "      \"name\": \"ABC News (AU)\",\n" +
            "      \"description\": \"No matter what you choose here, you will always get mocked news anyway.\",\n" +
            "      \"url\": \"http://www.abc.net.au/news\",\n" +
            "      \"category\": \"general\",\n" +
            "      \"language\": \"en\",\n" +
            "      \"country\": \"au\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"aftenposten\",\n" +
            "      \"name\": \"Aftenposten\",\n" +
            "      \"description\": \"No matter what you choose here, you will always get mocked news anyway.\",\n" +
            "      \"url\": \"https://www.aftenposten.no\",\n" +
            "      \"category\": \"general\",\n" +
            "      \"language\": \"no\",\n" +
            "      \"country\": \"no\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"al-jazeera-english\",\n" +
            "      \"name\": \"Al Jazeera English\",\n" +
            "      \"description\": \"No matter what you choose here, you will always get mocked news anyway.\",\n" +
            "      \"url\": \"http://www.aljazeera.com\",\n" +
            "      \"category\": \"general\",\n" +
            "      \"language\": \"en\",\n" +
            "      \"country\": \"us\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"ansa\",\n" +
            "      \"name\": \"ANSA.it\",\n" +
            "      \"description\": \"No matter what you choose here, you will always get mocked news anyway.\",\n" +
            "      \"url\": \"http://www.ansa.it\",\n" +
            "      \"category\": \"general\",\n" +
            "      \"language\": \"it\",\n" +
            "      \"country\": \"it\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"argaam\",\n" +
            "      \"name\": \"Argaam\",\n" +
            "      \"description\": \"ارقام موقع متخصص في متابعة سوق الأسهم السعودي تداول - تاسي - مع تغطيه معمقة لشركات واسعار ومنتجات البتروكيماويات , تقارير مالية الاكتتابات الجديده \",\n" +
            "      \"url\": \"http://www.argaam.com\",\n" +
            "      \"category\": \"business\",\n" +
            "      \"language\": \"ar\",\n" +
            "      \"country\": \"sa\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"ars-technica\",\n" +
            "      \"name\": \"Ars Technica\",\n" +
            "      \"description\": \"No matter what you choose here, you will always get mocked news anyway.\",\n" +
            "      \"url\": \"http://arstechnica.com\",\n" +
            "      \"category\": \"technology\",\n" +
            "      \"language\": \"en\",\n" +
            "      \"country\": \"us\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"ary-news\",\n" +
            "      \"name\": \"Ary News\",\n" +
            "      \"description\": \"No matter what you choose here, you will always get mocked news anyway.\",\n" +
            "      \"url\": \"https://arynews.tv/ud/\",\n" +
            "      \"category\": \"general\",\n" +
            "      \"language\": \"ud\",\n" +
            "      \"country\": \"pk\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"associated-press\",\n" +
            "      \"name\": \"Associated Press\",\n" +
            "      \"description\": \"The AP delivers in-depth coverage on the international, politics, lifestyle, business, and entertainment news.\",\n" +
            "      \"url\": \"https://apnews.com/\",\n" +
            "      \"category\": \"general\",\n" +
            "      \"language\": \"en\",\n" +
            "      \"country\": \"us\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"australian-financial-review\",\n" +
            "      \"name\": \"Australian Financial Review\",\n" +
            "      \"description\": \"The Australian Financial Review reports the latest news from business, finance, investment and politics, updated in real time. It has a reputation for independent, award-winning journalism and is essential reading for the business and investor community.\",\n" +
            "      \"url\": \"http://www.afr.com\",\n" +
            "      \"category\": \"business\",\n" +
            "      \"language\": \"en\",\n" +
            "      \"country\": \"au\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"axios\",\n" +
            "      \"name\": \"Axios\",\n" +
            "      \"description\": \"Axios are a new media company delivering vital, trustworthy news and analysis in the most efficient, illuminating and shareable ways possible.\",\n" +
            "      \"url\": \"https://www.axios.com\",\n" +
            "      \"category\": \"general\",\n" +
            "      \"language\": \"en\",\n" +
            "      \"country\": \"us\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"bbc-news\",\n" +
            "      \"name\": \"BBC News\",\n" +
            "      \"description\": \"Use BBC News for up-to-the-minute news, breaking news, video, audio and feature stories. BBC News provides trusted World and UK news as well as local and regional perspectives. Also entertainment, business, science, technology and health news.\",\n" +
            "      \"url\": \"http://www.bbc.co.uk/news\",\n" +
            "      \"category\": \"general\",\n" +
            "      \"language\": \"en\",\n" +
            "      \"country\": \"gb\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"

}