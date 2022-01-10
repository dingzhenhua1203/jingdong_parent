package com.jingdong.util;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;

import java.util.HashMap;
import java.util.Map;

public class ESHighLevelRestUtil {
    static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("172.19.12.249", 9200, "http")));

    /**
     * 验证索引是否存在
     *
     * @param index
     *            索引名称
     * @return
     * @throws Exception
     */
    public boolean indexExists(String index) throws Exception {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(index);
        request.local(false);
        request.humanReadable(true);

        boolean exists = client.indices().exists(request);
        return exists;
    }

    /**
     *
     * @param index
     * @param indexType
     * @param properties
     *            结构: {name:{type:text}} {age:{type:integer}}
     * @return
     * @throws Exception
     */
    public boolean indexCreate(String index, String indexType,
                               Map<String, Object> properties) throws Exception {

        if (indexExists(index)) {
            return true;
        }
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.settings(Settings.builder().put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2));

        Map<String, Object> jsonMap = new HashMap<>();
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        jsonMap.put(indexType, mapping);
        request.mapping(indexType, jsonMap);

        CreateIndexResponse createIndexResponse = client.indices().create(
                request);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        return acknowledged;
    }

    /**
     * 删除索引
     *
     * @param index
     * @return
     * @throws Exception
     */
    public boolean indexDelete(String index) throws Exception {
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(index);
            DeleteIndexResponse deleteIndexResponse = client.indices().delete(
                    request);
            return deleteIndexResponse.isAcknowledged();
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 创建更新文档
     *
     * @param index
     * @param indexType
     * @param documentId
     * @param josonStr
     * @return
     * @throws Exception
     */
    public boolean documentCreate(String index, String indexType,
                                  String documentId, String josonStr) throws Exception {
        IndexRequest request = new IndexRequest(index, indexType, documentId);

        request.source(josonStr, XContentType.JSON);
        IndexResponse indexResponse = client.index(request);

        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED
                || indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            return true;
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            return true;
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo
                    .getFailures()) {
                throw new Exception(failure.reason());
            }
        }
        return false;
    }

    /**
     * 创建更新索引
     *
     * @param index
     * @param indexType
     * @param documentId
     * @param map
     * @return
     * @throws Exception
     */
    public boolean documentCreate(String index, String indexType,
                                  String documentId, Map<String, Object> map) throws Exception {
        IndexRequest request = new IndexRequest(index, indexType, documentId);

        request.source(map);
        IndexResponse indexResponse = client.index(request);

        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED
                || indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            return true;
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            return true;
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo
                    .getFailures()) {
                throw new Exception(failure.reason());
            }
        }
        return false;
    }

    /**
     * 创建索引
     *
     * @param index
     * @param indexType
     * @param josonStr
     * @return
     * @throws Exception
     */
    public String documentCreate(String index, String indexType, String josonStr)
            throws Exception {
        IndexRequest request = new IndexRequest(index, indexType);

        request.source(josonStr, XContentType.JSON);
        IndexResponse indexResponse = client.index(request);

        String id = indexResponse.getId();
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED
                || indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            return id;
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            return id;
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo
                    .getFailures()) {
                throw new Exception(failure.reason());
            }
        }
        return null;
    }

    /**
     * 创建索引
     *
     * @param index
     * @param indexType
     * @param map
     * @return
     * @throws Exception
     */
    public String documentCreate(String index, String indexType,
                                 Map<String, Object> map) throws Exception {
        IndexRequest request = new IndexRequest(index, indexType);

        request.source(map);
        IndexResponse indexResponse = client.index(request);

        String id = indexResponse.getId();
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED
                || indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            return id;
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            return id;
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo
                    .getFailures()) {
                throw new Exception(failure.reason());
            }
        }
        return null;
    }

    public boolean documentDelete(String index, String indexType,
                                  String documentId) throws Exception {
        DeleteRequest request = new DeleteRequest(index, indexType, documentId);
        DeleteResponse deleteResponse = client.delete(request);
        if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
            return true;
        }
        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            return true;
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo
                    .getFailures()) {
                throw new Exception(failure.reason());
            }
        }
        return false;
    }
}
