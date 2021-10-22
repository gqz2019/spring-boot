package com.gqz.springbootes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gqz.springbootes.po.Article;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.*;

@SpringBootTest
public class SpringBootEsApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createIndexAndDocument() throws Exception {
        CreateIndexRequest request = new CreateIndexRequest("test");


//        request.mapping("_doc", xContentBuilder);
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }

    @Test
    public void updateIndex() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest();

        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
        xContentBuilder
                .startObject("mappings")
                .startObject()
                .startObject("article")
                .startObject("properties")
                .startObject("id")
                .field("type", "long")
                .field("store", "true")
                .endObject()
                .startObject("title")
                .field("type", "text")
                .field("store", "true")
                .field("analyzer", "ik_smart")
                .field("index", "true")
                .endObject()
                .startObject("content")
                .field("type", "text")
                .field("store", "true")
                .field("analyzer", "ik_smart")
                .field("index", "true")
                .endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject();

        updateRequest.doc(xContentBuilder);
        UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(response.getIndex());
    }

    //判断索引库是否存在
    @Test
    void isExists() throws IOException {
        GetIndexRequest test = new GetIndexRequest("test");
        boolean exists = restHighLevelClient.indices().exists(test, RequestOptions.DEFAULT);
        GetIndexResponse response = restHighLevelClient.indices().get(test, RequestOptions.DEFAULT);
        Map<String, Settings> settings = response.getSettings();
        settings.forEach((k, v) -> {
            System.out.println(v);
        });
        System.out.println(exists);
    }

    //删除索引库
    @Test
    void deleteIndex() throws IOException {
        //发送一个删除索引库的请求，test是索引库的名字
        DeleteIndexRequest request = new DeleteIndexRequest("test");
        //通过核心对象删除索引库
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    @Test
    public void insertData() throws IOException {
//        ThreadLocal threadLocal = new ThreadLocal();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int j = 0; j < 9; j++) {
            service.execute(() -> {
                // ... do something inside runnable task
                System.out.println("111111111111111111111111111111111111111111");

                BulkRequest bulkRequest = new BulkRequest("test");

                for (long i = 0; i < 10000; i++) {
                    Article article = new Article();
                    article.setId(i);
                    article.setTitle("华为苹果小米" + i);
                    article.setContent(i + "随机一段废话的发生, 到底需要如何做到, 不随机一段废话的发生, 又会如何产生. 要想清楚, 随机一段废话, 到底是一种怎么样的存在. 我们都知道, 只要有意义, 那么就必须慎重考虑.随机一段废话因何而发生?随机一段废话, 发生了会如何, 不发生又会如何. 随机一段废话的发生, 到底需要如何做到, 不随机一段废话的发生, 又会如何产生. 生活中, 若随机一段废话出现了, 我们就不得不考虑它出现了的事实. 从这个角度来看, 每个人都不得不面对这些问题. 在面对这种问题时, 而这些并不是完全重要, 更加重要的问题是, 对我个人而言，随机一段废话不仅仅是一个重大的事件，还可能会改变我的人生. 那么, \n" +
                            "随机一段废话, 到底应该如何实现. 从这个角度来看, 一般来讲, 我们都必须务必慎重的考虑考虑. 我们不得不面对一个非常尴尬的事实, 那就是, 达尔文曾经说过, 敢于浪费哪怕一个钟头时间的人，说明他还不懂得珍惜生命的全部价值。这句话语虽然很短, 但令我浮想联翩. 这样看来, 而这些并不是完全重要, 更加重要的问题是, 屠格涅夫曾经提到过, 你想成为幸福的人吗？但愿你首先学会吃得起苦。这似乎解答了我的疑惑. 我认为, 随机一段废话, 到底应该如何实现. 带着这些问题, 我们来审视一下随机一段废话. 我们都知道, 只要有意义, 那么就必须慎重考虑.吉格·金克拉曾经说过, 如果你能做梦，你就能实现它。我希望诸位也能好好地体会这句话. 现在, 解决随机一段废话的问题, 是非常非常重要的. 所以, \n" +
                            "\n" +
                            "我们不得不面对一个非常尴尬的事实, 那就是, 本人也是经过了深思熟虑,在每个日日夜夜思考这个问题. 带着这些问题, 我们来审视一下随机一段废话. 一般来说, 随机一段废话, 到底应该如何实现. 这是不可避免的. 而这些并不是完全重要, 更加重要的问题是, 既然如何, 要想清楚, 随机一段废话, 到底是一种怎么样的存在. 那么, 总结的来说, 总结的来说, \n" +
                            "生活中, 若随机一段废话出现了, 我们就不得不考虑它出现了的事实. 这种事实对本人来说意义重大, 相信对这个世界也是有一定意义的.我认为, 一般来讲, 我们都必须务必慎重的考虑考虑. \n" +
                            "随机一段废话似乎是一种巧合，但如果我们从一个更大的角度看待问题，这似乎是一种不可避免的事实. 这是不可避免的. 卢梭说过一句著名的话, 浪费时间是一桩大罪过。这似乎解答了我的疑惑. ");

                    IndexRequest request = new IndexRequest("test");

                    try {
                        request.source(objectMapper.writeValueAsString(article), XContentType.JSON);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
//                request.timeout(TimeValue.timeValueSeconds(60));
                    request.id(String.valueOf(i));

                    bulkRequest.add(request);

                }

                BulkResponse bulk = null;
                try {
                    bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(bulk.getTook());
//            Arrays.stream(bulk.getItems()).forEach(System.out::println);
                System.out.println(bulk.buildFailureMessage());
                System.out.println("fjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
            });

        }

        System.in.read();
        service.shutdown();


    }

    @Test
    public void insertMulti() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, Integer.MAX_VALUE, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1");
                }
            });
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //但条件查询+分页
    @Test
    void search() throws IOException {
        String keywords = "";
        SearchRequest searchRequest = new SearchRequest(".tasks");

        searchRequest.source(new SearchSourceBuilder()
                .query(QueryBuilders
                        .boolQuery()
                        .should(QueryBuilders
                                .matchQuery("id", 94)))
                .from(0)
                .size(2));
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();

        Arrays.stream(hits).forEach(System.out::println);
    }

    @Test
    public void deleteAll() {
        DeleteRequest deleteRequest = new DeleteRequest();

    }

}
